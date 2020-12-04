package com.cityiot.guanxin.workOrder.entity.Vo;

import com.cityiot.guanxin.workOrder.entity.EInspectionWorkOrder;
import com.cityiot.guanxin.workOrder.entity.EWorkOrderImages;

import java.util.List;

public class InspectionVo {

    private EInspectionWorkOrder eInspectionWorkOrder;

    private List<EWorkOrderImages> inspectionPictures;

    private List<EWorkOrderImages> deleteInspectionPictures;

    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<EWorkOrderImages> getDeleteInspectionPictures() {
        return deleteInspectionPictures;
    }

    public void setDeleteInspectionPictures(List<EWorkOrderImages> deleteInspectionPictures) {
        this.deleteInspectionPictures = deleteInspectionPictures;
    }

    public EInspectionWorkOrder geteInspectionWorkOrder() {
        return eInspectionWorkOrder;
    }

    public void seteInspectionWorkOrder(EInspectionWorkOrder eInspectionWorkOrder) {
        this.eInspectionWorkOrder = eInspectionWorkOrder;
    }

    public List<EWorkOrderImages> getInspectionPictures() {
        return inspectionPictures;
    }

    public void setInspectionPictures(List<EWorkOrderImages> inspectionPictures) {
        this.inspectionPictures = inspectionPictures;
    }

}
