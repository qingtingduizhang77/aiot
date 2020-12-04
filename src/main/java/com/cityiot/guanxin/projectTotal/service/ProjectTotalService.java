package com.cityiot.guanxin.projectTotal.service;

import com.cityiot.guanxin.common.repository.BaseMongoRepository;
import com.cityiot.guanxin.mongo.IMongoDao;
import com.cityiot.guanxin.projectTotal.pojo.ProjectTotal;

import com.cityiot.guanxin.projectTotal.pojo.guanWang;
import com.gexin.fastjson.JSON;
import com.gexin.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class ProjectTotalService extends BaseMongoRepository<ProjectTotal> {
    // 日志对象
    private static final Logger log = LoggerFactory.getLogger(ProjectTotalService.class);
    @Autowired
    private IMongoDao iMongoDao;

    @Value("${projectTotal.url}")
    private String url;
    @Value("${projectTotal.companyId}")
    private String companyId;
    @Value("${projectTotal.token}")
    private String token;

    public List<ProjectTotal> getAllItems() {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                url+"?companyId={1}&token={2}",
                null,
                String.class,
                companyId,
                token);

        if(responseEntity.getStatusCodeValue()!=200){
            System.out.println("请求失败，从本地获取");
            return getItemByDB();
        }

        String body = responseEntity.getBody();
        JSONArray objects = JSON.parseArray(body);
        List<ProjectTotal> list = JSONArray.parseArray(body, ProjectTotal.class);

        if(list==null || list.size()<1){
            System.out.println("数据为空，从本地获取");
            return getItemByDB();
        }
        //保存到本地
        for (ProjectTotal projectTotal : list) {
        this.replaceOneEntity(projectTotal);
        }
        return list;
    }
    //从mongodb取数据
    public List<ProjectTotal> getItemByDB(){
        return this.mongoTemplate.findAll(ProjectTotal.class);
    }
    //查询管网数据
    public List<guanWang> getAllItemsGuanWang(){
        return this.mongoTemplate.findAll(guanWang.class);
    }

    //更新管网数据
    public List<guanWang> updateGuanWang(guanWang[] guanWangs){
        if(guanWangs!=null&&guanWangs.length>0){
            for (guanWang guanWang : guanWangs) {
                this.mongoTemplate.save(guanWang);
            }
        }
        return getAllItemsGuanWang();
    }
}
