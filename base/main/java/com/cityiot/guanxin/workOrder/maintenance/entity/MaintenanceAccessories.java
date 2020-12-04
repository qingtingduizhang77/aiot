package com.cityiot.guanxin.workOrder.maintenance.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;


@ApiModel(value="设备保养/巡检附件")
@Entity
@CnName("设备保养/巡检附件")
@EntityListeners(AuditingEntityListener.class)
public class MaintenanceAccessories extends BaseEntity {

    @ApiModelProperty(value="记录ID",name="recordID",example="")
    @CnName("记录ID")
    @Column(nullable = false)
    private Long  recordID;

    /**
     * 1:保养
     * 2:巡检
     * 3:维修
     */
    @ApiModelProperty(value="类型",name="type",example="")
    @CnName("类型")
    @Column(nullable = false)
    private Integer type;
    /**
     * 1:图片
     * 2:文档
     */
    @ApiModelProperty(value="文件类型",name="fileType",example="")
    @CnName("文件类型")
    @Column(nullable = false)
    private Integer fileType;

    @ApiModelProperty(value="保存路径",name="saveRoute",example="")
    @CnName("保存路径")
    @Column(nullable = false)
    private String saveRoute;

    public Long getRecordID() {
        return recordID;
    }

    public void setRecordID(Long recordID) {
        this.recordID = recordID;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getSaveRoute() {
        return saveRoute;
    }

    public void setSaveRoute(String saveRoute) {
        this.saveRoute = saveRoute;
    }
}
