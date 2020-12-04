package com.cityiot.guanxin.branchLeader.service;

import com.cityiot.guanxin.area.entity.Area;
import com.cityiot.guanxin.area.entity.QArea;
import com.cityiot.guanxin.area.service.AreaService;
import com.cityiot.guanxin.branchLeader.entity.*;
import com.cityiot.guanxin.branchLeader.entity.vo.BranchLeaderVo;
import com.cityiot.guanxin.branchLeader.repository.BranchLeaderRepository;
import com.cityiot.guanxin.deviceGroup.entity.DeviceGroup;
import com.cityiot.guanxin.deviceGroup.entity.QDeviceGroup;
import com.cityiot.guanxin.deviceGroup.entity.QDeviceGroupRelation;
import com.cityiot.guanxin.deviceGroup.service.DeviceGroupService;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.deviceInformation.entity.QDeviceinformation;
import com.cityiot.guanxin.deviceInformation.service.DeviceinformationService;
import com.cityiot.guanxin.devicePermission.entity.DevPermissionToAreaCode;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swallow.framework.exception.SwallowException;
import swallow.framework.service.BaseService;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author guoyingzhao
 * 型号区域负责人配置表业务层
 */
@Service
public class BranchLeaderService extends BaseService<BranchLeaderRepository, BranchLeader> {
    private static final Logger log = LoggerFactory.getLogger(BranchLeaderService.class);
    @Autowired
    private BranchToAreaService branchToAreaService;
    @Autowired
    private BranchToDeviceModelService branchToDeviceModelService;
    @Autowired
    private BranchToDeviceGroupService branchToDeviceGroupService;
    @Autowired
    private DeviceinformationService deviceinformationService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private DeviceGroupService deviceGroupService;
    /**
     *新增区域负责人配置表
     *
     */
    @Transactional
    public BranchLeader insertItem01(BranchLeaderVo branchLeaderVo) throws SwallowException{
        //新增BranchLeader表
        BranchLeader branchLeader = new BranchLeader();
        branchLeader.setAreaName(branchLeaderVo.getAreaName());
        branchLeader.setDeviceModelName(branchLeaderVo.getDeviceModelName());
        branchLeader.setDeviceGroupName(branchLeaderVo.getDeviceGroupName());
        branchLeader.setOperatorOrCompanyManageId(branchLeaderVo.getOperatorOrCompanyManageId());
        branchLeader.setRemark(branchLeaderVo.getRemark());
        BranchLeader branchLeader1 = super.insertItem(branchLeader);
        Long id = branchLeader1.getId();
        //添加从表
        insertAreaAndDevice(id,branchLeaderVo.getAreaIds(),branchLeaderVo.getDeviceModelIds(), branchLeaderVo.getDeviceGroupIds());

        return branchLeader1;
    }

    /**
     * 删除指定id的区域配置表以及中间表
     * @param id
     * @return
     */
    //要增加事务才能执行删除操作
    @Transactional
    public long deleteItemById01(Long id) {
        //删除从表
        branchToAreaService.deleteItemByBranchId(id);
        branchToDeviceModelService.deleteItemByBranchId(id);
        branchToDeviceGroupService.deleteItemByBranchId(id);

        //删除主表
        return super.deleteItemById(id, (Predicate)null);
    }






    //查询指定branchId的区域负责人配置表
    public BranchLeaderVo getItemById01 (Long id){
        //查到配置表
        BranchLeader branchLeader = super.getItemById(id);

        //插入到返回对象中
        BranchLeaderVo branchLeaderVo = new BranchLeaderVo();
        branchLeaderVo.setAreaName(branchLeader.getAreaName());
        branchLeaderVo.setDeviceModelName(branchLeader.getDeviceModelName());
        branchLeaderVo.setOperatorOrCompanyManageId(branchLeader.getOperatorOrCompanyManageId());
        branchLeaderVo.setRemark(branchLeader.getRemark());
        branchLeaderVo.setLeaderName(branchLeader.getLeaderName());

        //查到对应区域
        branchLeaderVo.setAreaIds(branchToAreaService.getAllAreaIdsByBranchId(id));
        //查到对应设备型号
        branchLeaderVo.setDeviceModelIds(branchToDeviceModelService.getAllDeviceModelIdsByBranchId(id));
        //查到对应设备分组
        branchLeaderVo.setDeviceGroupIds(branchToDeviceGroupService.getAllDeviceGroupIdsByBranchId(id));
        return branchLeaderVo;
    }

    //更新区域负责人配置表
    @Transactional
    public BranchLeader updateItem01(BranchLeaderVo branchLeaderVo) {
        //更新主表
        BranchLeader branchLeader = super.getItemById(branchLeaderVo.getId());
        branchLeader.setAreaName(branchLeaderVo.getAreaName());
        branchLeader.setDeviceModelName(branchLeaderVo.getDeviceModelName());
        branchLeader.setDeviceGroupName(branchLeaderVo.getDeviceGroupName());
        branchLeader.setOperatorOrCompanyManageId(branchLeaderVo.getOperatorOrCompanyManageId());
        branchLeader.setRemark(branchLeaderVo.getRemark());
        super.updateItem(branchLeader);

        //更新从表
        Long id = branchLeaderVo.getId();
        //先删除后添加
        branchToAreaService.deleteItemByBranchId(id);
        branchToDeviceModelService.deleteItemByBranchId(id);
        branchToDeviceGroupService.deleteItemByBranchId(id);
        //添加
        insertAreaAndDevice(id,branchLeaderVo.getAreaIds(),branchLeaderVo.getDeviceModelIds(),branchLeaderVo.getDeviceGroupIds());
        return super.getItemById(id);

    }

    //新增从表操作
    private void insertAreaAndDevice (long branchId,Long[] areaIds,Long[] deviceModelIds,Long[] deviceGroupIds){
        //新增BranchToArea表
        if(null!=areaIds  && areaIds.length!=0) {
            for (Long areaId : areaIds) {
                BranchToArea BTA = new BranchToArea();
                BTA.setBranchId(branchId);
                BTA.setAreaId(areaId);
                branchToAreaService.insertItem(BTA);
            }
        }
        //新增BranchToDeviceModel表
        if(null!=deviceModelIds  && deviceModelIds.length!=0) {
            for (Long deviceModelId : deviceModelIds) {
                BranchToDeviceModel BTD = new BranchToDeviceModel();
                BTD.setBranchId(branchId);
                BTD.setDeviceModelId(deviceModelId);
                branchToDeviceModelService.insertItem(BTD);
            }
        }
        //新增BranchToDeviceGroup表
        if(null!=deviceGroupIds  && deviceGroupIds.length>0) {
            for (Long deviceGroupId : deviceGroupIds) {
                BranchToDeviceGroup BTG = new BranchToDeviceGroup();
                BTG.setBranchId(branchId);
                BTG.setDeviceGroupId(deviceGroupId);
                branchToDeviceGroupService.insertItem(BTG);
            }
        }
    }

    //传入deviceModelid,返回BranchLeader列表
    public PageListData<BranchLeader> getAllByDeviceModel(BasePageQueryBean queryBean,Long deviceModelId) {
        return this.getRepsitory().getAllItemPageByQuerybean(queryBean,
                query ->{
            return query.from(QBranchLeader.branchLeader,QBranchToDeviceModel.branchToDeviceModel)
                    .where(QBranchLeader.branchLeader.id.eq(QBranchToDeviceModel.branchToDeviceModel.branchId).and(QBranchToDeviceModel.branchToDeviceModel.deviceModelId.eq(deviceModelId)));
        });

    }

    // 获取负责人ID
    public Long getHandleId(long deviceId){
        try {
            Deviceinformation deviceinformation = deviceinformationService.getItemById(deviceId);
            if (deviceinformation == null) {
                return null;
            }
            // 设备所在区域
            Area area = areaService.getItemById(deviceinformation.getAreaId());

            List<Long> areaIds = new ArrayList<>();
            if (area != null) {
                // 查找本区域及父级区域
                List<Area> areas = areaService.getAllItems(query -> {
                    if (StringUtils.isNotEmpty(area.getParentCodeArr())) {
                        String[] areaCodes = area.getParentCodeArr().split(",");
                        Long[] areaCodesArr = new Long[areaCodes.length];
                        for (int i = 0; i < areaCodesArr.length; i++) {
                            areaCodesArr[i] = Long.valueOf(areaCodes[i]);
                        }
                        query.where(QArea.area.code.in(areaCodesArr).or(QArea.area.code.eq(area.getCode())));
                    }else {
                        query.where(QArea.area.code.eq(area.getCode()));
                    }
                    return query;
                });

                // 区域ID
                areaIds = areas.stream().map(Area::getId).distinct().collect(Collectors.toList());
            }


            QDeviceGroupRelation qDeviceGroupRelation = QDeviceGroupRelation.deviceGroupRelation;
            QDeviceGroup qDeviceGroup = QDeviceGroup.deviceGroup;

            // 查找设备所在分组
            List<DeviceGroup> groups = deviceGroupService.getAllItems(query -> {
                query.leftJoin(qDeviceGroupRelation)
                        .on(qDeviceGroupRelation.deviceGroupId.eq(qDeviceGroup.id))
                        .where(qDeviceGroupRelation.deviceId.eq(deviceinformation.getId()));
                return query;
            });

            // 自身分组及父级分组ID
            List<Long> groupIds = new ArrayList<>();
            if (groups.size() > 0) {
                for (DeviceGroup group : groups) {
                    String[] groupIdArr = group.getParentIdArr().replaceAll("'", "").split(",");
                    Long[] groupIdLongArr = new Long[groupIdArr.length];
                    for (int i = 0; i < groupIdLongArr.length; i++) {
                        groupIdLongArr[i] = Long.valueOf(groupIdArr[i]);
                    }
                    groupIds.add(group.getId());// 添加自身ID
                    groupIds.addAll(Arrays.asList(groupIdLongArr));// 添加父ID
                }
            }

            QBranchLeader qBranchLeader = QBranchLeader.branchLeader;
            QBranchToDeviceModel qBranchToDeviceModel = QBranchToDeviceModel.branchToDeviceModel;
            QBranchToArea qBranchToArea = QBranchToArea.branchToArea;
            QBranchToDeviceGroup qBranchToDeviceGroup = QBranchToDeviceGroup.branchToDeviceGroup;
            var a1 = qBranchToDeviceModel.deviceModelId.isNotNull()
                    .or(qBranchToArea.areaId.isNotNull())
                    .or(qBranchToDeviceGroup.deviceGroupId.isNotNull())
                    .and(qBranchToDeviceModel.deviceModelId.in(deviceinformation.getDeviceModelId()))
                    .and(qBranchToArea.areaId.in(areaIds))
                    .and(qBranchToDeviceGroup.deviceGroupId.in(groupIds));

            var a2 = qBranchToDeviceModel.deviceModelId.isNotNull()
                    .and(qBranchToDeviceModel.deviceModelId.in(deviceinformation.getDeviceModelId()))
                    .and(qBranchToArea.areaId.isNull())
                    .and(qBranchToDeviceGroup.deviceGroupId.isNull());

            var a3 = qBranchToDeviceModel.deviceModelId.isNull()
                    .and(qBranchToArea.areaId.isNotNull())
                    .and(qBranchToArea.areaId.in(areaIds))
                    .and(qBranchToDeviceGroup.deviceGroupId.isNull());

            var a4 = qBranchToDeviceModel.deviceModelId.isNull()
                    .and(qBranchToArea.areaId.isNull())
                    .and(qBranchToDeviceGroup.deviceGroupId.isNotNull())
                    .and(qBranchToDeviceGroup.deviceGroupId.in(groupIds));

            var a5 = qBranchToDeviceModel.deviceModelId.isNotNull()
                    .and(qBranchToDeviceModel.deviceModelId.in(deviceinformation.getDeviceModelId()))
                    .and(qBranchToArea.areaId.isNotNull())
                    .and(qBranchToArea.areaId.in(areaIds))
                    .and(qBranchToDeviceGroup.deviceGroupId.isNull());

            var a6 = qBranchToDeviceModel.deviceModelId.isNotNull()
                    .and(qBranchToDeviceModel.deviceModelId.in(deviceinformation.getDeviceModelId()))
                    .and(qBranchToArea.areaId.isNull())
                    .and(qBranchToDeviceGroup.deviceGroupId.isNotNull())
                    .and(qBranchToDeviceGroup.deviceGroupId.in(groupIds));

            var a7 = qBranchToDeviceModel.deviceModelId.isNull()
                    .and(qBranchToArea.areaId.isNotNull())
                    .and(qBranchToArea.areaId.in(areaIds))
                    .and(qBranchToDeviceGroup.deviceGroupId.isNotNull())
                    .and(qBranchToDeviceGroup.deviceGroupId.in(groupIds));

            List<BranchLeader> branchLeaders = this.getAllItems(query -> {
                query.leftJoin(qBranchToDeviceModel).on(qBranchToDeviceModel.branchId.eq(qBranchLeader.id))
                        .leftJoin(qBranchToArea).on(qBranchToArea.branchId.eq(qBranchLeader.id))
                        .leftJoin(qBranchToDeviceGroup).on(qBranchToDeviceGroup.branchId.eq(qBranchLeader.id))
                        .where(a1.or(a2).or(a3).or(a4).or(a5).or(a6).or(a7));
                return query;
            });

            if (branchLeaders != null && branchLeaders.size() > 0) {
                return branchLeaders.get(0).getOperatorOrCompanyManageId();
            }
        }catch (Exception e){
            log.warn("查询设备负责人报错：" + e.getMessage());
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
