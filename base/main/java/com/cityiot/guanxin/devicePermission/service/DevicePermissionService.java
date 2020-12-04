package com.cityiot.guanxin.devicePermission.service;

import com.cityiot.guanxin.area.entity.Area;
import com.cityiot.guanxin.area.entity.QArea;
import com.cityiot.guanxin.area.service.AreaService;
import com.cityiot.guanxin.deviceGroup.entity.DeviceGroup;
import com.cityiot.guanxin.deviceGroup.entity.DeviceGroupRelation;
import com.cityiot.guanxin.deviceGroup.entity.QDeviceGroup;
import com.cityiot.guanxin.deviceGroup.entity.QDeviceGroupRelation;
import com.cityiot.guanxin.deviceGroup.service.DeviceGroupRelationService;
import com.cityiot.guanxin.deviceGroup.service.DeviceGroupService;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.deviceInformation.entity.QDeviceModel;
import com.cityiot.guanxin.deviceInformation.entity.QDeviceinformation;
import com.cityiot.guanxin.deviceInformation.service.DeviceinformationService;
import com.cityiot.guanxin.devicePermission.entity.*;
import com.cityiot.guanxin.devicePermission.repository.DevicePermissionRepository;
import com.cityiot.guanxin.role.entity.Role;
import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.user.service.UserRoleRelationService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swallow.framework.exception.SwallowException;
import swallow.framework.service.BaseService;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.BaseQueryBean;
import swallow.framework.web.PageListData;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DevicePermissionService extends BaseService<DevicePermissionRepository, DevicePermission> {

    private static final Logger log = LoggerFactory.getLogger(DevicePermissionService.class);

    @Autowired
    private DevPermissionToAreaCodeService areaCodeService;
    @Autowired
    private DevPermissionToGroupService groupService;
    @Autowired
    private DevPermissionToModelService modelService;
    @Autowired
    private DevPermissionToViewRoleService viewRoleService;
    @Autowired
    private DevPermissionToOperRoleService operRoleService;
    @Autowired
    private UserRoleRelationService userRoleRelationService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private DeviceGroupService deviceGroupService;
    @Autowired
    private DeviceGroupRelationService deviceGroupRelationService;
    @Autowired
    private DeviceinformationService deviceinformationService;

    public DevicePermission insertItem(DevicePermission item){
        DevicePermission entity = super.insertItem(item);
        insertOtherInfo(entity);
        return entity;
    }

    // 添加关联表数据
    private void insertOtherInfo(DevicePermission item) throws SwallowException{

        // 设备型号
        if (StringUtils.isNotEmpty(item.getDeviceModelId())) {
            String[] deviceModelIds = item.getDeviceModelId().split(",");
            for (var i =0; i<deviceModelIds.length;i++) {
                DevPermissionToModel model = new DevPermissionToModel();
                model.setDeviceModelId(Long.valueOf(deviceModelIds[i]));
                model.setDevicePermissionId(item.getId());
                modelService.insertItem(model);
            }
        }

        // 设备分组
        if (StringUtils.isNotEmpty(item.getDeviceGroupId())) {
            String[] deviceGroupIds = item.getDeviceGroupId().split(",");
            for (var i =0; i<deviceGroupIds.length;i++) {
                DevPermissionToGroup model = new DevPermissionToGroup();
                model.setDeviceGroupId(Long.valueOf(deviceGroupIds[i]));
                model.setDevicePermissionId(item.getId());
                groupService.insertItem(model);
            }
        }

        // 区域编号
        if (StringUtils.isNotEmpty(item.getAreaCode())) {
            String[] areaCodes = item.getAreaCode().split(",");
            String[] areaIds = item.getAreaId().split(",");
            if (areaCodes.length == areaIds.length) {
                for (var i =0; i<areaCodes.length;i++) {
                    DevPermissionToAreaCode model = new DevPermissionToAreaCode();
                    model.setAreaId(Long.valueOf(areaIds[i]));
                    model.setAreaCode(Long.valueOf(areaCodes[i]));
                    model.setDevicePermissionId(item.getId());
                    areaCodeService.insertItem(model);
                }
            }else{
                log.error("区域编码数据有误！");
                throw new SwallowException("区域编码数据有误！");
            }
        }

        // 可查看角色
        if (StringUtils.isNotEmpty(item.getViewRoleId())) {
            String[] viewRoleIds = item.getViewRoleId().split(",");
            for (var i =0; i<viewRoleIds.length;i++) {
                DevPermissionToViewRole model = new DevPermissionToViewRole();
                model.setViewRoleId(Long.valueOf(viewRoleIds[i]));
                model.setDevicePermissionId(item.getId());
                viewRoleService.insertItem(model);
            }
        }

        // 可操作角色
        if (StringUtils.isNotEmpty(item.getOperRoleId())) {
            String[] operRoleIds = item.getOperRoleId().split(",");
            for (var i =0; i<operRoleIds.length;i++) {
                DevPermissionToOperRole model = new DevPermissionToOperRole();
                model.setOperRoleId(Long.valueOf(operRoleIds[i]));
                model.setDevicePermissionId(item.getId());
                operRoleService.insertItem(model);
            }
        }
    }

    @Transactional
    public void deleteItemById(long []ids){
        for(var id:ids){
            super.deleteItemById(id); //删除设备权限
            deleteItemByPermissionId(id);//删除关联表
        }
    }

    @Transactional
    public void deleteItemByPermissionId(long id){
        modelService.deleteItemByPermissionId(id);
        groupService.deleteItemByPermissionId(id);
        areaCodeService.deleteItemByPermissionId(id);
        viewRoleService.deleteItemByPermissionId(id);
        operRoleService.deleteItemByPermissionId(id);
    }

    @Transactional
    public DevicePermission updateItem(DevicePermission item){
        deleteItemByPermissionId(item.getId());//先删除关联表
        insertOtherInfo(item);// 新增关联表数据
        return super.updateItem(item);
    }

    public Map<String,List<Long>> getDeviceInfo(String type) throws Exception{
        Map<String,List<Long>> map = new HashMap<>();
        List<Long> modelIds = new ArrayList<>();
        List<Long> areaCodes = new ArrayList<>();
        List<Long> deviceIds = new ArrayList<>();

        // 当前登录用户
        Userview userview = (Userview) SecurityUtils.getSubject().getPrincipal();
        // 当前用户角色
        List<Role> roles = userRoleRelationService.getAllRoleByUserId(userview.getId());

        if (roles == null || roles.size() == 0) {
            log.warn("当前登录用户无角色，不可查看及操作设备");
            return null;
        }

        List<String> roleCodes = roles.stream().map(Role::getCode).distinct().collect(Collectors.toList());

        if (roleCodes.contains("1")) {
            map.put("admin", new ArrayList<>(1));
            return map;
        }

        List<Long> roleIds = roles.stream().map(Role::getId).distinct().collect(Collectors.toList());

        List<DevicePermission> permissions;
        if ("view".equals(type)) { // 可查看
            QDevPermissionToViewRole qDevPermissionToViewRole = QDevPermissionToViewRole.devPermissionToViewRole;

            // 设备权限
            permissions = this.getAllItems(query -> query
                    .leftJoin(qDevPermissionToViewRole)
                    .on(qDevPermissionToViewRole.devicePermissionId.eq(QDevicePermission.devicePermission.id))
                    .where(qDevPermissionToViewRole.viewRoleId.in(roleIds)));
        }else { // 可操作
            QDevPermissionToOperRole qDevPermissionToOperRole = QDevPermissionToOperRole.devPermissionToOperRole;

            // 设备权限
            permissions = this.getAllItems(query -> query
                    .leftJoin(qDevPermissionToOperRole)
                    .on(qDevPermissionToOperRole.devicePermissionId.eq(QDevicePermission.devicePermission.id))
                    .where(qDevPermissionToOperRole.operRoleId.in(roleIds)));
        }

        if (permissions != null && permissions.size() > 0) {
            // 设备权限ID
            List<Long> ids = permissions.stream().map(DevicePermission::getId).distinct().collect(Collectors.toList());

            QDevPermissionToModel qDevPermissionToModel = QDevPermissionToModel.devPermissionToModel;

            // 权限设备关联表
            List<DevPermissionToModel> models = modelService.getAllItems(query -> query
                    .leftJoin(QDeviceModel.deviceModel1)
                    .on(QDeviceModel.deviceModel1.id.eq(qDevPermissionToModel.deviceModelId))
                    .where(qDevPermissionToModel.devicePermissionId.in(ids))
            );

            // 设备型号ID
            modelIds = models.stream().map(DevPermissionToModel::getDeviceModelId).distinct().collect(Collectors.toList());

            QDevPermissionToAreaCode qDevPermissionToAreaCode = QDevPermissionToAreaCode.devPermissionToAreaCode;

            // 设备权限区域关联表
            List<DevPermissionToAreaCode> areas = areaCodeService.getAllItems(query ->
                    query.leftJoin(QArea.area)
                            .on(QArea.area.code.eq(qDevPermissionToAreaCode.areaCode))
                            .where(qDevPermissionToAreaCode.devicePermissionId.in(ids))
            );

            // 设备权限关联的区域编码
            areaCodes = areas.stream().map(DevPermissionToAreaCode::getAreaCode).distinct().collect(Collectors.toList());

            if (areas.size() > 0) {
                for (DevPermissionToAreaCode area : areas) {
                    // 查询下属区域
                    List<Area> childAreas = areaService.getAllItems(query ->
                            query.where(QArea.area.parentId.eq(area.getAreaId())
                                    .or(QArea.area.parentCodeArr.like("%"+area.getAreaCode()+",%"))));
                    if (childAreas != null && childAreas.size() > 0) {
                        for (Area child : childAreas) {
                            areaCodes.add(child.getCode());// 添加下属区域的编码
                        }
                    }
                }
            }

            QDevPermissionToGroup qDevPermissionToGroup = QDevPermissionToGroup.devPermissionToGroup;

            // 设备权限设备分组关联表
            List<DevPermissionToGroup> groups = groupService.getAllItems(query ->
                    query.leftJoin(QDeviceGroup.deviceGroup)
                            .on(QDeviceGroup.deviceGroup.id.eq(qDevPermissionToGroup.deviceGroupId))
                            .where(qDevPermissionToGroup.devicePermissionId.in(ids))
            );

            List<Long> groupIds = groups.stream().map(DevPermissionToGroup::getDeviceGroupId).distinct().collect(Collectors.toList());

            if (groups.size() > 0) {

                for (DevPermissionToGroup group : groups) {
                    // 查询下层分组
                    List<DeviceGroup> childDeviceGroup = deviceGroupService.getDeviceGroupListByParentId(group.getDeviceGroupId());
                    if (childDeviceGroup != null && childDeviceGroup.size() > 0) {
                        for (DeviceGroup child : childDeviceGroup) {
                            groupIds.add(child.getId());// 添加下层设备分组ID
                        }
                    }
                }

                //查询所有分组下的设备ID
                if (groupIds.size() > 0) {
                    List<DeviceGroupRelation> list = deviceGroupRelationService.getAllItems(
                            query -> query.where(QDeviceGroupRelation.deviceGroupRelation.deviceGroupId
                                    .in(groupIds)));

                    // 设备分组关联的所有设备ID
                    deviceIds = list.stream().map(DeviceGroupRelation::getDeviceId).distinct().collect(Collectors.toList());

                }
            }
        }else {
            log.warn("无设备权限有关配置");
            return null;
        }
        // 条件都为空时，可查看和操作的列表都为空
        if (modelIds.size() == 0 && areaCodes.size() == 0 && deviceIds.size() == 0) {
            return null;
        }
        map.put("modelIds",modelIds);
        map.put("areaCodes",areaCodes);
        map.put("deviceIds",deviceIds);
        return map;
    }

    // 获取当前用户角色下有（查看）权限的设备列表
    /**
     * @param areaId 区域Id
     * @param groupId 分组Id
     * @param deviceTypeIds 设备类型ID数组
     * @param deviceModelIds 设备模型ID数组
     * @return 满足条件的且用户有权限查看的设备信息分页列表
     */
    public PageListData<Deviceinformation> getDevicePageList(BasePageQueryBean queryBean, Long []deviceTypeIds, Long areaId, Long groupId, Long []deviceModelIds) throws Exception{

        Map<String,List<Long>> map = getDeviceInfo("view");

        if (map == null) {
            log.info("没有可查看的设备列表");
            return null;
        }

        List<Long> areaCodes = this.getChildAreaCodeById(areaId);// 查询区域和子级编码
        List<Long> deviceIds = this.getDeviceIdByGroupId(groupId);// 查询设备分组和自己下级分组所有设备
        List<Long> modelIds = new ArrayList<>();//设备模型
        if(deviceModelIds!= null && deviceModelIds.length > 0){
            modelIds = Stream.of(deviceModelIds).collect(Collectors.toList());
        }

        if (map.containsKey("admin")) { //判断是否有管理员权限
            return this.getDevicePageList(modelIds, deviceTypeIds, areaCodes, deviceIds, queryBean);
        }

        // 合并去重
        //List<Long> disAreaCodes = Stream.of(areaCodes, map.get("areaCodes")).flatMap(Collection::stream).distinct().collect(Collectors.toList());
        //List<Long> disDeviceIds = Stream.of(deviceIds, map.get("deviceIds")).flatMap(Collection::stream).distinct().collect(Collectors.toList());

        List<Long> disAreaCodes = map.get("areaCodes");
        List<Long> disDeviceIds = map.get("deviceIds");
        List<Long> disModelIds = map.get("modelIds");
        //求交集
        if(areaId!= null){
            disAreaCodes.retainAll(areaCodes);//区域编码
        }
        if(groupId!= null){
            disDeviceIds.retainAll(deviceIds);//设备分组
        }
        if(deviceModelIds!= null && deviceModelIds.length > 0){
            disModelIds.retainAll(modelIds);//设备模型
        }
        return getDevicePageList(disModelIds, deviceTypeIds, disAreaCodes, disDeviceIds, queryBean);
    }

    public PageListData<Deviceinformation> getDevicePageList(List<Long> modelIds,Long []deviceTypeIds, List<Long> areaCodes, List<Long> deviceIds,
                                                             BasePageQueryBean queryBean){
        QDeviceinformation qDeviceinformation = QDeviceinformation.deviceinformation;
        return deviceinformationService.getAllItemPageByQuerybean(queryBean, query -> {
            if (deviceTypeIds!= null && deviceTypeIds.length > 0){
                query.where(qDeviceinformation.deviceTypeId.in(deviceTypeIds));
            }
            if (modelIds != null && modelIds.size() > 0) {
                query.where(qDeviceinformation.deviceModelId.in(modelIds));
            }
            if (areaCodes != null && areaCodes.size() > 0) {
                query.where(qDeviceinformation.areaCode.in(areaCodes));
            }
            if (deviceIds != null && deviceIds.size() > 0) {
                query.where(qDeviceinformation.id.in(deviceIds));
            }
            return query;
        });
    }

    // 获取当前用户角色下有（查看）权限的设备列表
    public List<Deviceinformation> getDeviceList(BaseQueryBean queryBean) throws Exception{

        Map<String,List<Long>> map = getDeviceInfo("view");

        if (map == null) {
            log.info("没有可查看的设备列表");
            return null;
        }else {
            if (map.containsKey("admin")) { //判断是否有管理员权限
                return this.getAdminDeviceList(queryBean);
            }
        }

        return getDeviceList(map.get("modelIds"), map.get("areaCodes"), map.get("deviceIds"), queryBean);
    }

    public List<Deviceinformation> getDeviceList(List<Long> modelIds, List<Long> areaCodes, List<Long> deviceIds,
                                                 BaseQueryBean queryBean){
        //设备列表
        List<Deviceinformation> deviceinformationList = deviceinformationService.getAllItemByQuerybean(queryBean,
                query ->{
            if (modelIds != null && modelIds.size() > 0) {
                query.where(QDeviceinformation.deviceinformation.deviceModelId.in(modelIds));
            }
            if (areaCodes != null && areaCodes.size() > 0) {
                query.where(QDeviceinformation.deviceinformation.areaCode.in(areaCodes));
            }
            if (deviceIds != null && deviceIds.size() > 0) {
                query.where(QDeviceinformation.deviceinformation.id.in(deviceIds));
            }
            return query;
        });
        return deviceinformationList;
    }

    // 获取当前用户角色下有（操作）权限的设备列表
    public List<Deviceinformation> getOperDeviceList() throws Exception{

        Map<String,List<Long>> map = getDeviceInfo("oper");

        if (map == null) {
            log.info("没有可操作的设备列表");
            return null;
        }else {
            if (map.containsKey("admin")) { //判断是否有管理员权限
                return this.getAdminDeviceList(null);
            }
        }

        return getDeviceList(map.get("modelIds"), map.get("areaCodes"), map.get("deviceIds"), null);
    }

    // 校验是否有设备的操作权限
    public boolean isOperationDevice(Long deviceId) throws Exception{
        List<Deviceinformation> deviceList = this.getOperDeviceList();

        if (deviceList != null && deviceList.size() > 0) {
            List<Long> deviceIds = deviceList.stream().map(Deviceinformation::getId).distinct().collect(Collectors.toList());

            if (deviceIds.contains(deviceId)){
                return true;
            }
        }
        return false;
    }

    public PageListData<Deviceinformation> getAdminDevicePageList(BasePageQueryBean queryBean){
        return deviceinformationService.getAllItemPageByQuerybean(queryBean,query -> query);
    }

    public List<Deviceinformation> getAdminDeviceList(BaseQueryBean queryBean){
        return deviceinformationService.getAllItemByQuerybean(queryBean,query -> query);
    }

    // 查询区域和子级编码
    private List<Long> getChildAreaCodeById(Long id){
        List<Long> areaCodes = new ArrayList<>();
        if (id != null) {
            Area area = areaService.getItemById(id);
            if (area != null) { // 查询下属区域
                areaCodes.add(area.getCode());
                List<Area> childAreas = areaService.getAllItems(query ->
                        query.where(QArea.area.parentId.eq(area.getId())
                                .or(QArea.area.parentCodeArr.like("%"+area.getCode()+",%"))));
                if (childAreas != null && childAreas.size() > 0) {
                    for (Area child : childAreas) {
                        areaCodes.add(child.getCode());// 添加下属区域的编码
                    }
                }
            }
        }
        return areaCodes;
    }

    // 查询设备分组和自己下级分组所有设备
    private List<Long> getDeviceIdByGroupId(Long groupId){
        List<Long> deviceIds = new ArrayList<>();
        List<Long> groupIds = new ArrayList<>();
        if (groupId != null) {
            DeviceGroup deviceGroup = deviceGroupService.getItemById(groupId);

            if (deviceGroup != null) {// 查询下层分组
                groupIds.add(deviceGroup.getId());

                List<DeviceGroup> childDeviceGroup = deviceGroupService.getDeviceGroupListByParentId(deviceGroup.getId());
                if (childDeviceGroup != null && childDeviceGroup.size() > 0) {
                    for (DeviceGroup child : childDeviceGroup) {
                        groupIds.add(child.getId());// 添加下层设备分组ID
                    }
                }

                //查询所有分组下的设备ID
                if (groupIds.size() > 0) {
                    List<DeviceGroupRelation> list = deviceGroupRelationService.getAllItems(
                            query -> query.where(QDeviceGroupRelation.deviceGroupRelation.deviceGroupId
                                    .in(groupIds)));

                    // 设备分组关联的所有设备ID
                    deviceIds = list.stream().map(DeviceGroupRelation::getDeviceId).distinct().collect(Collectors.toList());

                }
            }
        }
        return deviceIds;
    }

    /**
     * @param areaId 区域Id
     * @param groupId 分组Id
     * @param deviceTypeIds 设备类型ID数组
     * @param deviceModelIds 设备模型ID数组
     * @return 满足条件的且用户有权限查看的设备信息列表
     */
    public List<Deviceinformation> queryDeviceList(BaseQueryBean queryBean,Long []deviceTypeIds, Long areaId, Long groupId, Long []deviceModelIds) throws Exception{

        Map<String,List<Long>> map = getDeviceInfo("view");
        if (map == null) {
            log.info("没有可查看的设备列表");
            return null;
        }
        List<Long> areaCodes = this.getChildAreaCodeById(areaId);// 查询区域和子级编码
        List<Long> deviceIds = this.getDeviceIdByGroupId(groupId);// 查询设备分组和自己下级分组所有设备
        List<Long> modelIds = new ArrayList<>();//设备模型
        if(deviceModelIds!= null && deviceModelIds.length > 0){
            modelIds = Stream.of(deviceModelIds).collect(Collectors.toList());
        }
        if (map.containsKey("admin")) { //判断是否有管理员权限
            return this.getDeviceList(queryBean, deviceTypeIds, modelIds, areaCodes, deviceIds);
        }

        // 合并去重
        //List<Long> disAreaCodes = Stream.of(areaCodes, map.get("areaCodes")).flatMap(Collection::stream).distinct().collect(Collectors.toList());
        //List<Long> disDeviceIds = Stream.of(deviceIds, map.get("deviceIds")).flatMap(Collection::stream).distinct().collect(Collectors.toList());
        List<Long> disAreaCodes = map.get("areaCodes");
        List<Long> disDeviceIds = map.get("deviceIds");
        List<Long> disModelIds = map.get("modelIds");
        //求交集
        if(areaId!= null){
            disAreaCodes.retainAll(areaCodes);//区域编码
        }
        if(groupId!= null){
            disDeviceIds.retainAll(deviceIds);//设备分组
        }
        if(deviceModelIds!= null && deviceModelIds.length > 0){
            disModelIds.retainAll(modelIds);//设备模型
        }

        return this.getDeviceList(queryBean, deviceTypeIds, disModelIds, disAreaCodes, disDeviceIds);
    }

    private List<Deviceinformation> getDeviceList(BaseQueryBean queryBean,Long []deviceTypeIds,List<Long> modelIds, List<Long> areaCodes, List<Long> deviceIds){
        QDeviceinformation qDeviceinformation = QDeviceinformation.deviceinformation;
        return deviceinformationService.getAllItemByQuerybean(queryBean, query -> {
            if (deviceTypeIds!= null && deviceTypeIds.length > 0){
                query.where(qDeviceinformation.deviceTypeId.in(deviceTypeIds));
            }
            if (modelIds != null && modelIds.size() > 0) {
                query.where(qDeviceinformation.deviceModelId.in(modelIds));
            }
            if (areaCodes != null && areaCodes.size() > 0) {
                query.where(qDeviceinformation.areaCode.in(areaCodes));
            }
            if (deviceIds != null && deviceIds.size() > 0) {
                query.where(qDeviceinformation.id.in(deviceIds));
            }
            return query;
        });
    }
}
