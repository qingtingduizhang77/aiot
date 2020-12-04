package com.cityiot.guanxin.user.service;

import com.cityiot.guanxin.permission.entity.Permission;
import com.cityiot.guanxin.user.entity.RolePermissionRelation;
import com.cityiot.guanxin.user.entity.vo.PermissionOfRoleVo;
import com.cityiot.guanxin.user.repository.RolePermissionRelationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import swallow.framework.exception.SwallowException;
import swallow.framework.service.BaseService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RolePermissionRelationService extends BaseService<RolePermissionRelationRepository,RolePermissionRelation> {
    // 日志对象
    private static final Logger log = LoggerFactory.getLogger(RolePermissionRelationService.class);
    /** 通过角色Id获取角色所有的权限
     * @param roleId
     * @return
     */
    public List<Permission> getAllRoleByUserId(long roleId) {
        return getRepsitory().getAllPermissionByRoleId(roleId);
    }

    /** 通过角色Id获取角色所有的权限Id
     * @param roleId
     * @return
     */
    public List<Long> getAllRoleIdsByUserId(long roleId) {
        return getRepsitory().getAllPermissionIdsByRoleId(roleId);
    }

    /** 通过角色Id获取关联所有的权限角色关系
     * @param roleId
     * @return
     */
    public List<RolePermissionRelation> getAllItemByRoleId(long roleId) {
    return getRepsitory().getAllItemByRoleId(roleId);
    }
    /**
     * 获取指定角色已经授权的权限项和角色权限关系
     * @param roleId
     * @return
     */
    @Transactional
    public List<PermissionOfRoleVo> getAllPermissionfRoleByRoleId(Long roleId) {
    return getRepsitory().getAllPermissionfRoleByRoleId(roleId);
    }

    /**
     * 批量给角色授权
     * @param items
     * @return
     * @throws SwallowException
     */
    @Transactional
    public boolean saveAllRolesRightItemAllocation(List<RolePermissionRelation> items) throws SwallowException {
        this.getRepsitory().saveAll(items);
        return true;
    }
}
