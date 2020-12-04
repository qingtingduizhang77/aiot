package com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@ApiModel(value="负责区域")
@Entity
@CnName("负责区域")
@EntityListeners(AuditingEntityListener.class)
public class ResponsibleArea extends BaseEntity {

    @ApiModelProperty(value="运维ID",name="operatorId",example="")
    @CnName("运维ID")
    private Long operatorId;

    @ApiModelProperty(value="区域",name="area",example="")
    @CnName("区域")
    private String area;

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
