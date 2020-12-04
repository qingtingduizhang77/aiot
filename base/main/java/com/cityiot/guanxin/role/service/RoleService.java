package com.cityiot.guanxin.role.service;

import com.cityiot.guanxin.role.entity.QRole;
import com.cityiot.guanxin.role.entity.Role;
import com.cityiot.guanxin.role.repository.RoleRepository;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

@Service
public class RoleService extends BaseService<RoleRepository, Role> {
    /** 根据角色名获取角色
     * @param name
     * @return
     */
    public Role getRoleByRoleName(String name) {
        QRole qRole = QRole.role;
        return getRepsitory()
                .findOneEntityByColumns(query -> {
                    return query.where(qRole.name.eq(name));
                });
    }
}
