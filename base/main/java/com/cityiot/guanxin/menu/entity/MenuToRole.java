package com.cityiot.guanxin.menu.entity;


import com.cityiot.guanxin.common.entity.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * @author Guoyz
 * 菜单与角色中间表
 */
@CnName("菜单管理")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class MenuToRole extends BaseEntity {

    @CnName("菜单id")
    private long menuId;
    @CnName("角色id")
    private long roleId;

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
