package com.cityiot.guanxin.user.repository;

import com.cityiot.guanxin.common.repository.BaseRepository;
import com.cityiot.guanxin.common.utils.Result;
import com.cityiot.guanxin.permission.entity.Permission;
import com.cityiot.guanxin.permission.entity.QPermission;
import com.cityiot.guanxin.user.entity.QRolePermissionRelation;
import com.cityiot.guanxin.user.entity.QUser;
import com.cityiot.guanxin.user.entity.QUserRoleRelation;
import com.cityiot.guanxin.user.entity.User;
import com.querydsl.core.types.EntityPath;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRepository extends BaseRepository<User> {
    

    public Result uploadHeadInfo(Long userId, String headImgUrl) {
        QUser qUser = QUser.user;
        this.factory.update(qUser).set(qUser.headPortrait, headImgUrl)
                .where(qUser.id.eq(userId)).execute();
        return Result.successResult();
    }

    /** 根据用户Id获取用户的所有权限
     * @param userId
     * @return
     */
    public List<Permission> getAllPermissionByUserId(long userId) {
        QUserRoleRelation qUserRoleRelation = QUserRoleRelation.userRoleRelation;
        QRolePermissionRelation qRolePermissionRelation = QRolePermissionRelation.rolePermissionRelation;
        QPermission qPermission = QPermission.permission;
        return factory
                .select(QPermission.permission)
                .from(qUserRoleRelation)
                .leftJoin(qRolePermissionRelation).on(qUserRoleRelation.roleId.eq(qRolePermissionRelation.roleId))
                .innerJoin(qPermission).on(qRolePermissionRelation.permissionId.eq(qPermission.id))
                .where(qUserRoleRelation.userId.eq(userId))
        .fetch();
    }
}
