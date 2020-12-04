package com.cityiot.guanxin.lightPlans.entity;

import com.cityiot.guanxin.common.entity.OnlyIdEntity;
import io.swagger.annotations.ApiModel;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * 路灯控制执行
 * LightPlansGroup关联表
 * @author Guoyz
 * createTime   2020/5/15 14:45
 */
@ApiModel(value="路灯控制执行")
@CnName("路灯控制执行")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class LightPlansGroupdo extends OnlyIdEntity {
    @CnName("分组id")
    private Long lightPlansGroupId;
    @CnName("路灯设备id")
    private Long lightDeviceId;
    @CnName("计划策略id")
    private Long lightPlansId;


    public Long getLightPlansGroupId() {
        return lightPlansGroupId;
    }

    public void setLightPlansGroupId(Long lightPlansGroupId) {
        this.lightPlansGroupId = lightPlansGroupId;
    }

    public Long getLightDeviceId() {
        return lightDeviceId;
    }

    public void setLightDeviceId(Long lightDeviceId) {
        this.lightDeviceId = lightDeviceId;
    }

    public Long getLightPlansId() {
        return lightPlansId;
    }

    public void setLightPlansId(Long lightPlansId) {
        this.lightPlansId = lightPlansId;
    }
}
