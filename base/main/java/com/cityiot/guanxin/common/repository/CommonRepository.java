package com.cityiot.guanxin.common.repository;


import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;

@Service
public class CommonRepository extends BaseRepository<Deviceinformation> {
    public JPAQueryFactory getFactory() {
        return this.factory;
    }
}
