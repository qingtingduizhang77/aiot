package com.cityiot.guanxin.workOrder.vo;


import com.cityiot.guanxin.common.entity.BaseEntity;
import com.cityiot.guanxin.workOrder.entity.EInspectionWorkOrder;
import com.cityiot.guanxin.workOrder.entity.EWorkOrderImages;
import com.cityiot.guanxin.workOrder.inspection.entity.InspectionRecord;

import java.util.Date;
import java.util.List;

public class InspectionWorkOrderVo {
    private EInspectionWorkOrder objData;
    private List<EWorkOrderImages> workOrderImages;
    private List<InspectionRecord> records;

    public EInspectionWorkOrder getObjData() {
        return objData;
    }

    public void setObjData(EInspectionWorkOrder objData) {
        this.objData = objData;
    }

    public List<EWorkOrderImages> getWorkOrderImages() {
        return workOrderImages;
    }

    public void setWorkOrderImages(List<EWorkOrderImages> workOrderImages) {
        this.workOrderImages = workOrderImages;
    }

    public List<InspectionRecord> getRecords() {
        return records;
    }

    public void setRecords(List<InspectionRecord> records) {
        this.records = records;
    }
}
