package com.cityiot.guanxin.user.entity.vo;

/**
 * 分配角色业务Vo
 */
public class AllocateUserAreaVo {
    private long userId;
    private Long[] readyGiveUpAreaIds;
    private Long[] readyGiveAreaIds;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Long[] getReadyGiveUpAreaIds() {
        return readyGiveUpAreaIds;
    }

    public void setReadyGiveUpAreaIds(Long[] readyGiveUpAreaIds) {
        this.readyGiveUpAreaIds = readyGiveUpAreaIds;
    }

    public Long[] getReadyGiveAreaIds() {
        return readyGiveAreaIds;
    }

    public void setReadyGiveAreaIds(Long[] readyGiveAreaIds) {
        this.readyGiveAreaIds = readyGiveAreaIds;
    }
}
