package com.cityiot.guanxin.light.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * @author huangjinyong
 * 路灯分组实体
 */
@ApiModel(value="路灯分组")
@CnName("路灯分组实体")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class LightGroup extends BaseEntity {
    @ApiModelProperty(value="组名",name="groupName",example="")
    @CnName("组名")
    private String groupName;
    @ApiModelProperty(value="定时开关状态",name="timeSwitchStatus",example="")
    @CnName("定时开关状态")
    // 0未开启 1已开启
    private int timeSwitchStatus;
    @ApiModelProperty(value="已绑定路灯数量",name="lampCount",example="")
    @CnName("已绑定路灯数量")
    private int lampCount;
    @ApiModelProperty(value="分组地址",name="address",example="")
    @CnName("分组地址")
    private String address;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getTimeSwitchStatus() {
        return timeSwitchStatus;
    }

    public void setTimeSwitchStatus(int timeSwitchStatus) {
        this.timeSwitchStatus = timeSwitchStatus;
    }

    public int getLampCount() {
        return lampCount;
    }

    public void setLampCount(int lampCount) {
        this.lampCount = lampCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
