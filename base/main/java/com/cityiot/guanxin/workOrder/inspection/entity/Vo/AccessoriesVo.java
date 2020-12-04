package com.cityiot.guanxin.workOrder.inspection.entity.Vo;

import com.cityiot.guanxin.workOrder.inspection.entity.InspectionRecord;
import com.cityiot.guanxin.workOrder.maintenance.entity.MaintenanceAccessories;

import java.util.List;

public class AccessoriesVo {

    private InspectionRecord inspectionRecord;

    private List<MaintenanceAccessories> patrolPictures;

    private List<MaintenanceAccessories> deletepatrolPictures;

    public InspectionRecord getInspectionRecord() {
        return inspectionRecord;
    }

    public void setInspectionRecord(InspectionRecord inspectionRecord) {
        this.inspectionRecord = inspectionRecord;
    }

    public List<MaintenanceAccessories> getPatrolPictures() {
        return patrolPictures;
    }

    public void setPatrolPictures(List<MaintenanceAccessories> patrolPictures) {
        this.patrolPictures = patrolPictures;
    }

    public List<MaintenanceAccessories> getDeletepatrolPictures() {
        return deletepatrolPictures;
    }

    public void setDeletepatrolPictures(List<MaintenanceAccessories> deletepatrolPictures) {
        this.deletepatrolPictures = deletepatrolPictures;
    }
}
