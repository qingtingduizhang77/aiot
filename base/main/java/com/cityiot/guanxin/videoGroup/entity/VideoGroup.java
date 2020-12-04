package com.cityiot.guanxin.videoGroup.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import com.cityiot.guanxin.planGroup.entity.PlanGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * 视频分组
 * @author Guoyz
 * createTime   2020/7/31 14:45
 */
@Entity
@CnName("视频分组")
@ApiModel(value="视频分组")
@EntityListeners(AuditingEntityListener.class)
public class VideoGroup extends BaseEntity {
    public static final String T_VideoGroup = "T_VideoGroup";

    @ApiModelProperty(value="分组名",name="name",example="")
    @CnName("分组名")
    private String name;

    @ApiModelProperty(value="上级分组Id",name="parentId",example="")
    @CnName("上级分组Id")
    @JoinEntity(name = "id",joinEntityClass = VideoGroup.class,joinEntityAlias = T_VideoGroup)
    private long parentId;

    @ApiModelProperty(value="级别",name="level",example="")
    @CnName("级别")
    private long level;

    @ApiModelProperty(value="排序号",name="orderNo",example="")
    @CnName("排序号")
    private long orderNo;

    @ApiModelProperty(value="父分组id数组",name="parentIdArr",example="")
    @CnName("父分组id数组")
    private String parentIdArr;

    @ApiModelProperty(value="备注",name="remark",example="")
    @CnName("备注")
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }

    public String getParentIdArr() {
        return parentIdArr;
    }

    public void setParentIdArr(String parentIdArr) {
        this.parentIdArr = parentIdArr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
