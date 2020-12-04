package com.cityiot.guanxin.organization.service;


import com.cityiot.guanxin.organization.entity.OrgMember;
import com.cityiot.guanxin.organization.entity.QOrgMember;
import com.cityiot.guanxin.organization.repository.OrgMemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

import java.util.List;


/**
 * OrgMember 数据Service
 * @author zhengjc
 *
 */
@Service
public class OrgMemberService extends BaseService<OrgMemberRepository, OrgMember>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(OrgMemberService.class);

	public List<OrgMember> getAllUserByOrgId(long orgId) {
		QOrgMember qo = QOrgMember.orgMember;
		return this.getAllItemByQuerybean(null,query -> query.where(qo.orgId.eq(orgId)));
	}
}
