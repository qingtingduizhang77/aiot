package com.cityiot.guanxin.user.repository;

import com.cityiot.guanxin.common.repository.BaseRepository;
import com.cityiot.guanxin.role.entity.QRole;
import com.cityiot.guanxin.user.entity.QUserRoleRelation;
import com.cityiot.guanxin.role.entity.Role;
import com.cityiot.guanxin.user.entity.UserRoleRelation;
import com.querydsl.core.types.EntityPath;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleRelationRepository extends BaseRepository<UserRoleRelation> {
    

    /** 通过用户Id获取用户所有的角色
     * @param userId
     * @return
     */
    public List<Role> getAllRoleByUserId(long userId) {
        QUserRoleRelation qUserRoleRelation = QUserRoleRelation.userRoleRelation;
        QRole qRole = QRole.role;
        List<Role> roleList = factory
                .select(QRole.role)
                .from(qUserRoleRelation)
                .innerJoin(qRole)
                .on(qUserRoleRelation.roleId.eq(qRole.id))
                .where(qUserRoleRelation.userId.eq(userId))
        .fetch();
        return roleList;
    }

    /** 通过用户Id获取用户所有的角色Id
     * @param userId
     * @return
     */
    public List<Long> getAllRoleIdsByUserId(long userId) {
        QUserRoleRelation qUserRoleRelation = QUserRoleRelation.userRoleRelation;
        QRole qRole = QRole.role;
        List<Long> roleIdList = factory
                .select(QRole.role.id)
                .from(qUserRoleRelation)
                .innerJoin(qRole)
                .on(qUserRoleRelation.roleId.eq(qRole.id))
                .where(qUserRoleRelation.userId.eq(userId))
                .fetch();
        return roleIdList;
    }
}
