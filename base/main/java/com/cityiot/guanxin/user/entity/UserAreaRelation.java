package com.cityiot.guanxin.user.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;

@ApiModel(value="用户和区域")
@Entity
@CnName("用户和区域关系")
public class UserAreaRelation extends BaseEntity {
    @ApiModelProperty(value="用户ID",name="userId",example="")
    private long userId;
    @ApiModelProperty(value="区域ID",name="areaId",example="")
    private long areaId;
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }
}
