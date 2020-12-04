package com.cityiot.guanxin.plan.entity;


import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;

/**
 * @author Guoyz
 * 平面图
 */
@ApiModel(value="平面图")
@CnName("平面图")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Plan extends BaseEntity {
    @ApiModelProperty(value="平面图名",name="name",example="")
    @CnName("平面图名")
    private String name;
    @ApiModelProperty(value="图片url",name="imgUrl",example="")
    @CnName("图片url")
    private String imgUrl;
    @ApiModelProperty(value="备注",name="remark",example="")
    @CnName("备注")
    private String remark;
    @ApiModelProperty(value="平面图宽度",name="bboxWidth",example="")
    @CnName("平面图宽度")
    private long bboxWidth;
    @ApiModelProperty(value="平面图高度",name="bboxHeight",example="")
    @CnName("平面图高度")
    private long bboxHeight;
    @ApiModelProperty(value="图层次序",name="layerOrder",example="")
    @CnName("图层次序")
    @Transient
    private long layerOrder;
    @ApiModelProperty(value="图层类型",name="layerType",example="")
    @CnName("图层类型")
    @Transient
    private String layerType;
    @ApiModelProperty(value="是否上锁",name="planLock",example="")
    @CnName("是否上锁")
    @Transient
    private long planLock;//1为上锁，0为不上锁
    @ApiModelProperty(value="x轴",name="x",example="")
    @CnName("x轴")
    @Transient
    private long x;
    @ApiModelProperty(value="y轴",name="y",example="")
    @CnName("y轴")
    @Transient
    private long y;

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public long getLayerOrder() {
        return layerOrder;
    }

    public void setLayerOrder(long layerOrder) {
        this.layerOrder = layerOrder;
    }

    public String getLayerType() {
        return layerType;
    }

    public void setLayerType(String layerType) {
        this.layerType = layerType;
    }

    public long getPlanLock() {
        return planLock;
    }

    public void setPlanLock(long planLock) {
        this.planLock = planLock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getBboxWidth() {
        return bboxWidth;
    }

    public void setBboxWidth(long bboxWidth) {
        this.bboxWidth = bboxWidth;
    }

    public long getBboxHeight() {
        return bboxHeight;
    }

    public void setBboxHeight(long bboxHeight) {
        this.bboxHeight = bboxHeight;
    }
}
