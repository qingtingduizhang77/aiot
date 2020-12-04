package com.cityiot.guanxin.branchLeader.service;

import com.cityiot.guanxin.area.entity.Area;
import com.cityiot.guanxin.area.entity.QArea;
import com.cityiot.guanxin.area.service.AreaService;
import com.cityiot.guanxin.branchLeader.entity.BranchToDeviceGroup;
import com.cityiot.guanxin.branchLeader.entity.BranchToDeviceModel;
import com.cityiot.guanxin.branchLeader.entity.QBranchToArea;
import com.cityiot.guanxin.branchLeader.entity.QBranchToDeviceGroup;
import com.cityiot.guanxin.branchLeader.repository.BranchToDeviceGroupRepository;
import com.cityiot.guanxin.deviceGroup.entity.DeviceGroup;
import com.cityiot.guanxin.deviceGroup.entity.DeviceGroupRelation;
import com.cityiot.guanxin.deviceGroup.entity.QDeviceGroup;
import com.cityiot.guanxin.deviceGroup.entity.QDeviceGroupRelation;
import com.cityiot.guanxin.deviceGroup.service.DeviceGroupRelationService;
import com.cityiot.guanxin.deviceGroup.service.DeviceGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhengjc
 * 设备分组与负责人配置表
 */
@Service
public class BranchToDeviceGroupService extends BaseService<BranchToDeviceGroupRepository, BranchToDeviceGroup> {
    private static final Logger log = LoggerFactory.getLogger(BranchToDeviceGroupService.class);

    @Autowired
    private DeviceGroupService deviceGroupService;

    @Autowired
    private DeviceGroupRelationService deviceGroupRelationService;

    //删除指定branchId的对象
    public void deleteItemByBranchId(Long branchId) {
        QBranchToDeviceGroup branchToDeviceGroup = QBranchToDeviceGroup.branchToDeviceGroup;
        getRepsitory()
                .deleteEntityByColumns(query -> {
                    return query.where(
                            branchToDeviceGroup.branchId.eq(branchId));
                });

    }

    public Long[] getAllDeviceGroupIdsByBranchId(Long branchId) {
        //条件查询
        List<BranchToDeviceGroup> entitys = getRepsitory().findEntitysByColumns(query -> {
            return query.where(QBranchToDeviceGroup.branchToDeviceGroup.branchId.eq(branchId));
        });
        //取出区域集合
        List<Long> modelList = entitys.stream().map(BranchToDeviceGroup::getDeviceGroupId).collect(Collectors.toList());
        //集合转成数组
        Long[] re = modelList.toArray(new Long[modelList.size()]);
        return re;

    }

    public Long[] getDeviceIdsByBranchId(Long branchId){

        QBranchToDeviceGroup qBranchToDeviceGroup = QBranchToDeviceGroup.branchToDeviceGroup;
        List<DeviceGroup> deviceGroups = deviceGroupService.getAllItems(query ->
                query.leftJoin(qBranchToDeviceGroup)
                        .on(QDeviceGroup.deviceGroup.id.eq(qBranchToDeviceGroup.deviceGroupId))
                        .where(qBranchToDeviceGroup.branchId.eq(branchId))
        );

        if (deviceGroups != null && deviceGroups.size() > 0) {

            List<Long> groupIds = new ArrayList<>();

            for (DeviceGroup deviceGroup : deviceGroups) {

                groupIds.add(deviceGroup.getId());// 添加负责人管理设备分组ID

                List<DeviceGroup> childDeviceGroup = deviceGroupService.getDeviceGroupListByParentId(deviceGroup.getId());
                if (childDeviceGroup != null && childDeviceGroup.size() > 0) {
                    for (DeviceGroup child : childDeviceGroup) {
                        groupIds.add(child.getId());// 添加负责人管理的下层设备分组ID
                    }
                }
            }

            //查询所有分组下的设备ID
            if (groupIds.size() > 0) {
                List<DeviceGroupRelation> list = deviceGroupRelationService.getAllItems(
                        query -> query.where(QDeviceGroupRelation.deviceGroupRelation.deviceGroupId
                                .in(groupIds.toArray(new Long[groupIds.size()]))));

                List<Long> deviceIds = list.stream().map(DeviceGroupRelation::getDeviceId).distinct().collect(Collectors.toList());

                //集合转成数组
                Long[] re = deviceIds.toArray(new Long[deviceIds.size()]);

                return re;
            }
        }

        return null;
    }
}
