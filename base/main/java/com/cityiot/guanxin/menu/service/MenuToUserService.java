package com.cityiot.guanxin.menu.service;


import com.cityiot.guanxin.menu.entity.MenuToUser;
import com.cityiot.guanxin.menu.entity.QMenuToUser;
import com.cityiot.guanxin.menu.repository.MenuToUserRepository;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

@Service
public class MenuToUserService extends BaseService<MenuToUserRepository, MenuToUser> {

    public void deleteItemByMenuId(Long menuId){
        getRepsitory().deleteEntityByColumns(
                query -> query.where(QMenuToUser.menuToUser.menuId.eq(menuId)));
    }
}
