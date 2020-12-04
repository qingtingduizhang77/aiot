package com.cityiot.guanxin.monitor.respository;

import com.cityiot.guanxin.common.repository.BaseMongoRepository;
import com.cityiot.guanxin.monitor.entity.DeviceStatusInfo;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class DeviceStatusInfoMongoRepository extends BaseMongoRepository<DeviceStatusInfo> {

    public long countForStatus(String collectionName,int status) {
        Criteria criteria = Criteria.where("status").is(status);
        return mongoTemplate.count(new Query(criteria),collectionName);
    }
}
