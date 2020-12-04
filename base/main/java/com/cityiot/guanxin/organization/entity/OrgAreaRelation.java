package com.cityiot.guanxin.organization.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@ApiModel(value="组织和区域")
@Entity
@CnName("组织和区域关系")
@EntityListeners(AuditingEntityListener.class)
public class OrgAreaRelation extends BaseEntity {
    @ApiModelProperty(value="组织ID",name="orgId",example="")
    private long orgId;
    @ApiModelProperty(value="区域ID",name="areaId",example="")
    private long areaId;

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }
}
