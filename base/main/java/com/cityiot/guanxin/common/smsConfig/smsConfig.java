package com.cityiot.guanxin.common.smsConfig;


/**
 * Created by jq on 2016/8/19.
 */
public class smsConfig {
    public static final String messageTitle = "利澳出行";
    public static final int projectType = 1;//利澳的系统类型是1
    public static final int appType_Customer = 1;//利澳的客户端是1
    public static final int appType_driver = 2;//利澳的车管端是2

    /**
     * 消息分类：
     * 订单提醒信息：100
     * 申请提醒信息：200
     * 审批提醒信息：300
     * 系统通知消息：400
     * 优惠活动消息:500
     * 获得优惠卷提醒消息：600
     */

    public static final Integer MESSAGE_TYPE_ORDER = 100;//订单
    public static final Integer MESSAGE_TYPE_APPLY = 200;//申请
    public static final Integer MESSAGE_TYPE_APPROVAL = 300;//审批
    public static final Integer MESSAGE_TYPE_SYSTEM = 400;//系统通知
    public static final Integer MESSAGE_TYPE_ACTIVITY = 500;//优惠活动
    public static final Integer MESSAGE_TYPE_COUPON = 600;//获得优惠券
    //违章
    public static final Integer MESSAGE_TYPE_VIOLATION = 800;

    /**
     * 消息子分类
     */
    public static final Integer MESSAGE_SUB_TYPE_FORCE_FINISH_ORDER=0;//强制结单
    public static final Integer MESSAGE_SUB_TYPE_APPROVAL_AUTH_PASS = 1;//认证审批通过
    public static final Integer MESSAGE_SUB_TYPE_APPROVAL_AUTH_REJECT = 2;//认证审批不过
    public static final Integer MESSAGE_SUB_TYPE_APPROVAL_ERROR_RETURN_PASS = 3;//异常还车审批通过
    public static final Integer MESSAGE_SUB_TYPE_APPROVAL_ERROR_RETURN_REJECT = 4;//异常还车审批不过
    public static final Integer MESSAGE_SUB_TYPE_APPROVAL_DEPOSIT_PASS = 5;//押金退回审批通过
    public static final Integer MESSAGE_SUB_TYPE_APPROVAL_DEPOSIT_REJECT = 6;//押金退回审批不过
    public static final Integer MESSAGE_SUB_TYPE_TIMEOUT_ORDER = 7;//超时未解锁

    public static final Integer MESSAGE_SUB_TYPE_COMBO_DUE_SOON = 8;//套餐即将到期提醒
    public static final Integer MESSAGE_APPOINTMET_APPROVAL_AUTH_PASS = 9;
    public static final Integer MESSAGE_APPOINTMET_ASSGIN_CAR = 10;

    public static final String MESSAGE_TYPE_ORDER_NAME = "订单提醒消息";
    public static final String MESSAGE_TYPE_APPLY_NAME = "申请提醒信息";
    public static final String MESSAGE_TYPE_APPROVAL_NAME = "审批提醒信息";
    public static final String MESSAGE_TYPE_SYSTEM_NAME = "系统通知消息";
    public static final String MESSAGE_TYPE_ACTIVITY_NAME = "优惠活动消息";
    public static final String MESSAGE_TYPE_COUPON_NAME = "获得优惠卷提醒消息";



    public static final Integer MESSAGE_TYPE_VEHICLE_REMIND = 700;
    public static final String MESSAGE_TYPE_VEHICLE_REMIND_NAME = "附近有车提醒消息";
    public static final Integer REMIND_CAR = 1;//有车
    public static final Integer REMIND_OUT_TIME = 2;//超时 无车

    /**
     * 人员分类：
     * 后台客服人员：1
     * 车管人员：2
     * 客户：3
     */
    public static final Integer PESRSON_TYPE_WEB = 1;
    public static final Integer PESRSON_TYPE_DRIVER = 2;
    public static final Integer PESRSON_TYPE_CUSTOMER = 3;
}
