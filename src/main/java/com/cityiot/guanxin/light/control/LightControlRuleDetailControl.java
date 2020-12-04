package com.cityiot.guanxin.light.control;


import com.cityiot.guanxin.light.entity.LightControlRuleDetail;
import com.cityiot.guanxin.light.service.LightControlRuleDetailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

/**
 * 路灯规则控制实体数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "路灯规则控制实体数据访问API接口")
@RequestMapping("/api/lightControlRuleDetail")
public class LightControlRuleDetailControl {
	
	private static final Logger log = LoggerFactory.getLogger(LightControlRuleDetailControl.class);

	@Autowired
	private LightControlRuleDetailService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	@ApiModel(value="路灯规则控制查询对象")
	static class QueryBean extends BasePageQueryBean{

		// 规则编号
		@ApiModelProperty(value=" 规则编号",name="code",example="")
		@Like
		private String code;
		// 路灯控制规则
		@ApiModelProperty(value="路灯控制规则",name="lightControlRuleId",example="")
		private Long lightControlRuleId;
		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public Long getLightControlRuleId() {
			return lightControlRuleId;
		}

		public void setLightControlRuleId(Long lightControlRuleId) {
			this.lightControlRuleId = lightControlRuleId;
		}
	}
	
	/**
	 * 通过查询bean进行分页查询数据
	 * @param query
	 * @return
	 */
	@ApiOperation(value = " 通过查询bean进行分页查询数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@PostMapping("listpage")
	@SuppressWarnings("unchecked")
	public ApiResult<PageListData<LightControlRuleDetail>> query(@RequestBody  QueryBean query){
		try {			
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询路灯规则控制实体对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询路灯规则控制实体对象出错:"+e.getMessage());
		}
	}

}
