package com.cityiot.guanxin.common.ipush.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityiot.guanxin.common.ipush.entity.GeTuiBuilder;
import com.cityiot.guanxin.common.ipush.entity.IPush;
import com.cityiot.guanxin.common.ipush.entity.IPushRepository;
import com.cityiot.guanxin.serviceClient.ServiceClientUtil;

import net.sf.json.JSONObject;
import swallow.framework.service.BaseService;


//个推业务层
@Service
public class IPushService extends BaseService<IPushRepository, IPush> {
	
	private static final Logger log = LoggerFactory.getLogger(IPushService.class);
	
//    @Autowired
//    private GeTuiUtil geTuiUtil;
//    @Autowired
//    private IPushRepository repository;
    
    @Autowired
	 private ServiceClientUtil serviceClientUtil;

    /**
     *单推
     * @param geTuiBuilder
     * @throws Exception
     */
    //单个用户个体单条推送(如果批量发送)
    public boolean pushMessageToSingle(GeTuiBuilder geTuiBuilder) {
    	
    	
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("function", "IPush");
			map.put("type", "single");
			map.put("data", geTuiBuilder);

			String re = serviceClientUtil.csExtend(JSONObject.fromObject(map).toString());

			if (re != null) {
				JSONObject jobject = JSONObject.fromObject(re);
				if (jobject.getInt("Code") != 0) {
					System.out.println(jobject.getString("Msg"));
					
					log.error("消息推送："+re);
					return false;
				}
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("消息推送："+e.getMessage());
			return false;
		}

//        Map ret = geTuiUtil.pushMessageToSingle(geTuiBuilder);
//        IPush iPush = new IPush();
//        iPush.setClientId(geTuiBuilder.getClientId());
//        iPush.setAccountId(geTuiBuilder.getAccountId());
//        Map map = geTuiBuilder.getMessage();
//        iPush.setMessage(JSONObject.fromObject(map).toString());//map消息转字符串
//        iPush.setPushTime(new Date());
//        iPush.setStatus(ret.get("result").equals("ok")?0:1);//0发送成功，1发送失败
//        iPush = repository.insertItem(iPush);
        return true;
    }


    /**
     * 批量单推(Batch)
     * 当单推任务较多时，推荐使用该接口，可以减少与服务端的交互次数。
     */
    public boolean pushToSingleBatch(List<GeTuiBuilder> geTuiBuilderList) {
    	
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("function", "IPush");
			map.put("type", "list");
			map.put("data", geTuiBuilderList);

			String re = serviceClientUtil.csExtend(JSONObject.fromObject(map).toString());

			if (re != null) {
				JSONObject jobject = JSONObject.fromObject(re);
				if (jobject.getInt("Code") != 0) {
					System.out.println(jobject.getString("Msg"));
					log.error("消息推送："+re);
					return false;
				}
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("消息推送："+e.getMessage());
			return false;
		}
    	
//        //过滤没有cid的用户数据
//        if(geTuiBuilderList==null || geTuiBuilderList.size()==0){return true;}
//        geTuiBuilderList = geTuiBuilderList.stream().filter(a ->
//                !a.getClientId().equals("") && a.getClientId() != null
//        ).collect(Collectors.toList());
//
//        Function<GeTuiBuilder, IPush> function = geTuiBuilder -> {
//            IPush iPush = new IPush();
//            iPush.setClientId(geTuiBuilder.getClientId());
//            iPush.setAccountId(geTuiBuilder.getAccountId());
//            iPush.setMessage(JSONObject.fromObject(geTuiBuilder.getMessage()).toString());
//            iPush.setPushTime(new Date());
//            iPush.setStatus(0);
//            return iPush;
//        };
//        List<IPush> iPushList = geTuiBuilderList.stream().map(function).collect(Collectors.toList());
//        JSONObject items=null;
//        Map map = null;
//        try {
//            map = geTuiUtil.pushToSingleBatch(geTuiBuilderList);
//        } catch (Exception e) {
//            e.printStackTrace();//网络异常或个推服务器异常
//            //iPushList = iPushList.stream().map(a -> {a.setStatus(1);return a;}).collect(Collectors.toList());//全部发送失败
//        }
//        ArrayList<String> cids = new ArrayList<>();
//        map.values().forEach(a->{
//            JSONObject json = JSONObject.fromObject(a);
//            if(!json.get("result").equals("ok")){cids.add((String)json.get("cid"));}//返回发送失败的cid
//        });
//
//        if(cids.size()>0){//有发送失败的推送
//            for (IPush iPush : iPushList) {
//                String cid = iPush.getClientId();
//                for (String o : cids) {
//                    if(cid.equals(o)){
//                        iPush.setStatus(1);//修改状态为：发送失败
//                    }
//                }
//            }
//        }
//        for (IPush iPush : iPushList) {
//            repository.insertItem(iPush);
//        }
        return true;
    }

}
