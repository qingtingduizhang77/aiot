package com.cityiot.guanxin.message.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swallow.framework.service.BaseService;

import com.cityiot.guanxin.message.entity.EMessageCenter;
import com.cityiot.guanxin.message.repository.EMessageCenterRepository;

import java.util.Date;

/**
 * EMessageCenter 数据Service
 * @author aohanhe
 *
 */
@Service
public class EMessageCenterService extends BaseService<EMessageCenterRepository, EMessageCenter>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(EMessageCenterService.class);

	@Autowired
	private EMessageCenterRepository repository;

	public long notReadCount(long id) {
		return repository.notReadCount(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public void tagMessage(long messageId, int status) {
		repository.tagMessage(messageId, status);
	}

	@Transactional(rollbackFor = Exception.class)
	public void tagMessageAll(long id) {
		repository.tagMessageAll(id);
	}


	@Transactional(rollbackFor = Exception.class)
	public EMessageCenter insertNewItem(int messageType, int messageStatus, long targetId){
		EMessageCenter messageCenter = new EMessageCenter();
		messageCenter.setMessageContent("你有新的工单需要处理,请进入工单管理进行查看!");
		messageCenter.setMessageType(messageType);
		messageCenter.setMessageStatus(messageStatus);
		messageCenter.setMessageTime(new Date());
		messageCenter.setTargetId(targetId);
		return this.insertItem(messageCenter);
	}
}
