package com.cityiot.guanxin.menu.service;

import com.cityiot.guanxin.menu.entity.Menu;
import com.cityiot.guanxin.menu.entity.MenuToRole;
import com.cityiot.guanxin.menu.entity.MenuToUser;
import com.cityiot.guanxin.menu.entity.QMenu;
import com.cityiot.guanxin.menu.repository.MenuRepository;
import com.cityiot.guanxin.role.entity.Role;
import com.cityiot.guanxin.user.service.UserRoleRelationService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swallow.framework.exception.SwallowException;
import swallow.framework.service.BaseService;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MenuService extends BaseService<MenuRepository, Menu> {
    // 日志对象
    private static final Logger log = LoggerFactory.getLogger(MenuService.class);

    @Autowired
    private MenuToRoleService menuToRoleService;
    @Autowired
    private MenuToUserService menuToUserService;
    @Autowired
    private UserRoleRelationService userRoleRelationService;
    //新增
    public Menu insertItem(Menu item){
        Menu entity = super.insertItem(item);
        insertOrderInfo(entity);
        return entity;
    }

    // 添加关联表数据,更新副表数据
    private void insertOrderInfo(Menu item) throws SwallowException {
        // 菜单关联人员
        if (StringUtils.isNotEmpty(item.getAccountId())) {
            String[] accountIds = item.getAccountId().split(",");
            for (var i =0; i<accountIds.length;i++) {
                MenuToUser menuToUser = new MenuToUser();
                menuToUser.setMenuId(item.getId());
                menuToUser.setUserId(Long.valueOf(accountIds[i]));
                menuToUserService.insertItem(menuToUser);
            }
        }
        // 菜单关联角色
        if (StringUtils.isNotEmpty(item.getRoleId())) {
            String[] roleIds = item.getRoleId().split(",");
            for (var i =0; i<roleIds.length;i++) {
                MenuToRole menuToRole = new MenuToRole();
                menuToRole.setMenuId(item.getId());
                menuToRole.setRoleId(Long.valueOf(roleIds[i]));
                menuToRoleService.insertItem(menuToRole);
            }
        }
    }

    //删除
    @Transactional
    public void deleteItemById(long []ids) throws Exception{
        for(var id:ids){
            Menu itemById = this.getItemById(id);
            if(itemById.getSystemMenu()==1){
                throw new Exception("此菜单为系统菜单，不可删除！或联系管理员");
            }
            if (this.getMenuListByParentId(id).size() > 0) {
                throw new Exception("有子菜单，不予以删除！请先删除子菜单");
            }
            this.deleteItemById(id); //删除主表
            deleteItemByMenuId(id);//删除副表
        }
    }

    //更新
    @Transactional
    public Menu updateItem01(Menu item) throws Exception {
        Menu itemById = this.getItemById(item.getId());
        deleteItemByMenuId(item.getId());//删除关联副表
        insertOrderInfo(item);// 更新副表
        return super.updateItem(item);//更新主表
    }

    //删除指定菜单配置id的副表
    public void deleteItemByMenuId(long id){
        menuToRoleService.deleteItemByMenuId(id);
        menuToUserService.deleteItemByMenuId(id);
    }

    //查询是否有子菜单
    public List<Menu> getMenuListByParentId(long id){
        QMenu qmenu = QMenu.menu;
        return this.getAllItems(
                query -> query.where(qmenu.parentId.eq(id).or(qmenu.parentIdArr.like(id+",")))
        );
    }

    // 检查是否超过3层
    public void checkIsGtLevel(Menu item) throws Exception {
        // 设备分组属于第几级别
        if (item.getParentId() > 0) {
            Menu group = this.getItemById(item.getParentId());
            if (group != null){
                item.setLevel(group.getLevel() + 1);
                if (StringUtils.isNotEmpty(group.getParentIdArr())) {
                    item.setParentIdArr("" + group.getParentIdArr() + ",'" + group.getId() + "'");
                }else{
                    item.setParentIdArr("'" + group.getId()+ "'");
                }
            }
        }else {
            item.setLevel(1);
            item.setParentIdArr(null);
        }
        if (item.getLevel() > 3) {
            throw new Exception("分组层级最多为3！");
        }
    }

    //根据用户id加载可查看菜单接口
    public List<Menu> getAllItemsByUser(Long id) {
        //1.获取所有"可显示"的菜单列表
        List<Menu> allItems = this.getAllItems(query -> query.where(QMenu.menu.menuShow.eq(1)).orderBy(QMenu.menu.parentId.asc(), QMenu.menu.menuOrder.asc()));
        //2.查询用户角色
        List<Role> roles = userRoleRelationService.getAllRoleByUserId(id);//根据用户id获取角色id
        long match = roles.stream().filter(role -> role.getCode()!=null).count();
        //是个超级管理员
        if(match>0){
            return allItems;
        }
        //不是超级管理员
        Stream<Menu> stream = allItems.stream();
        //过滤掉该用户不该看到的菜单
        Predicate<Menu> filter = x -> {
            String accountId = x.getAccountId();
            String roleId = x.getRoleId();
            //用户和角色都为空
            if((accountId==null||accountId.isEmpty()) && (roleId==null||roleId.isEmpty())){
                return true;
            }
            //指定的用户可以看
            if((accountId!=null) && !accountId.isEmpty()){
                for (String s : accountId.split(",")) {
                    if(s.equals(id.toString())){return true;}
                }
            }
            //指定的角色可以看
            if(roleId!=null && !roleId.isEmpty() && roles.size()>0){
                List<String> rolesByUser = userRoleRelationService.getAllRoleIdsByUserId(id).stream().map(a -> a.toString()).collect(Collectors.toList());
                List<String> rolesByMenu = Arrays.asList(roleId.split(","));
                rolesByUser.retainAll(rolesByMenu);//求并集
                if(rolesByUser.size()>0){return true;}
            }
            return false;
        };
        List<Menu> collect = stream.filter(filter).collect(Collectors.toList());
        return collect;
    }
}
