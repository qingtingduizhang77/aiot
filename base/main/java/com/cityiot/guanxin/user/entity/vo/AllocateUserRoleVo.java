package com.cityiot.guanxin.user.entity.vo;

/**
 * 分配角色业务Vo
 */
public class AllocateUserRoleVo {
    private long userId;
    private Long[] readyGiveUpRoleIds;
    private Long[] readyGiveRoleIds;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Long[] getReadyGiveUpRoleIds() {
        return readyGiveUpRoleIds;
    }

    public void setReadyGiveUpRoleIds(Long[] readyGiveUpRoleIds) {
        this.readyGiveUpRoleIds = readyGiveUpRoleIds;
    }

    public Long[] getReadyGiveRoleIds() {
        return readyGiveRoleIds;
    }

    public void setReadyGiveRoleIds(Long[] readyGiveRoleIds) {
        this.readyGiveRoleIds = readyGiveRoleIds;
    }
}
