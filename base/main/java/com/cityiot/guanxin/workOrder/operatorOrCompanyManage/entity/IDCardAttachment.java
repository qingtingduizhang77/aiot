package com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@ApiModel(value="身份证附件")
@Entity
@CnName("身份证附件")
@EntityListeners(AuditingEntityListener.class)
public class IDCardAttachment extends BaseEntity {

    @ApiModelProperty(value="运维ID",name="operatorId",example="")
    @CnName("运维ID")
    @Column(nullable = false)
    private Long operatorId;

    @ApiModelProperty(value="图片地址",name="imgUrl",example="")
    @CnName("图片地址")
    @Column(nullable = false)
    private String imgUrl;
    /**
     * 1：正面
     * 0：反面
     */
    @ApiModelProperty(value="图片类型",name="imgType",example="")
    @CnName("图片类型")
    @Column(nullable = false)
    private Integer imgType;


    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getImgType() {
        return imgType;
    }

    public void setImgType(Integer imgType) {
        this.imgType = imgType;
    }
}
