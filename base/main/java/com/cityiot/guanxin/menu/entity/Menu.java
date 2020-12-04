package com.cityiot.guanxin.menu.entity;


import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;
import java.util.List;

/**
 * @author Guoyz
 * 菜单管理
 */
@ApiModel(value="菜单管理")
@CnName("菜单管理")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Menu extends BaseEntity {
    public static final String Parent = "T_Parent_Menu";
    @ApiModelProperty(value="上级菜单Id",name="parentId",example="")
    @CnName("上级菜单Id")
    @JoinEntity(name = "id",joinEntityClass = Menu.class,joinEntityAlias = Parent)
    private long parentId;
    @ApiModelProperty(value="菜单名称",name="name",example="")
    @CnName("菜单名称")
    private String name;
    @ApiModelProperty(value="级别",name="level",example="")
    @CnName("级别")
    private long level;//最高等级为1，最多3级
    @ApiModelProperty(value="父分组id数组",name="parentIdArr",example="")
    @CnName("父分组id数组")
    private String parentIdArr;
    @ApiModelProperty(value="路由名称",name="routeName",example="")
    @CnName("路由名称")
    private String routeName;
    @ApiModelProperty(value="序号",name="menuOrder",example="")
    @CnName("序号")
    private long menuOrder;//用于查询排序
    @ApiModelProperty(value="图标",name="icon",example="")
    @CnName("图标")
    private String icon;
    @ApiModelProperty(value="菜单显示",name="menuShow",example="")
    @CnName("菜单显示")
    private int menuShow;//1为显示，0为不显示，默认是1
    @ApiModelProperty(value="系统菜单",name="systemMenu",example="")
    @CnName("系统菜单")
    private int systemMenu;//1为系统菜单，0为非系统菜单，默认为0
    @ApiModelProperty(value="外链地址",name="url",example="")
    @CnName("外链地址")
    private String url;
    @ApiModelProperty(value="参数Query",name="jsonQuery",example="")
    @CnName("参数Query")
    private String jsonQuery;
    @ApiModelProperty(value="参数Params",name="jsonParams",example="")
    @CnName("参数Params")
    private String jsonParams;
    @ApiModelProperty(value="备注",name="remark",example="")
    @CnName("备注")
    private String remark;
    @ApiModelProperty(value="查看角色ID",name="roleId",example="")
    @CnName("查看角色ID")
    private String roleId;//用逗号分隔
    @ApiModelProperty(value="查看角色名称",name="roleName",example="")
    @CnName("查看角色名称")
    private String roleName;//用#&#分隔
    @ApiModelProperty(value="查看人员ID",name="accountId",example="")
    @CnName("查看人员ID")
    private String accountId;//用逗号分隔
    @ApiModelProperty(value="查看人员名称",name="accountName",example="")
    @CnName("查看人员名称")
    private String accountName;//用#&#分隔

    public int getSystemMenu() {
        return systemMenu;
    }

    public void setSystemMenu(int systemMenu) {
        this.systemMenu = systemMenu;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public String getParentIdArr() {
        return parentIdArr;
    }

    public void setParentIdArr(String parentIdArr) {
        this.parentIdArr = parentIdArr;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public long getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(long menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getMenuShow() {
        return menuShow;
    }

    public void setMenuShow(int menuShow) {
        this.menuShow = menuShow;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJsonQuery() {
        return jsonQuery;
    }

    public void setJsonQuery(String jsonQuery) {
        this.jsonQuery = jsonQuery;
    }

    public String getJsonParams() {
        return jsonParams;
    }

    public void setJsonParams(String jsonParams) {
        this.jsonParams = jsonParams;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
