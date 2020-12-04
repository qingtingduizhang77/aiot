package com.cityiot.guanxin.monitor.entity;

/**
 * 井盖状态数据
 * 状态： 1正常 2合法位移 3非法位移 其他未知异常
 * 数值：当前位移
 * 长时间不更新即异常状态
 */
public class HoleCoverInfo extends DeviceStatusInfo {
    public static final class DeviceStatus {
        // 井盖正常移动
        public static final int NORMAL_MOVE = 2;
        // 井盖非法移动
        public static final int EXCEPTION_MOVE = 3;
        // 井盖移动了MIX_,混合状态
        public static final Integer[] MIX_MOVE = new Integer[]{NORMAL_MOVE,EXCEPTION_MOVE};
    }
}
