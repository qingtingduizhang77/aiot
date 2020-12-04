package com.cityiot.guanxin.workOrder.operatorOrCompanyManage.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.IDCardAttachment;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.repository.IDCardAttachmentRepository;

/**
 * IDCardAttachment 数据Service
 * @author aohanhe
 *
 */
@Service
public class IDCardAttachmentService extends BaseService<IDCardAttachmentRepository, IDCardAttachment>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(IDCardAttachmentService.class);
	
}
