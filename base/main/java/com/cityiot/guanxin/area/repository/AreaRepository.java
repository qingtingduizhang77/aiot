package com.cityiot.guanxin.area.repository;

import com.cityiot.guanxin.area.entity.Area;
import com.cityiot.guanxin.area.entity.QArea;
import com.cityiot.guanxin.common.repository.BaseRepository;
import com.querydsl.core.types.EntityPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaRepository extends BaseRepository<Area> {
    private static final Logger log = LoggerFactory.getLogger(AreaRepository.class);



    // 获取所有的子模块
    public List<Area> getAllChildArea(Area entity) {
        QArea qArea = QArea.area;
        return getQuery().select(qArea)
                .from(qArea)
                .where(qArea.parentId.eq((Long) entity.getId()))
                .orderBy(qArea.orderNumber.asc())
                .fetch();

    }
}
