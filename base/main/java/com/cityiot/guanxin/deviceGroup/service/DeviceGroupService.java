package com.cityiot.guanxin.deviceGroup.service;

import com.cityiot.guanxin.deviceGroup.entity.DeviceGroup;
import com.cityiot.guanxin.deviceGroup.entity.QDeviceGroup;
import com.cityiot.guanxin.deviceGroup.repository.DeviceGroupRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swallow.framework.service.BaseService;

import java.util.List;

@Service
public class DeviceGroupService extends BaseService<DeviceGroupRepository, DeviceGroup> {

    @Autowired
    private DeviceGroupRelationService relationService;

    @Transactional
    public void deleteItemById(long []ids) throws Exception{
        for(var id:ids){
            if (this.getDeviceGroupListByParentId(id).size() > 0) {
                throw new Exception("有子级分组，不予以删除！");
            }
            this.deleteItemById(id); //删除设备分组
            relationService.deleteItemByGroupId(id);// 删除设备关联分组数据
        }
    }

    public List<DeviceGroup> getDeviceGroupListByParentId(long id){
        QDeviceGroup qDeviceGroup = QDeviceGroup.deviceGroup;
        return this.getAllItems(
                query -> query.where(qDeviceGroup.parentId.eq(id).or(qDeviceGroup.parentIdArr.like("%'"+id+"',%")))
        );
    }


    public void checkIsGtLevel(DeviceGroup item) throws Exception{
        // 设备分组属于第几级别
        if (item.getParentId() > 0) {
            DeviceGroup group = this.getItemById(item.getParentId());
            if (group != null){
                item.setLevel(group.getLevel() + 1);
                if (StringUtils.isNotEmpty(group.getParentIdArr())) {
                    item.setParentIdArr("" + group.getParentIdArr() + ",'" + group.getId() + "'");
                }else{
                    item.setParentIdArr("'" + group.getId()+ "'");
                }
            }
        }else {
            item.setLevel(1);
            item.setParentIdArr(null);
        }
        if (item.getLevel() > 9) {
            throw new Exception("分组层级最多为9！");
        }
    }
}
