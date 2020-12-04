package com.cityiot.guanxin.deviceInformation.control;

import java.util.*;

import com.cityiot.guanxin.alarmRule.entity.QAlarmRule;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.deviceInformation.entity.QDeviceinformation;
import com.cityiot.guanxin.deviceInformation.service.DeviceinformationService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.moduleAuth.service.ModuleControlService;
import com.cityiot.guanxin.serviceClient.ServiceClientUtil;
import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.warning.entity.DeviceWarningMsg;
import com.cityiot.guanxin.warning.entity.QDeviceWarningMsg;
import com.cityiot.guanxin.warning.service.DeviceWarningService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

/**
 * 设备类型数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "设备告警信息访问API接口")
@RequestMapping("/api/devicewarning")
public class DeviceWarningControl {
	
	private static final Logger log = LoggerFactory.getLogger(DeviceWarningControl.class);
	
	@Autowired
	private DeviceWarningService service;
	
	 @Autowired
	 private ServiceClientUtil serviceClientUtil;
	 
	 @Autowired
	 private ModuleControlService moduleControlService;

	 @Autowired
	 private DeviceinformationService deviceinformationService;
	 
	
	/**
	 * 设备告警信息查询对象
	 * @author aohanhe
	 *
	 */
	@ApiModel(value="设备告警信息查询对象")
	static class QueryBean extends BasePageQueryBean{

		 @ApiModelProperty(value="告警规则名称",name="ruleName",example="")
		 @CnName("告警规则名称")
		 @Like
		 private String ruleName;
		 
		 @ApiModelProperty(value="告警类型",name="warningType",example="")
		 @CnName("告警类型")
		 private String warningType;
		 
		 @ApiModelProperty(value="告警级别",name="warningEvel",example="")
		 @CnName("告警级别")
		 private String warningEvel;
		 
		 @ApiModelProperty(value="状态",name="state",example="状态：1未处理，2已处理")
		 @CnName("状态")
		 private Integer state;

		 @ApiModelProperty(value="修改日期",name="lastmodi",example="")
		 private Date lastmodi;

		@ApiModelProperty(value="设备ID",name="deviceId",example="")
		@CnName("状态")
		 private Long deviceId;

		@ApiModelProperty(value="查询类型",name="selectType",example="")
		@CnName("查询类型")
		private Integer selectType;

		@ApiModelProperty(value="规则Code查询",name="codes",example="")
		@CnName("规则Code查询")
		private List<String> codes;

		public String getRuleName() {
			return ruleName;
		}

		public void setRuleName(String ruleName) {
			this.ruleName = ruleName;
		}

		public String getWarningType() {
			return warningType;
		}

		public void setWarningType(String warningType) {
			this.warningType = warningType;
		}

		public String getWarningEvel() {
			return warningEvel;
		}

		public void setWarningEvel(String warningEvel) {
			this.warningEvel = warningEvel;
		}

		public Integer getState() {
			return state;
		}

		public void setState(Integer state) {
			this.state = state;
		}

		public Date getLastmodi() {
			return lastmodi;
		}

		public void setLastmodi(Date lastmodi) {
			this.lastmodi = lastmodi;
		}

		public Long getDeviceId() {
			return deviceId;
		}

		public void setDeviceId(Long deviceId) {
			this.deviceId = deviceId;
		}

		public Integer getSelectType() {
			return selectType;
		}

		public void setSelectType(Integer selectType) {
			this.selectType = selectType;
		}

		public List<String> getCodes() {
			return codes;
		}

		public void setCodes(List<String> codes) {
			this.codes = codes;
		}
	}
	
	/**
	 *设备监控大屏统计查询
	 *
	 */
	@ApiOperation(value = "设备监控大屏统计查询")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@PostMapping("index")
	@SuppressWarnings("unchecked")
	public ApiResult<Map<String,Object>>  indexDeviceWarningStatis()
	{
		try {
			
			 Map<String,Object> re=service.getIndexDeviceWarningStatistics();
			 re.put("data", service.getDeviceWarningMsgByState(1));
			 //re.put("data", service.getDeviceWarningMsgByState(1));
			return ApiResult.success(re);
		}catch (Exception e) {
            log.error("设备监控大屏统计查询:" + e.getMessage(), e);
            return ApiResult.fail("查询我的待办事项列表:" + e.getMessage());
        }
		
	}
	
	/**
	 * 移动APP告警统计查询
	 * @return
	 */
	@ApiOperation(value = "移动APP告警统计查询")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@PostMapping("app")
	@SuppressWarnings("unchecked")
	public ApiResult<Map<String,Object>>  getAppDeviceWarningStatistics()
	{
		try {
			
			 Map<String,Object> re=service.getAppDeviceWarningStatistics();
			 re.put("data", service.getDeviceWarningMsgByState(1));
			 
			return ApiResult.success(re);
		}catch (Exception e) {
           log.error("设备监控大屏统计查询:" + e.getMessage(), e);
           return ApiResult.fail("查询我的待办事项列表:" + e.getMessage());
       }
	}
	
	
	/**
	 * 通过查询bean进行分页查询数据
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "通过查询bean进行分页查询数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true)})
	@PostMapping("listpage")
	@SuppressWarnings("unchecked")
	public ApiResult<PageListData<DeviceWarningMsg>> query(@RequestBody QueryBean query){
		try {
			
			// 用户
			Userview userview = (Userview) SecurityUtils.getSubject().getPrincipal();
			
			boolean admin=moduleControlService.isAuth(10001, userview.getId());
			
			///long roleId=0;
			if(query.getSorts()==null || query.getSorts().length==0)
			{
				query.setSorts(new String[] {"-lastmodi"});
			}
			
			QDeviceWarningMsg qDeviceWarningMsg=QDeviceWarningMsg.deviceWarningMsg;
			QDeviceinformation qDeviceinformation = QDeviceinformation.deviceinformation;
			Long deviceId = query.getDeviceId();
			Integer selectType = query.getSelectType();
			List<String> codes = query.getCodes();
			query.setSelectType(null);// 设置为null时，querybean不做此字段查询不报错（报错因DeviceWarningMsg无此属性字段）
			query.setDeviceId(null);
			query.setCodes(null);
			PageListData<DeviceWarningMsg> bean = service.getAllItemPageByQuerybean(query, qu ->{
				qu.leftJoin(qDeviceinformation)
						.on(qDeviceinformation.id.eq(qDeviceWarningMsg.deviceId));
				qu.leftJoin(QAlarmRule.alarmRule)
						.on(QAlarmRule.alarmRule.id.eq(qDeviceWarningMsg.ruleId.castToNum(Long.class)));
				if(!admin){
					qu.where(qDeviceWarningMsg.noticeUserId.like("%"+userview.getId()+",%").
							or(qDeviceWarningMsg.noticeUserId.like("%,"+userview.getId()+"%")));
				}
				if (selectType != null && selectType == 1 && deviceId != null && deviceId > 0) {// 查询灯杆及子级设备告警记录
					qu.where(qDeviceinformation.parentId.eq(deviceId)
							.or(qDeviceinformation.id.eq(deviceId)));
				}else if (selectType != null && selectType == 2 && deviceId != null && deviceId > 0){// 查询当前设备告警记录
					qu.where(qDeviceinformation.id.eq(deviceId));
				}
				if (codes != null && !codes.isEmpty()) {
					qu.where(QAlarmRule.alarmRule.code.in(codes));
				}
				return qu;
			});

			return ApiResult.success(bean);
		} catch (Exception e) {
			log.error("查询设备告警信息对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询设备告警信息对象出错:"+e.getMessage());
		}
	}
	
	
	
	
	
	@ApiOperation(value = "通过id设备告警信息取数据")
	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "id", value = "设备告警信息id", required = true, dataType = "long")
	  		})
	@PostMapping("{id}")
	@SuppressWarnings("unchecked")
	public ApiResult<DeviceWarningMsg> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("id设备告警信息取数据出错:"+e.getMessage(),e);
			return ApiResult.fail("id设备告警信息取数据出错:"+e.getMessage());
		}
	}
	
	
	/**
	 * 关闭设备告警信息
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "关闭设备告警信息")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true),
	                     @ApiImplicitParam(name = "id", value = "设备告警信息id", required = true, dataType = "long",paramType="query"),
                         @ApiImplicitParam(name = "handleUserId", value = "处理人ID", required = true, dataType = "int",paramType="query"),
                         @ApiImplicitParam(name = "handleUserName", value = "处理人姓名", required = true, dataType = "String",paramType="query"),
                         @ApiImplicitParam(name = "handleTime", value = "处理时间", required = true, dataType = "Date",paramType="query" ,defaultValue="20120-04-08 22:23:24"),
                         @ApiImplicitParam(name = "handleText", value = "处理过程", required = false, dataType = "String",paramType="query")
	                 })
	@ViLog(operEvent = "关闭设备告警信息", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PostMapping("closeWarningMsg")
	public ApiResult<String> closeWarningMsg(@RequestParam(value = "id", required = true) long id,
			                                 @RequestParam(value = "handleUserId", required = true) Integer handleUserId,
			                                 @RequestParam(value = "handleUserName", required = true) String handleUserName,
			                                 @RequestParam(value = "handleTime", required = true ) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date handleTime,
			                                 @RequestParam(value = "handleText", required = false) String handleText) {
		try {
			
			DeviceWarningMsg deviceWarningMsg=service.getItemById(id);
			if(deviceWarningMsg!=null)
			{
				
				deviceWarningMsg.setHandleUserId(handleUserId);
				deviceWarningMsg.setHandleUserName(handleUserName);
				deviceWarningMsg.setHandleTime(handleTime);
				deviceWarningMsg.setHandleText(handleText);
				deviceWarningMsg.setState(2);
				service.updateItem(deviceWarningMsg);
				long count  = service.getWarningCountByDeviceId(deviceWarningMsg.getDeviceId());
				Deviceinformation deviceinformation = deviceinformationService.getItemById(deviceWarningMsg.getDeviceId());
				if (count == 0 && deviceinformation != null) {// 如全部告警状态已处理,设备告警状态为正常0
					deviceinformation.setAlarmStatus(0);
					deviceinformationService.updateItem(deviceinformation);
				}
			}
			
			return ApiResult.success("关闭成功用");
		} catch (Exception e) {
			log.error("关闭出错:"+e.getMessage(),e);
			return ApiResult.fail("关闭出错:"+e.getMessage());
		}
	}
	
	
	
	
	
	
	/**
	 * 测试设备规则
	 * @return
	 */
	@ApiOperation(value = "测试设备规则")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true),
	                     @ApiImplicitParam(name = "id", value = "设备id", required = true, dataType = "long",paramType="query"),
                         @ApiImplicitParam(name = "jsdata", value = "js格式对像数据", required = true, dataType = "String",paramType="query")
	                 })
	@ViLog(operEvent = "测试设备规则", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PostMapping("testRrule")
	public ApiResult<String> testRrule(@RequestParam(value = "id", required = true) long id,
			                                 @RequestParam(value = "jsdata", required = true) String jsdata) {
		try {
			
			String param="{\"deviceId\":"+id+",\"function\":\"deviceData\",\"data\":"+jsdata+"}";
			
            String re=serviceClientUtil.csExtend(param);
			
			return ApiResult.success(re);
		} catch (Exception e) {
			log.error("测试设备规则出错:"+e.getMessage(),e);
			return ApiResult.fail("测试设备规则出错:"+e.getMessage());
		}
	}

	/**
	 * 设备警告统计
	 * @param year 年份
	 * @return
	 */
	@ApiOperation(value = "设备告警统计")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true)
	})
	@PostMapping("/stat")
	@SuppressWarnings("unchecked")
	public ApiResult<Map<String, Object>> getWarningStat(String year){
		try {
			Map<String, Object> data = new HashMap<>();
			data.put("warningEvel", service.getWarningEvelStatByYear(year));
			data.put("warningType", service.getWarningTypeStatByYear(year));
			return ApiResult.success(data);
		} catch (Exception e) {
			log.error("查询设备告警统计出错:"+e.getMessage(),e);
			return ApiResult.fail("查询设备告警统计出错:"+e.getMessage());
		}
	}


    /**
     * 设备警告统计
     * @param year 年份
     * @return
     */
    @ApiOperation(value = "设备告警统计")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true)
    })
    @PostMapping("/statByCode")
    @SuppressWarnings("unchecked")
    public ApiResult<Map<String, Object>> getWarningStat(String year, String[] codes){
        try {
            return ApiResult.success(service.getWarningMsgStatByYearAndCode(year, codes));
        } catch (Exception e) {
            log.error("查询设备告警统计出错:"+e.getMessage(),e);
            return ApiResult.fail("查询设备告警统计出错:"+e.getMessage());
        }
    }
}
