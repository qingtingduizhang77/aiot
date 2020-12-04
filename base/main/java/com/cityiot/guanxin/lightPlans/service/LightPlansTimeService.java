package com.cityiot.guanxin.lightPlans.service;

import com.cityiot.guanxin.lightPlans.entity.LightPlans;
import com.cityiot.guanxin.lightPlans.entity.LightPlansTime;
import com.cityiot.guanxin.lightPlans.entity.QLightPlansTime;
import com.cityiot.guanxin.lightPlans.repository.LightPlansTimeRepository;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class LightPlansTimeService extends BaseService<LightPlansTimeRepository, LightPlansTime> {
    private static final Logger log = LoggerFactory.getLogger(LightPlansTimeService.class);
    //根据lightPlansId条件删除
    public void deleteItemBylightPlansId(long lightPlansId) {
        getRepsitory().deleteEntityByColumns(
                query -> query.where(QLightPlansTime.lightPlansTime.lightPlansId.eq(lightPlansId)));
    }

    //根据lightPlans新增表数据
    public void insertItemByLightPlans(LightPlans lightPlans){
        JSONArray jsonArray = JSONArray.fromObject(lightPlans.getPlansTime());
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
        for (int i = 0; i < jsonArray.size(); i++) {
            LightPlansTime lightPlansTime = new LightPlansTime();
            lightPlansTime.setLightPlansId(lightPlans.getId());
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            lightPlansTime.setLuminance(jsonObject.getInt("luminance"));
            lightPlansTime.setType(jsonObject.getString("type"));
            String time = jsonObject.getString("time");
            ParsePosition pos = new ParsePosition(0);
            Date parse = sf.parse(time, pos);
            lightPlansTime.setTime(parse);
            super.insertItem(lightPlansTime);
        }
    }
}
