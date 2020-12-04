package com.cityiot.guanxin.workOrder.maintenance.entity.Vo;

import com.cityiot.guanxin.workOrder.maintenance.entity.MaintenanceAccessories;
import com.cityiot.guanxin.workOrder.maintenance.entity.MaintenanceRecord;

import java.util.List;

public class MaintenanceVo {


    private MaintenanceRecord maintenanceRecord;
    private List<MaintenanceAccessories> maintenancePictures;
    private List<MaintenanceAccessories> deleteMaintenancePictures;

    public MaintenanceRecord getMaintenanceRecord() {
        return maintenanceRecord;
    }

    public void setMaintenanceRecord(MaintenanceRecord maintenanceRecord) {
        this.maintenanceRecord = maintenanceRecord;
    }

    public List<MaintenanceAccessories> getMaintenancePictures() {
        return maintenancePictures;
    }

    public void setMaintenancePictures(List<MaintenanceAccessories> maintenancePictures) {
        this.maintenancePictures = maintenancePictures;
    }

    public List<MaintenanceAccessories> getDeleteMaintenancePictures() {
        return deleteMaintenancePictures;
    }

    public void setDeleteMaintenancePictures(List<MaintenanceAccessories> deleteMaintenancePictures) {
        this.deleteMaintenancePictures = deleteMaintenancePictures;
    }
}
