package com.cityiot.guanxin.app.repository;

import com.cityiot.guanxin.app.entity.AppVersion;
import com.cityiot.guanxin.app.entity.QAppVersion;
import com.cityiot.guanxin.common.repository.BaseRepository;

import com.cityiot.guanxin.message.entity.QEMessageCenter;
import org.springframework.stereotype.Service;

@Service
public class AppVersionRepository extends BaseRepository<AppVersion> {

    public void publishApp(long id, Integer appPlatformType){
        QAppVersion qAppVersion = QAppVersion.appVersion1;
        // 发布
        this.factory.update(qAppVersion)
                .set(qAppVersion.publishStatus, 1)
                .where(qAppVersion.id.eq(id).and(qAppVersion.appPlatformType.eq(appPlatformType))).execute();
        // 把另外版本已发布的改为未发布
        this.factory.update(qAppVersion)
                .set(qAppVersion.publishStatus, 0)
                .where(qAppVersion.id.ne(id).and(qAppVersion.appPlatformType.eq(appPlatformType))).execute();
    }
}
