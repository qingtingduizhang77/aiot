package com.cityiot.guanxin.organization.entity.vo;

import com.cityiot.guanxin.organization.entity.Organization;

public class OrganizationVo {

    private Organization organization;
    private Long[] readyGiveUpUserIds;
    private Long[] readyGiveUserIds;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Long[] getReadyGiveUpUserIds() {
        return readyGiveUpUserIds;
    }

    public void setReadyGiveUpUserIds(Long[] readyGiveUpUserIds) {
        this.readyGiveUpUserIds = readyGiveUpUserIds;
    }

    public Long[] getReadyGiveUserIds() {
        return readyGiveUserIds;
    }

    public void setReadyGiveUserIds(Long[] readyGiveUserIds) {
        this.readyGiveUserIds = readyGiveUserIds;
    }
}
