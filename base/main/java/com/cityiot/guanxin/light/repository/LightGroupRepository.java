package com.cityiot.guanxin.light.repository;

import com.cityiot.guanxin.common.repository.BaseRepository;
import com.cityiot.guanxin.light.entity.LightGroup;
import com.cityiot.guanxin.light.entity.QLightGroup;
import com.querydsl.core.types.EntityPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LightGroupRepository extends BaseRepository<LightGroup> {
    // 日志对象
    private static final Logger log = LoggerFactory.getLogger(LightGroupRepository.class);
   

    public void updateStatus(long groupId, int timeSwitchStatus) {
        QLightGroup lightGroup = QLightGroup.lightGroup;
        this.factory.update(lightGroup)
                .set(lightGroup.timeSwitchStatus, timeSwitchStatus).where(lightGroup.id.eq(groupId)).execute();
    }
}
