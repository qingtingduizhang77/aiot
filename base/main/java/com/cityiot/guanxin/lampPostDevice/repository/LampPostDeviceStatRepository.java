package com.cityiot.guanxin.lampPostDevice.repository;

import com.cityiot.guanxin.common.repository.BaseRepository;
import com.cityiot.guanxin.lampPostDevice.entity.LampPostDeviceStat;
import com.cityiot.guanxin.lampPostDevice.entity.QLampPostDeviceStat;
import org.springframework.stereotype.Service;

@Service
public class LampPostDeviceStatRepository extends BaseRepository<LampPostDeviceStat> {

    public long getDeviceSum(Long[] deviceTypeIds){
        return this.count(QLampPostDeviceStat.lampPostDeviceStat.deviceTypeId.in(deviceTypeIds));
    }
}
