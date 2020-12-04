package com.cityiot.guanxin.videoGroup.control;

import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.videoGroup.entity.QVideoGroup;
import com.cityiot.guanxin.videoGroup.entity.VideoGroup;
import com.cityiot.guanxin.videoGroup.entity.VideoGroupToDevice;
import com.cityiot.guanxin.videoGroup.service.VideoGroupService;
import com.cityiot.guanxin.videoGroup.service.VideoGroupToDeviceService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 视频分组管理控制层
 * @author Guoyz
 * createTime   2020/7/31 14:02
 */
@RestController
@Api(tags = "视频分组管理控制层")
@RequestMapping("/api/videoGroup")
public class VideoGroupControl {
    private static final Logger log = LoggerFactory.getLogger(VideoGroupControl.class);

    @Autowired
    private VideoGroupService service;

    @Autowired
    private VideoGroupToDeviceService VGTDService;
    @ApiModel(value="视频分组管理查询对象")
    static class QueryBean extends BasePageQueryBean {
        // 名称
        @ApiModelProperty(value="名称",name="name",example="")
        @Like
        private String name;

        // 分组父ID
        @ApiModelProperty(value="分组父ID",name="parentId",example="")
        private Long parentId;

        @ApiModelProperty(value="修改时间",name="lastmodi",example="")
        private Date lastmodi;

        // 排序号
        @ApiModelProperty(value="排序号",name="orderNo",example="")
        private Long orderNo;

        public Long getParentId() {
            return parentId;
        }

        public void setParentId(Long parentId) {
            this.parentId = parentId;
        }

        public Date getLastmodi() {
            return lastmodi;
        }

        public void setLastmodi(Date lastmodi) {
            this.lastmodi = lastmodi;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(Long orderNo) {
            this.orderNo = orderNo;
        }
    }

    /**
     * 新增一个视频分组管理对象
     * @param item
     * @return
     */
    @PutMapping
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "新增一个视频分组管理对象")
    @ViLog(operEvent = "新增一个视频分组管理对象", operType =1)//日志记录
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    public ApiResult<VideoGroup> saveNewVideoGroup(@RequestBody VideoGroup item) {
        try {
            service.checkIsGtLevel(item);// 检查是否超过9层
            return ApiResult.success(service.insertItem(item));
        } catch (Exception e) {
            log.error("新增视频分组管理出错:"+e.getMessage(),e);
            return ApiResult.fail("新增视频分组管理出错:"+e.getMessage());
        }
    }

    /**
     * 删除视频分组管理
     * @param ids
     * @return
     */
    @ApiOperation(value = " 删除视频分组管理")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "ids", value = "视频分组管理IDs数组", required = true,allowMultiple=true, dataType = "Long")
    })
    @ViLog(operEvent = "删除视频分组管理", operType =3)//日志记录
    @DeleteMapping
    public BaseApiResult deleteVideoGroup(@RequestBody long []ids) {
        try {
            for(var id:ids)
                if (service.getVideoGroupListByParentId(id).size() > 0) {
                    throw new Exception("有子级分组，不予以删除！");
                }else {
                    service.deleteItemById(id);}
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("删除视频分组管理出错:"+e.getMessage(),e);
            return BaseApiResult.failResult(500,"删除视频分组管理出错:"+e.getMessage());
        }
    }

    /**
     * 修改视频分组管理
     * @param item
     * @return
     */
    @ApiOperation(value = "修改视频分组管理")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "修改视频分组管理", operType =2)//日志记录
    @PostMapping
    @SuppressWarnings("unchecked")
    public ApiResult<VideoGroup> saveVideoGroup(@RequestBody VideoGroup item){
        try {
            VideoGroup group = service.getItemById(item.getParentId());// 父节点
            if (item.getParentId() == item.getId() || (group != null && group.getParentIdArr() != null &&
                    (group.getParentIdArr().contains("'"+item.getId()+"',") || group.getParentId() == item.getId()))) {
                return ApiResult.fail("节点不可移动到子节点或本节点分组上！");
            }
            service.checkIsGtLevel(item);// 检查是否超过9层
            return ApiResult.success(service.updateItem(item));
        } catch (Exception e) {
            log.error("修改视频分组管理出错:"+e.getMessage(),e);
            return ApiResult.fail("修改视频分组管理出错:"+e.getMessage());
        }
    }

    /**
     * 通过查询bean进行分页查询数据
     * @param query
     * @return
     */
    @ApiOperation(value = "通过查询bean进行分页查询数据")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @PostMapping("listpage")
    @SuppressWarnings("unchecked")
    public ApiResult<PageListData<VideoGroup>> query(@RequestBody QueryBean query){
        try {
            return ApiResult.success(service.getAllItemPageByQuerybean(query));
        } catch (Exception e) {
            log.error("查询视频分组管理出错:"+e.getMessage(),e);
            return ApiResult.fail("查询视频分组管理出错:"+e.getMessage());
        }
    }

    @ApiOperation(value = "根据id取视频分组管理信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "id", value = "视频分组管理Id", required = true, dataType = "long")
    })
    @RequestMapping("{id}")
    @SuppressWarnings("unchecked")
    public ApiResult<VideoGroup> getItemById(@PathVariable long id){
        try {
            return ApiResult.success(service.getItemById(id));
        } catch (Exception e) {
            log.error("查询视频分组管理出错:"+e.getMessage(),e);
            return ApiResult.fail("查询视频分组管理出错:"+e.getMessage());
        }
    }

    /**
     * 视频分组分组列表（不分页）
     * @return
     */
    @ApiOperation(value = "视频分组分组列表（不分页）")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @SuppressWarnings("unchecked")
    @RequestMapping("getAllItems")
    public ApiResult<List<VideoGroup>> getAllItems(){
        try {
            return ApiResult.success(service.getAllItems(query -> query.orderBy(QVideoGroup.videoGroup.parentId.asc(),QVideoGroup.videoGroup.orderNo.asc())));
        } catch (Exception e){
            log.error("查询平面图分组列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询平面图分组列表出错:"+e.getMessage());
        }
    }

    /**
     * 查询视频分组管理关联监控摄像头设备列表
     * @return
     */
    @ApiOperation(value = " 查询视频分组管理关联监控摄像头设备列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "id", value = "视频分组管理Id", required = true, dataType = "long")
    })
    @SuppressWarnings("unchecked")
    @RequestMapping("getDeviceList/{id}")
    public ApiResult<List<VideoGroupToDevice>> getAllItems(@PathVariable long id){
        try {
            return ApiResult.success(VGTDService.getVideoGroupToDeviceListByGroupId(id));
        } catch (Exception e){
            log.error("查询视频分组管理关联监控摄像头设备列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询视频分组管理关联监控摄像头设备列表出错:"+e.getMessage());
        }
    }

    /**
     * 添加关联监控摄像头设备
     * @return
     */
    @ApiOperation(value = " 添加关联监控摄像头设备")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "videoGroupToDevicelist", value = "视频分组关联数组", required = true, allowMultiple=true,dataType = "videoGroupToDevicelist")
    })
    @SuppressWarnings("unchecked")
    @RequestMapping("/add/device")
    public BaseApiResult addPlan(@RequestBody VideoGroupToDevice[] videoGroupToDevicelist) {
        try {
            for (VideoGroupToDevice videoGroupToDevice : videoGroupToDevicelist) {
                VGTDService.insertItem(videoGroupToDevice);
            }
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("添加关联监控摄像头设备出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"添加关联监控摄像头设备出错:"+e.getMessage());
        }
    }

    /**
     * 删除关联监控摄像头设备
     * @return
     */
    @ApiOperation(value = "删除关联监控摄像头设备")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "ids", value = "关联表IDs数组", required = true,allowMultiple=true, dataType = "Long")
    })
    @SuppressWarnings("unchecked")
    @RequestMapping("/cancel/device")
    public BaseApiResult cancelDevice(@RequestBody long []ids) {
        try {
            for (long id : ids) {
                VGTDService.deleteItemById(id);
            }
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("删除关联监控摄像头设备出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"删除关联监控摄像头设备出错:"+e.getMessage());
        }
    }

    /**
     * 更新监控摄像头设备排序
     * @return
     */
    @ApiOperation(value = " 更新监控摄像头设备排序")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "videoGroupToDevicelist", value = "关联表list", required = true, allowMultiple=true,dataType = "videoGroupToDevicelist")
    })
    @SuppressWarnings("unchecked")
    @RequestMapping("/updataDeviceList")
    public BaseApiResult updataPlanList(@RequestBody VideoGroupToDevice[] videoGroupToDevicelist) {
        try {
            for (VideoGroupToDevice videoGroupToDevice : videoGroupToDevicelist) {
                VGTDService.updateItem(videoGroupToDevice);
            }
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("更新监控摄像头设备排序出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"更新监控摄像头设备排序出错:"+e.getMessage());
        }
    }

}
