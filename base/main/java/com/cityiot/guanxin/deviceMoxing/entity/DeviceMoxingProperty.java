package com.cityiot.guanxin.deviceMoxing.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@ApiModel(value="设备模型属性")
@Entity
@CnName("设备模型属性")
@EntityListeners(AuditingEntityListener.class)
public class DeviceMoxingProperty extends BaseEntity {

    @ApiModelProperty(value="属性名称",name="name",example="")
    @CnName("属性名称")
    private String name;

    @ApiModelProperty(value="标识符",name="tag",example="")
    @CnName("标识符")
    private String tag;

    @ApiModelProperty(value="数据类型",name="dataType",example="")
    @CnName("数据类型")
    private String dataType;

    @ApiModelProperty(value="单位",name="unit",example="")
    @CnName("单位")
    private String unit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
