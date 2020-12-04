package com.cityiot.guanxin.app.service;

import com.cityiot.guanxin.app.entity.AppVersion;
import com.cityiot.guanxin.app.entity.QAppVersion;
import com.cityiot.guanxin.app.repository.AppVersionRepository;
import com.cityiot.guanxin.common.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swallow.framework.service.BaseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppVersionService extends BaseService<AppVersionRepository, AppVersion> {


    public Result getItemsByAppInnerVersion(Integer appPlatformType, Integer appInnerVersoin, int appType) {
        QAppVersion qAppVersion = QAppVersion.appVersion1;
        List<AppVersion> appVersions = this.getAllItems(query -> query
                .where(qAppVersion.appPlatformType.eq(appPlatformType))
                .where(qAppVersion.appInnerVersoin.gt(appInnerVersoin))
                .where(qAppVersion.appType.eq(appType))
                .orderBy(qAppVersion.appVersion.desc()));
        return Result.successResult(appVersions);
    }

    public Result getItemsByAppVersion(Integer appPlatformType, String appVersion, int appType) {
        QAppVersion qAppVersion = QAppVersion.appVersion1;
        List<AppVersion> appVersions = this.getAllItems(query-> query
                .where(qAppVersion.appPlatformType.eq(appPlatformType))
                .where(qAppVersion.appVersion.gt(appVersion))
                .where(qAppVersion.appType.eq(appType))
                .orderBy(qAppVersion.appVersion.desc()));
        return Result.successResult(appVersions);
    }

    @Transactional(rollbackFor = Exception.class)
    public void publishApp(long id, Integer appPlatformType){
        this.getRepsitory().publishApp(id, appPlatformType);
    }

    public Map<String, Object> getPublishList(){
        Map<String, Object> map = new HashMap<>();
        QAppVersion qAppVersion = QAppVersion.qAppVersion;
        List<AppVersion> appVersions = this.getAllItems(query -> query.where(qAppVersion.publishStatus.eq(1)));
        if (appVersions != null && appVersions.size() > 0){
            for (var appVersion : appVersions){
                if (appVersion.getAppPlatformType() == 1) {
                    map.put("ios", getAppPublishInfo(appVersion));
                }else if(appVersion.getAppPlatformType() == 2){
                    map.put("android", getAppPublishInfo(appVersion));
                }
            }
        }
        return map;
    }

    private Map<String, Object> getAppPublishInfo(AppVersion appVersion){
        Map<String, Object> data = new HashMap<>();
        data.put("version", appVersion.getAppVersion());
        data.put("is_hotUpdate", appVersion.getIsHotUpdate());
        data.put("is_force_update", appVersion.getForceUpdate());
        data.put("description", appVersion.getUpdateDescription());
        data.put("hot_update_download", appVersion.getHotUpdateUrl());
        data.put("full_update_download", appVersion.getAppFileUrl());
        data.put("size", appVersion.getFileSize());
        return data;
    }
}
