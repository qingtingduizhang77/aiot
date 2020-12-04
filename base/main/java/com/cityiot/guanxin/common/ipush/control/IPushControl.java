package com.cityiot.guanxin.common.ipush.control;



import com.cityiot.guanxin.common.ipush.entity.GeTuiBuilder;
import com.cityiot.guanxin.common.ipush.entity.IPush;
import com.cityiot.guanxin.common.ipush.service.IPushService;
import com.cityiot.guanxin.response.BaseResponse;
import com.cityiot.guanxin.response.RespCode;
import com.cityiot.guanxin.response.RespData;
import com.cityiot.guanxin.user.entity.Accountinfo;
import com.cityiot.guanxin.user.service.AccountinfoService;

import com.gexin.fastjson.JSON;
import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Api(tags  = "消息推送接口")
@RestController
@RequestMapping("/ipush")
public class IPushControl {
    private static final Logger log = LoggerFactory.getLogger(IPushControl.class);

    @Autowired
    private IPushService service;
    @Autowired
    private AccountinfoService accountinfoService;


    static class QueryBean extends BasePageQueryBean {
        // 状态
        private Boolean status;

        public Boolean getstatus() {
            return status;
        }
        public void setStatus(Boolean status) {
            this.status = status;
        }
    }

    /**
     * 向一个客户端发送一条消息
     *
     * @return
     */
    @RequestMapping("/pushMessageToSingle")
    public ApiResult<IPush> pushMessageToSingle(String clientId, String messageTitle, String messageContent) {
        try {
            Accountinfo item = accountinfoService.getItemById(217);
            //GeTuiBuilder geTuiBuilder = new GeTuiBuilder(messageTitle, messageContent, item.getClientId(),item.getId());
            GeTuiBuilder geTuiBuilder = new GeTuiBuilder();
            geTuiBuilder.setClientId(item.getClientId());
            geTuiBuilder.setAccountId(item.getId());
            HashMap<String, String> map = new HashMap<>();
            map.put("messageTitle",messageTitle);
            map.put("messageContent",messageContent);
            geTuiBuilder.setMessage(JSON.toJSONString(map));
            return ApiResult.success(service.pushMessageToSingle(geTuiBuilder));
        } catch (Exception e) {
            log.error("发送个推出错:"+e.getMessage(),e);
            return ApiResult.fail("发送个推出错:"+e.getMessage());
        }
    }

    /**
     * 批量单推
     */
    @RequestMapping("/pushToSingleBatch")
    public BaseApiResult pushToSingleBatch() {
        ArrayList<GeTuiBuilder> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Accountinfo item = accountinfoService.getItemById(i + 217);
            GeTuiBuilder geTuiBuilder = new GeTuiBuilder();
            geTuiBuilder.setClientId(item.getClientId());
            geTuiBuilder.setAccountId(item.getId());
            HashMap<String, String> map = new HashMap<>();
            map.put("messageTitle","第" + i + "条标题");
            map.put("messageContent","第" + i + "条内容");
            geTuiBuilder.setMessage(JSON.toJSONString(map));
            list.add(geTuiBuilder);
        }
        try {
            return ApiResult.success(service.pushToSingleBatch(list));
        } catch (Exception e) {
            log.error("发送个推出错:"+e.getMessage(),e);
            return ApiResult.fail("发送个推出错:"+e.getMessage());
        }
    }
}
