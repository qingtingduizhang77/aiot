package com.cityiot.guanxin.workOrder.entity.Vo;

import com.cityiot.guanxin.workOrder.entity.EMaintenanceWorkOrder;
import com.cityiot.guanxin.workOrder.entity.EWorkOrderImages;

import java.util.List;

public class MaintenanceVo {

    private EMaintenanceWorkOrder entity;

    private List<EWorkOrderImages> images;

    private List<EWorkOrderImages> deleteImages;

    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public EMaintenanceWorkOrder getEntity() {
        return entity;
    }

    public void setEntity(EMaintenanceWorkOrder entity) {
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
}
