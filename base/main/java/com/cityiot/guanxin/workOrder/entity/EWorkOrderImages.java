package com.cityiot.guanxin.workOrder.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@ApiModel(value="工单图片")
@Entity
@CnName("工单图片")
@EntityListeners(AuditingEntityListener.class)
public class EWorkOrderImages extends BaseEntity {
    @ApiModelProperty(value="工单ID",name="workOrderId",example="")
    @CnName("工单ID")
    private long workOrderId;
    @ApiModelProperty(value="工单图片url",name="imageUrl",example="")
    @CnName("工单图片url")
    private String imageUrl;
    @ApiModelProperty(value="工单类型",name="workOrderType",example="")
    @CnName("工单类型") //1巡检工单 2保养工单 3维修工单
    private int workOrderType;
    @ApiModelProperty(value="图片类型",name="imgType",example="")
    @CnName("图片类型") //1创建图片 2指派图片 3处理图片
    private int imgType;


    public long getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(long workOrderId) {
        this.workOrderId = workOrderId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getWorkOrderType() {
        return workOrderType;
    }

    public void setWorkOrderType(int workOrderType) {
        this.workOrderType = workOrderType;
    }

    public int getImgType() {
        return imgType;
    }

    public void setImgType(int imgType) {
        this.imgType = imgType;
    }
}
