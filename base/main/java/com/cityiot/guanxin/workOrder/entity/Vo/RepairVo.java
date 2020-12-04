package com.cityiot.guanxin.workOrder.entity.Vo;

import com.cityiot.guanxin.workOrder.entity.ERepairWorkOrder;
import com.cityiot.guanxin.workOrder.entity.EWorkOrderImages;

import java.util.List;

public class RepairVo {
    private ERepairWorkOrder entity;

    private List<EWorkOrderImages> images;

    private List<EWorkOrderImages> deleteImages;

    private Integer type;

    private Integer isProcess;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public ERepairWorkOrder getEntity() {
        return entity;
    }

    public void setEntity(ERepairWorkOrder entity) {
        this.entity = entity;
    }

    public List<EWorkOrderImages> getImages() {
        return images;
    }

    public void setImages(List<EWorkOrderImages> images) {
        this.images = images;
    }

    public List<EWorkOrderImages> getDeleteImages() {
        return deleteImages;
    }

    public void setDeleteImages(List<EWorkOrderImages> deleteImages) {
        this.deleteImages = deleteImages;
    }

    public Integer getIsProcess() {
        return isProcess;
    }

    public void setIsProcess(Integer isProcess) {
        this.isProcess = isProcess;
    }
}
