package com.cityiot.guanxin.organization.entity.vo;

public class AllocateOrgAreaVo {

    private long orgId;
    private Long[] readyGiveUpAreaIds;
    private Long[] readyGiveAreaIds;

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
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
