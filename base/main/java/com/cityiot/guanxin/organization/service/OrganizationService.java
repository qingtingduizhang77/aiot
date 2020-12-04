package com.cityiot.guanxin.organization.service;


import com.cityiot.guanxin.organization.entity.OrgMember;
import com.cityiot.guanxin.organization.entity.Organization;
import com.cityiot.guanxin.organization.entity.QOrgMember;
import com.cityiot.guanxin.organization.repository.OrganizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swallow.framework.exception.SwallowException;
import swallow.framework.service.BaseService;

import java.util.Date;


/**
 * Organization 数据Service
 * @author zhengjc
 *
 */
@Service
public class OrganizationService extends BaseService<OrganizationRepository, Organization>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(OrganizationService.class);

	@Autowired
	OrgMemberService orgMemberService;

	public Organization insertItem(Organization organization, Long[] readyGiveUserIds) throws SwallowException {
		Date now = new Date();
		organization.setCreated(now);
		organization.setLastmodi(now);
		organization = super.insertItem(organization);
		addOrgMember(organization.getId(),readyGiveUserIds);
		return organization;
	}

	public Organization updateItem(Organization organization, Long[] readyGiveUserIds
			,Long[] readyGiveUpUserIds) throws SwallowException {
		Date now = new Date();
		organization.setLastmodi(now);

		addOrgMember(organization.getId(),readyGiveUserIds);
		deleteOrgMember(organization.getId(),readyGiveUpUserIds);
		return super.updateItem(organization);
	}

	public void addOrgMember(long orgId,Long[] readyGiveUserIds) throws SwallowException {
		if(null!=readyGiveUserIds  && readyGiveUserIds.length!=0) {
			for(var userId:readyGiveUserIds){
				OrgMember orgMember = new OrgMember();
				Date now = new Date();
				orgMember.setCreated(now);
				orgMember.setLastmodi(now);
				orgMember.setOrgId(orgId);
				orgMember.setUserId(userId);
				orgMemberService.insertItem(orgMember);
			}
		}
	}

	public void deleteOrgMember(long orgId,Long[] readyGiveUpUserIds) {
		if(null!=readyGiveUpUserIds && readyGiveUpUserIds.length!=0) {
			for(var userId:readyGiveUpUserIds){
				QOrgMember df = QOrgMember.orgMember;
				OrgMember orgMember = orgMemberService.getItem(query -> query.where(df.userId.eq(userId).and(df.orgId.eq(orgId))));
				orgMemberService.deleteItemById(orgMember.getId());
			}
		}
	}
}
