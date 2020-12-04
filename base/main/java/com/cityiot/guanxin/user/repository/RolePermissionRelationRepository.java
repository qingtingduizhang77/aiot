package com.cityiot.guanxin.user.repository;

import com.cityiot.guanxin.common.repository.BaseRepository;
import com.cityiot.guanxin.permission.entity.Permission;
import com.cityiot.guanxin.permission.entity.QPermission;
import com.cityiot.guanxin.user.entity.QRolePermissionRelation;
import com.cityiot.guanxin.user.entity.RolePermissionRelation;
import com.cityiot.guanxin.user.entity.vo.PermissionOfRoleVo;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Projections;
import org.springframework.stereotype.Service;
import swallow.framework.exception.SwallowException;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RolePermissionRelationRepository extends BaseRepository<RolePermissionRelation> {
    

    /** 通过角色Id获取关联所有的权限
     * @param roleId
     * @return
     */
    public List<Permission> getAllPermissionByRoleId(long roleId) {
        QRolePermissionRelation qRolePermissionRelation = QRolePermissionRelation.rolePermissionRelation;
        QPermission qPermission = QPermission.permission;
        List<Permission> permissionList = factory
                .select(qPermission)
                .from(qRolePermissionRelation)
                .innerJoin(qPermission)
                .on(qRolePermissionRelation.permissionId.eq(qPermission.id))
                .where(qRolePermissionRelation.roleId.eq(roleId))
        .fetch();
        return permissionList;
    }

    /** 通过角色Id获取角色所有的权限Id
     * @param roleId
     * @return
     */
    public List<Long> getAllPermissionIdsByRoleId(long roleId) {
        QRolePermissionRelation qRolePermissionRelation = QRolePermissionRelation.rolePermissionRelation;
        QPermission qPermission = QPermission.permission;
        List<Long> permissoinIdList = factory
                .select(qPermission.id)
                .from(qRolePermissionRelation)
                .innerJoin(qPermission)
                .on(qRolePermissionRelation.permissionId.eq(qPermission.id))
                .where(qRolePermissionRelation.roleId.eq(roleId))
                .fetch();
        return permissoinIdList;
    }

    /** 通过角色Id获取关联所有的权限角色关系
     * @param roleId
     * @return
     */
    public List<RolePermissionRelation> getAllItemByRoleId(long roleId) {
        QRolePermissionRelation qRolePermissionRelation = QRolePermissionRelation.rolePermissionRelation;
        List<RolePermissionRelation> rolePermissionRelationList = factory
                .select(qRolePermissionRelation)
                .from(qRolePermissionRelation)
                .where(qRolePermissionRelation.roleId.eq(roleId))
                .fetch();
        return rolePermissionRelationList;
    }

    //  批量添加角色权限关系
    @Transactional(rollbackOn = Exception.class)
    public void saveAll(List<RolePermissionRelation> items)throws SwallowException {
        items.forEach(item -> {
            em.persist(item);
        });
        // 刷新到数据库
        em.flush();
        em.clear();
    }

    /**
     * 获取指定角色已经授权的权限项和角色权限关系
     * @param roleId
     * @return
     */
    @Transactional
    public List<PermissionOfRoleVo> getAllPermissionfRoleByRoleId(Long roleId) {
        QRolePermissionRelation qRolePermissionRelation = QRolePermissionRelation.rolePermissionRelation;
        QPermission qPermission = QPermission.permission;
        List<PermissionOfRoleVo> result = null;
        result = getQuery()
                .select(Projections.bean(PermissionOfRoleVo.class,qRolePermissionRelation.id.as("rolePermissionRelationId"),
                        qPermission.id,
                        qPermission.name,qPermission.moduleId,qPermission.code))
                .innerJoin(qPermission)
                .on(qRolePermissionRelation.permissionId.eq(qPermission.id))
                .where(qRolePermissionRelation.roleId.eq(roleId))
                .fetch();
        return result;
    }
}
