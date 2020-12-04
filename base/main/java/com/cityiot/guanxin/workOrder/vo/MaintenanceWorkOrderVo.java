package com.cityiot.guanxin.workOrder.vo;

import com.cityiot.guanxin.common.entity.BaseEntity;
import com.cityiot.guanxin.entity.SystemVariable;
import com.cityiot.guanxin.workOrder.entity.EMaintenanceWorkOrder;
import com.cityiot.guanxin.workOrder.entity.EWorkOrderImages;
import com.cityiot.guanxin.workOrder.maintenance.entity.MaintenanceRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MaintenanceWorkOrderVo  {
    private EMaintenanceWorkOrder objData;
    private List<EWorkOrderImages> workOrderImages;
    private List<MaintenanceRecord> records;

    public EMaintenanceWorkOrder getObjData() {
        return objData;
    }

    public void setObjData(EMaintenanceWorkOrder objData) {
        this.objData = objData;
    }

    public List<EWorkOrderImages> getWorkOrderImages() {
        return workOrderImages;
    }

    public void setWorkOrderImages(List<EWorkOrderImages> workOrderImages) {
        this.workOrderImages = workOrderImages;
    }

    public List<MaintenanceRecord> getRecords() {
        return records;
    }

    public void setRecords(List<MaintenanceRecord> records) {
        this.records = records;
    }
}
