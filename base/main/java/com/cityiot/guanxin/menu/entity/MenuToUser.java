package com.cityiot.guanxin.menu.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * @author Guoyz
 * 菜单与用户中间表
 */
@CnName("菜单管理")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class MenuToUser extends BaseEntity {
    @CnName("菜单id")
    private long menuId;
    @CnName("用户id")
    private long userId;

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
