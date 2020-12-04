package com.cityiot.guanxin.menu.control;


import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.menu.entity.Menu;
import com.cityiot.guanxin.menu.entity.QMenu;
import com.cityiot.guanxin.menu.service.MenuService;
import com.cityiot.guanxin.user.entity.Userview;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;


import java.util.Date;
import java.util.List;

/**
 * 菜单管理API接口
 * @author Guoyz
 *
 */
@RestController
@Api(tags = "菜单管理API接口")
@RequestMapping("/api/menu")
public class MenuControl {

    private static final Logger log = LoggerFactory.getLogger(MenuControl.class);


    @Autowired
    private MenuService service;


    /**
     * 查询对象
     * @author aohanhe
     *
     */
    @ApiModel(value="菜单管理查询对象")
    static class QueryBean extends BasePageQueryBean {

        // 菜单名称
        @Like
        @ApiModelProperty(value="菜单名称",name="name",example="")
        private String name;

        // 分组父ID
        @ApiModelProperty(value="分组父ID",name="parentId",example="")
        private Long parentId;

        @ApiModelProperty(value="修改时间",name="lastmodi",example="")
        private Date lastmodi;

        @ApiModelProperty(value="序号",name="menuOrder",example="")
        private Long menuOrder;

        public Long getParentId() {
            return parentId;
        }

        public void setParentId(Long parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getLastmodi() {
            return lastmodi;
        }

        public void setLastmodi(Date lastmodi) {
            this.lastmodi = lastmodi;
        }

        public Long getMenuOrder() {
            return menuOrder;
        }

        public void setMenuOrder(Long menuOrder) {
            this.menuOrder = menuOrder;
        }
    }

    /**
     * 新增菜单管理对象
     * @param item
     * @return
     */
    @ViLog(operEvent = "新增菜单管理对象", operType =1)//日志记录
    @SuppressWarnings("unchecked")
    @PutMapping()
    @RequiresPermissions(value={"001007001","SUPERADMIN"}, logical= Logical.OR)
    public ApiResult<Menu> saveNewMenu(@RequestBody Menu item) {
        try {
            service.checkIsGtLevel(item);// 检查是否超过3层
            return ApiResult.success(service.insertItem(item));
        } catch (Exception e) {
            log.error("新增菜单管理对象出错:"+e.getMessage(),e);
            return ApiResult.fail("新增菜单管理出错:"+e.getMessage());
        }
    }

    /**
     * 删除菜单管理对象
     * @return
     */
    @ViLog(operEvent = "删除菜单管理对象", operType =3)//日志记录
    @DeleteMapping
    @Transactional
    @RequiresPermissions(value={"001007002","SUPERADMIN"}, logical= Logical.OR)
    public BaseApiResult deleteMenu(@RequestBody long []ids) {
        try {
                service.deleteItemById(ids);
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("删除菜单管理对象出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"删除菜单管理对象出错:"+e.getMessage());
        }
    }


    /**
     * 修改菜单管理对象
     * @param item
     * @return
     */
    @ViLog(operEvent = "修改菜单管理对象", operType =2)//日志记录
    @PostMapping
    @SuppressWarnings("unchecked")
    @RequiresPermissions(value={"001007003","SUPERADMIN"}, logical= Logical.OR)
    public ApiResult<Menu> saveMenu(@RequestBody Menu item){
        try {
            Menu group = service.getItemById(item.getParentId());// 父节点
            if (item.getParentId() == item.getId() || (group != null && group.getParentIdArr() != null &&
                    (group.getParentIdArr().contains("'"+item.getId()+"',") || group.getParentId() == item.getId()))) {
                return ApiResult.fail("节点不可移动到子节点或本节点分组上！");
            }
            service.checkIsGtLevel(item);// 检查是否超过3层
            return ApiResult.success(service.updateItem01(item));
        } catch (Exception e) {
            log.error("修改菜单管理对象出错:"+e.getMessage(),e);
            return ApiResult.fail("修改菜单管理对象出错:"+e.getMessage());
        }
    }

    /**
     * 通过查询bean进行分页查询数据，走正常流程
     * @param query
     * @return
     */
    @PostMapping("listpage")
    @SuppressWarnings("unchecked")
    @RequiresPermissions(value={"001007004","SUPERADMIN"}, logical= Logical.OR)
    public ApiResult<PageListData<Menu>> query(@RequestBody QueryBean query){
        try {
            return ApiResult.success(service.getAllItemPageByQuerybean(query,a->a.orderBy(QMenu.menu.parentId.asc(),QMenu.menu.menuOrder.asc())));
        } catch (Exception e) {
            log.error("查询菜单管理对象出错:"+e.getMessage(),e);
            return ApiResult.fail("查询菜单管理对象出错:"+e.getMessage());
        }
    }
    /**
     * 菜单列表控件（不分页）
     * 不过滤权限
     * @return
     */
    @SuppressWarnings("unchecked")
    @GetMapping("getAllItems")
    public ApiResult<List<Menu>> getAllItems(){
        try {
            return ApiResult.success(service.getAllItems(query->query.orderBy(QMenu.menu.parentId.asc(),QMenu.menu.menuOrder.asc())));
        } catch (Exception e){
            log.error("查询菜单管理列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询菜单管理列表出错:"+e.getMessage());
        }
    }



    /**
     * 根据用户id加载可查看菜单接口
     * @return
     */
    @GetMapping("getAllItemsByUser")
    @SuppressWarnings("unchecked")
    public ApiResult<List<Menu>> getAllItemsByUser(){
        Subject subject = SecurityUtils.getSubject();
        Userview loginUser = (Userview) subject.getPrincipal();
        return ApiResult.success(service.getAllItemsByUser(loginUser.getId()));
    }
}
