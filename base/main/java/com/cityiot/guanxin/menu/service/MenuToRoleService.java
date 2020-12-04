package com.cityiot.guanxin.menu.service;

import com.cityiot.guanxin.menu.entity.MenuToRole;
import com.cityiot.guanxin.menu.entity.QMenuToRole;
import com.cityiot.guanxin.menu.repository.MenuToRoleRepository;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

@Service
public class MenuToRoleService extends BaseService<MenuToRoleRepository, MenuToRole> {

    public void deleteItemByMenuId(Long menuId){
        getRepsitory().deleteEntityByColumns(
                query -> query.where(QMenuToRole.menuToRole.menuId.eq(menuId)));
    }
}
