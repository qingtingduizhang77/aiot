package com.cityiot.guanxin.workOrder.vo;

import com.cityiot.guanxin.common.entity.BaseEntity;
import com.cityiot.guanxin.entity.SystemVariable;
import com.cityiot.guanxin.workOrder.entity.ERepairWorkOrder;
import com.cityiot.guanxin.workOrder.entity.EWorkOrderImages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepairWorkOrderVo extends BaseEntity {
    private ERepairWorkOrder objData;
    private List<EWorkOrderImages> workOrderImages;

    public ERepairWorkOrder getObjData() {
        return objData;
    }

    public void setObjData(ERepairWorkOrder objData) {
        this.objData = objData;
    }

    public List<EWorkOrderImages> getWorkOrderImages() {
        return workOrderImages;
    }

    public void setWorkOrderImages(List<EWorkOrderImages> workOrderImages) {
        this.workOrderImages = workOrderImages;
    }
}
