package com.cityiot.guanxin.lampPostDevice.repository;

import com.cityiot.guanxin.common.repository.BaseRepository;
import com.cityiot.guanxin.deviceInformation.entity.QDeviceinformation;
import com.cityiot.guanxin.lampPostDevice.entity.LampPostDevice;
import com.cityiot.guanxin.lampPostDevice.entity.QLampPostDevice;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class LampPostDeviceRepository extends BaseRepository<LampPostDevice> {
	
	 public List<LampPostDevice>  getchildrenLampPostDevice(Long deviceId)
	    {
		 
		 QLampPostDevice qlampPostDevice=QLampPostDevice.lampPostDevice;
		 
		 QDeviceinformation qDeviceinformation=QDeviceinformation.deviceinformation;
		 
		 return getQuery().select(qlampPostDevice)
				 .from(qlampPostDevice,qDeviceinformation)
				 .where(qlampPostDevice.deviceId.eq(qDeviceinformation.id)
						 .and(qDeviceinformation.parentId.eq(deviceId))).orderBy(qlampPostDevice.deviceId.desc()).fetch();
	    }
	
//	LampPostDevice

}
