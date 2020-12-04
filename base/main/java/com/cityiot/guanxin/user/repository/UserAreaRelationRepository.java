package com.cityiot.guanxin.user.repository;

import com.cityiot.guanxin.area.entity.Area;
import com.cityiot.guanxin.area.entity.QArea;
import com.cityiot.guanxin.common.repository.BaseRepository;
import com.cityiot.guanxin.user.entity.QUserAreaReleation;
import com.cityiot.guanxin.user.entity.UserAreaRelation;
import com.querydsl.core.types.EntityPath;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAreaRelationRepository extends BaseRepository<UserAreaRelation> {
   
    /** 通过用户Id获取用户所有的区域
     * @param userId
     * @return
     */
    public List<Area> getAllAreaByUserId(long userId) {
        QUserAreaReleation qUserAreaReleation = QUserAreaReleation.userAreaReleation;
        QArea qArea = QArea.area;
        List<Area> areaList = factory
                .select(qArea)
                .from(qUserAreaReleation)
                .innerJoin(qArea)
                .on(qUserAreaReleation.areaId.eq(qArea.id))
                .where(qUserAreaReleation.userId.eq(userId))
                .fetch();
        return areaList;
    }
}
