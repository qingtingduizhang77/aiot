package com.cityiot.guanxin.monitor.service;

import com.cityiot.guanxin.monitor.respository.DeviceStatusInfoMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceStatusInfoService {

    @Autowired
    private DeviceStatusInfoMongoRepository deviceStatusInfoMongoRepository;

    /**n 统计某个状态的实时设备数量
     * @param collectionName
     * @param status
     * @return
     */
    public long countForStatus(String collectionName,int status) {
        return deviceStatusInfoMongoRepository.countForStatus(collectionName,status);
    }
}
