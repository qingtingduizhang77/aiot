package com.cityiot.guanxin.common.utils;


import com.cityiot.guanxin.common.ipush.entity.GeTuiBuilder;
import com.gexin.fastjson.JSON;
import com.gexin.fastjson.JSONObject;
import com.gexin.rp.sdk.base.IBatch;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created on 20/3/2. 个推发送工具类
 * 工程依赖了4个jar包(存放在工程的libs目录下)
 * 1:gexin-rp-sdk-http-4.1.0.5.jar 2:gexin-rp-sdk-template-4.0.0.24.jar
 * 3:gexin-rp-fastjson-1.0.0.3.jar 4:gexin-rp-sdk-base-4.0.0.30.jar
 * 帮助文档:http://docs.getui.com/getui/server/java/summary/
 */
@Component
public class GeTuiUtil {
    @Value("${getui.appId}")
    private String appId;
    @Value("${getui.appKey}")
    private String appKey;
    @Value("${getui.masterSecret}")
    private String masterSecret;
    @Value("${getui.url}")
    private String url;

    /**
     * 批量单推
     * 当单推任务较多时，推荐使用该接口，可以减少与服务端的交互次数。
     *
     * @param geTuiBuilderList
     * 发送批量个推请构造GeTuiBuilder类的列表
     *
     * 返回参数:
     * {
     *  "1":{"result":"ok","taskId":"OSS-xxx","cid":"xxx","status":"successed_online"},
     *  "2":{"result":"ok","taskId":"OSS-xxx","cid":"xxx","status":"successed_online"},
     *  ......
     * }
     */
    public Map pushToSingleBatch(List<GeTuiBuilder> geTuiBuilderList) throws Exception {
        //推送主体
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        IBatch batch = push.getBatch();

        IPushResult ret = null;
        for (GeTuiBuilder geTuiBuilder : geTuiBuilderList) {
            String jsonmessage = geTuiBuilder.getMessage();
            Map map = JSONObject.parseObject(jsonmessage);
            String clientId = geTuiBuilder.getClientId();
            if (clientId ==null){
                continue;
            }
            //推送模板
            TransmissionTemplate template = getTransmissionTemplate(map);
            //推送方式
            SingleMessage message = new SingleMessage();
            message.setData(template);
            message.setOffline(true);
            message.setOfflineExpireTime(24 * 3600 * 1000);

            // 设置推送目标，填入appid和clientId
            Target target = new Target();
            target.setAppId(appId);
            target.setClientId(clientId);
            batch.add(message, target);
        }
        //发出推送
        ret = batch.submit();
        Map<String, Object> response = ret.getResponse();
        JSONObject info = (JSONObject) response.get("info");
        Map<String, Object> map = info.getInnerMap();
        return map;
    }





    /**
     * 单推
     * 推送到单个app用户
     * @param geTuiBuilder
     * @return Map
     * {
     *     "result":"ok",
     *     "taskId":"xxx",
     *     "status":"successed_online"
     * }
     */
    public Map pushMessageToSingle(GeTuiBuilder geTuiBuilder) {
        //推送主体
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        //推送模板
        Map map = JSONObject.parseObject(geTuiBuilder.getMessage());
        TransmissionTemplate template=getTransmissionTemplate(map);
        //推送方式
        SingleMessage message = new SingleMessage();//单推
        message.setOffline(true);
        message.setOfflineExpireTime(24 * 3600 * 1000);// 可选：离线有效时间，默认1小时，现在1天
        message.setData(template);

        //推送目标
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(geTuiBuilder.getClientId());
        //别名推送
        //target.setAlias(Alias);
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        if (ret != null) {
            return ret.getResponse();
        } else {
            return null;
        }
    }



    /**(唯一的模板)(透传模板)
     * 获取同时有Android第三方推送及IOS推送功能的透传消息
     * 透传模式没有消息样式，无法通过点击打开app
     * @param customParam 自定义属性
     * @return
     */
    private TransmissionTemplate getTransmissionTemplate(Map<String,String> customParam) {
        TransmissionTemplate template = new TransmissionTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        template.setTransmissionContent(JSON.toJSONString(customParam)); // 透传内容


        //苹果手机消息APN
        APNPayload payload = new APNPayload();
        // 在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
        String badge = null;
        if (badge != null && badge.trim().length() > 0) {
            payload.setAutoBadge(badge);
        }
        payload.setContentAvailable(1);
        payload.setSound("default");
        if (customParam != null) { // 透传内容
            for (Map.Entry<String, String> enty : customParam.entrySet()) {
                payload.addCustomMsg(enty.getKey(), enty.getValue());
            }
        }
        //IOS通知提示样式
        APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
        alertMsg.setTitle(customParam.get("messageTitle"));
        alertMsg.setBody(customParam.get("messageContent"));
        payload.setAlertMsg(alertMsg);

        template.setAPNInfo(payload); // ios消息推送必须加上否则离线收不到
        return template;
    }
}
