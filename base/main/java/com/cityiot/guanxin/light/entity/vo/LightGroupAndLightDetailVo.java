package com.cityiot.guanxin.light.entity.vo;

import com.cityiot.guanxin.light.entity.LightGroup;
import com.cityiot.guanxin.light.entity.Light;

import java.util.List;

public class LightGroupAndLightDetailVo {
    // 路灯分组
    private LightGroup lightGroup;
    // 旧的要编辑的路灯子表
    private List<Light> oldLightList;
    // 新路灯子表
    private List<Light> newLightList;
    // 将要删除的路灯子表
    private List<Light> readyDeleteLightList;

    public LightGroup getLightGroup() {
        return lightGroup;
    }

    public void setLightGroup(LightGroup lightGroup) {
        this.lightGroup = lightGroup;
    }

    public List<Light> getOldLightList() {
        return oldLightList;
    }

    public void setOldLightList(List<Light> oldLightList) {
        this.oldLightList = oldLightList;
    }

    public List<Light> getNewLightList() {
        return newLightList;
    }

    public void setNewLightList(List<Light> newLightList) {
        this.newLightList = newLightList;
    }

    public List<Light> getReadyDeleteLightList() {
        return readyDeleteLightList;
    }

    public void setReadyDeleteLightList(List<Light> readyDeleteLightList) {
        this.readyDeleteLightList = readyDeleteLightList;
    }
}
