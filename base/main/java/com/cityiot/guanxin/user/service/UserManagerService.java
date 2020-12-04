package com.cityiot.guanxin.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cityiot.guanxin.area.entity.Area;
import com.cityiot.guanxin.permission.entity.Permission;
import com.cityiot.guanxin.role.entity.Role;
import com.cityiot.guanxin.user.entity.Accountinfo;
import com.cityiot.guanxin.user.entity.User;
import com.cityiot.guanxin.user.entity.UserAreaRelation;
import com.cityiot.guanxin.user.entity.UserRoleRelation;
import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.user.entity.dto.UserInfo;

import swallow.framework.exception.SwallowException;


@Service
public class UserManagerService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleRelationService userRoleRelationService;
    @Autowired
    private UserAreaRelationService userAreaRelationService;
    @Autowired
	private UserviewService userviewService;
    /**
     * 根据当前用户获取用户资料
     * @return
     */
    public UserInfo getUserInfoByUserId() {
        UserInfo userInfo = new UserInfo();
        Subject subject = SecurityUtils.getSubject();
       
        Userview user = (Userview)subject.getPrincipals().getPrimaryPrincipal();
       // Userview userview= userviewService.getItemById(user.getId());
        BeanUtils.copyProperties(user,userInfo);
        //userInfo.setPassword(null);
        boolean isSuperAdmin = false;
        List<Role> roleList = userRoleRelationService.getAllRoleByUserId((user.getId()));
        List<Permission> permissionList = userService.getAllPermissionByUserId(user.getId());
        List<String> roleNameList = roleList
                .stream()
                .map(item -> {
                    return item.getName();
        })
                .collect(Collectors.toList());
        for(String name:roleNameList) {
            if(name!=null && name.toUpperCase().equals("ADMIN")){
                isSuperAdmin = true;
            }
        }
        List<String> permissionCodeList = permissionList
                .stream()
                .map(item -> {
                    return item.getCode();
                })
                .collect(Collectors.toList());
        if(isSuperAdmin){
            permissionCodeList.add("SUPERADMIN");
        }
        userInfo.setRoleList(roleNameList);
        userInfo.setPermissionList(permissionCodeList);
        return userInfo;
    }

    /** 通过用户Id获取用户所有的角色
     * @param userId
     * @return
     */
    public List<Role> getAllRoleByUserId(long userId) {
        return userRoleRelationService.getAllRoleByUserId(userId);
    }

    /** 授予用户角色
     * @param userId
     * @param roleIds
     */
    @Transactional
    public void giveUserRole(long userId, Long[] roleIds) {
        List<Long> userRoleIds = userRoleRelationService.getAllRoleIdsByUserId(userId);
        for(long roleId: roleIds) {
            if(userRoleIds.contains(roleId)){
                throw new SwallowException("用户已经授予该角色");
            }
            UserRoleRelation userRoleRelation = new UserRoleRelation();
            userRoleRelation.setUserId(userId);
            userRoleRelation.setRoleId(roleId);
            userRoleRelationService.insertItem(userRoleRelation);
        }
    }

    /** 取消授予某个用户角色
     */
    public void giveUpUserRole(long userId,Long[] roleIds) {
        for (long roleId:roleIds) {
            userRoleRelationService.deleteEntityByUserIdAndRoleId(userId,roleId);
        }
    }

    public void allocateUserRole (long userId,Long[] readyGiveUpRoleIds,Long[] readyGiveRoleIds) {
       // User user = userService.getItemById(userId);
        Userview user= userviewService.getItemById(userId);
        if(null==user) {
            throw new SwallowException("用户不存在，非法操作");
        }
        if(null!=readyGiveUpRoleIds){
            giveUpUserRole(userId,readyGiveUpRoleIds);
        }
        if(null!=readyGiveRoleIds) {
            giveUserRole(userId,readyGiveRoleIds);
        }
    }

    /** 根据用户Id获取用户的所有对应区域关系
     * @param userId
     * @return
     */
    public List<UserAreaRelation> getAllUserAreaRelationByUserId(long userId) {
        return userAreaRelationService.getAllUserAreaRelationByUserId(userId);
    }

    /** 根据用户Id获取用户的所有对应区域
     * @param userId
     * @return
     */
    public List<Area> getAllUserAreaByUserId(long userId) {
        return userAreaRelationService.getAllAreaByUserId(userId);
    }

    /** 授予用户区域
     * @param userId
     * @param areaIds
     */
    @Transactional
    public void giveUserArea(long userId, Long[] areaIds) {
        List<Long> userAreaIds = userAreaRelationService.getAllAreaIdsByUserId(userId);
        for(long AreaId: areaIds) {
            if(userAreaIds.contains(AreaId)){
                throw new SwallowException("用户已经授予该区域");
            }
            UserAreaRelation userAreaRelation = new UserAreaRelation();
            userAreaRelation.setUserId(userId);
            userAreaRelation.setAreaId(AreaId);
            userAreaRelationService.insertItem(userAreaRelation);
        }
    }

    /** 取消授予某个用户区域
     */
    public void giveUpUserArea(long userId,Long[] areaIds) {
        for (long areaId:areaIds) {
            userAreaRelationService.deleteEntityByUserIdAndAreaId(userId,areaId);
        }
    }

    public void allocateUserArea (long userId,Long[] readyGiveUpAreaIds,Long[] readyGiveAreaIds) {
       // User user = userService.getItemById(userId);
        Userview user= userviewService.getItemById(userId);
        if(null==user) {
            throw new SwallowException("用户不存在，非法操作");
        }
        if(null!=readyGiveUpAreaIds){
            giveUpUserArea(userId,readyGiveUpAreaIds);
        }
        if(null!=readyGiveAreaIds) {
            giveUserArea(userId,readyGiveAreaIds);
        }
    }
}
