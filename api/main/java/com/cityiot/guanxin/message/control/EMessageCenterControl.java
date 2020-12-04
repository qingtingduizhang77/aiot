package com.cityiot.guanxin.message.control;


import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.user.entity.Userview;

import io.swagger.annotations.Api;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityiot.guanxin.message.entity.QEMessageCenter;
import com.cityiot.guanxin.message.service.EMessageCenterService;
import com.cityiot.guanxin.response.BaseResponse;
import com.cityiot.guanxin.response.RespCode;
import com.cityiot.guanxin.response.RespData;

import swallow.framework.web.BasePageQueryBean;

/**
 * 消息中心数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(value = "/guanxin-api/message",tags = "消息中心数据访问API接口")
@RequestMapping("/guanxin-api/message")
public class EMessageCenterControl {
	
	private static final Logger log = LoggerFactory.getLogger(EMessageCenterControl.class);

	@Autowired
	private EMessageCenterService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	static class QueryBean extends BasePageQueryBean{
		private long targetId;

		public long getTargetId() {
			return targetId;
		}

		public void setTargetId(long targetId) {
			this.targetId = targetId;
		}
	}

	/**
	 * 未读消息数量
	 * @param
	 * @return
	 */
	@RequestMapping("/notReadCount")
	public BaseResponse notReadCount(){
		Subject subject = SecurityUtils.getSubject();
		Userview loginUser = (Userview) subject.getPrincipal();
		RespData data = new RespData();
		try {
			data.putData("msgNumber", service.notReadCount(loginUser.getId()));
			BaseResponse baseResponse = new BaseResponse(data);
			baseResponse.setState(RespCode.SUCCESS);
			return baseResponse;
		} catch (Exception e) {
			log.error("查询未读消息数量出错:"+e.getMessage(),e);
			return new BaseResponse(data, RespCode.ERROR, "查询未读消息数量出错");
		}
	}

	/**
	 * 查询消息列表
	 * @param
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/queryMsgList")
	public BaseResponse queryMsgList(int page, int pageSize){
		Subject subject = SecurityUtils.getSubject();
		Userview loginUser = (Userview) subject.getPrincipal();
		RespData data = new RespData();
		try {
			QueryBean query = new QueryBean();
			query.setPage(page);
			query.setPageSize(pageSize);
			query.setTargetId(loginUser.getId());
			QEMessageCenter eMessageCenter = QEMessageCenter.eMessageCenter;
			data.putData("messageList", service.getAllItemPageByQuerybean(query, qu ->
								qu.where(eMessageCenter.messageStatus.in(1,2))
										.orderBy(eMessageCenter.created.desc())).getItems());
			BaseResponse baseResponse = new BaseResponse(data);
			baseResponse.setState(RespCode.SUCCESS);
			return baseResponse;
		} catch (Exception e) {
			log.error("查询消息列表出错:"+e.getMessage(),e);
			return new BaseResponse(data, RespCode.ERROR, "查询消息列表出错");
		}
	}

	/**
	 *  标记消息已读/删除
	 * @param messageId
	 * @param status
	 * @return
	 */
	@ViLog(operEvent = "标记消息已读/删除", operType =2)//日志记录
	@RequestMapping("/tagMessage")
	public BaseResponse tagMessage(long messageId, int status){
		RespData data = new RespData();
		try {
			service.tagMessage(messageId, status);
			BaseResponse baseResponse = new BaseResponse(data);
			baseResponse.setState(RespCode.SUCCESS);
			return baseResponse;
		} catch (Exception e) {
			log.error("标记消息已读/删除出错:"+e.getMessage(),e);
			return new BaseResponse(data, RespCode.ERROR, "标记消息已读/删除出错");
		}
	}


	/**
	 * 标记全部已读
	 * @param
	 * @return
	 */
	@ViLog(operEvent = "标记全部已读", operType =2)//日志记录
	@RequestMapping("/tagMessageAll")
	public BaseResponse tagMessageAll(){
		Subject subject = SecurityUtils.getSubject();
		Userview loginUser = (Userview) subject.getPrincipal();
		RespData data = new RespData();
		try {
			service.tagMessageAll(loginUser.getId());
			BaseResponse baseResponse = new BaseResponse(data);
			baseResponse.setState(RespCode.SUCCESS);
			return baseResponse;
		} catch (Exception e) {
			log.error("标记消息已读/删除出错:"+e.getMessage(),e);
			return new BaseResponse(data, RespCode.ERROR, "标记消息已读/删除出错");
		}
	}
}
