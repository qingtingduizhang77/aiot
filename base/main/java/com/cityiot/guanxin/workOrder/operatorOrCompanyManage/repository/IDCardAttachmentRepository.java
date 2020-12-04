package com.cityiot.guanxin.workOrder.operatorOrCompanyManage.repository;


import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.IDCardAttachment;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.QIDCardAttachment;
import com.querydsl.core.types.EntityPath;
import org.springframework.stereotype.Service;
import swallow.framework.jpaquery.repository.SwallowRepository;

/**
 * 身份证附件 的数据访问
 * 注意 因为使用的是自定义的SwallBaseRepository,使用@Repository无效
 * @author aohanhe
 *
 */
@Service
public class IDCardAttachmentRepository extends SwallowRepository<IDCardAttachment>{

	

}
