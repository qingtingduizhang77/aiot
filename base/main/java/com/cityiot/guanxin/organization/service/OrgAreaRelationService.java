package com.cityiot.guanxin.organization.service;


import com.cityiot.guanxin.organization.entity.OrgAreaRelation;
import com.cityiot.guanxin.organization.entity.Organization;
import com.cityiot.guanxin.organization.entity.QOrgAreaRelation;
import com.cityiot.guanxin.organization.repository.OrgAreaRelationRepository;
import com.cityiot.guanxin.user.entity.QUserAreaReleation;
import com.cityiot.guanxin.user.entity.User;
import com.cityiot.guanxin.user.entity.UserAreaRelation;
import com.cityiot.guanxin.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swallow.framework.exception.SwallowException;
import swallow.framework.service.BaseService;

import java.util.List;
import java.util.stream.Collectors;


/**
 * OrgAreaRelation 数据Service
 * @author zhengjc
 *
 */
@Service
@Transactional
public class OrgAreaRelationService extends BaseService<OrgAreaRelationRepository, OrgAreaRelation>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(OrgAreaRelationService.class);

	@Autowired
	private OrganizationService orgService;

	public void allocateOrgArea (long orgId,Long[] readyGiveUpAreaIds,Long[] readyGiveAreaIds){
		Organization organization = orgService.getItemById(orgId);
		if(null==organization) {
			throw new SwallowException("组织不存在，非法操作");
		}
		if(null!=readyGiveUpAreaIds){
			giveUpOrgArea(orgId,readyGiveUpAreaIds);
		}
		if(null!=readyGiveAreaIds) {
			giveOrgArea(orgId,readyGiveAreaIds);
		}
	}

	/** 取消授予某个组织区域
	 */
	public void giveUpOrgArea(long orgId,Long[] areaIds) {
		for (long areaId:areaIds) {
			this.deleteEntityByOrgIdAndAreaId(orgId,areaId);
		}
	}

	/** 删除组织区域的关系
	 * @return
	 */
	public long deleteEntityByOrgIdAndAreaId(long orgId,long areaId){
		QOrgAreaRelation qOrgAreaRelation = QOrgAreaRelation.orgAreaRelation;
		return getRepsitory()
				.deleteEntityByColumns(query -> {
					return query.where(
							qOrgAreaRelation.orgId.eq(orgId)
									.and(qOrgAreaRelation.areaId.eq(areaId)));
				});
	}

	/** 授予组织区域
	 * @param orgId
	 * @param areaIds
	 */
	@Transactional
	public void giveOrgArea(long orgId,Long[] areaIds) {
		List<Long> userAreaIds = this.getAllAreaIdsByOrgId(orgId);
		for(long areaId: areaIds) {
			if(userAreaIds.contains(areaId)){
				throw new SwallowException("组织已经授予该区域");
			}
			OrgAreaRelation orgAreaRelation = new OrgAreaRelation();
			orgAreaRelation.setOrgId(orgId);
			orgAreaRelation.setAreaId(areaId);
			this.insertItem(orgAreaRelation);
		}
	}

	/** 通过组织Id获取组织所有的区域Id
	 * @param orgId
	 * @return
	 */
	public List<Long> getAllAreaIdsByOrgId(long orgId) {
		return getAllUserAreaRelationByOrgId(orgId).stream().map(item -> item.getAreaId()).collect(Collectors.toList());
	}

	/** 根据组织Id获取组织的所有对应区域Id
	 * @param orgId
	 * @return
	 */
	public List<OrgAreaRelation> getAllUserAreaRelationByOrgId(long orgId) {
		return getRepsitory()
				.findEntitysByColumns(query -> {
					return query.where(QOrgAreaRelation.orgAreaRelation.orgId.eq(orgId));
				});
	}
}
