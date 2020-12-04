package com.cityiot.guanxin.repository;


import com.cityiot.guanxin.common.repository.BaseRepository;
import com.cityiot.guanxin.entity.QSystemVariable;
import com.cityiot.guanxin.entity.QSystemVariableType;
import com.cityiot.guanxin.entity.SystemVariable;
import com.querydsl.core.types.EntityPath;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统变量配置 的数据访问
 * 注意 因为使用的是自定义的SwallBaseRepository,使用@Repository无效
 * @author aohanhe
 *
 */
@Service
public class SystemVariableRepository extends BaseRepository<SystemVariable> {


    public List<SystemVariable> getAllName(String typeName, Number[] arrNum) {
        QSystemVariable systemVariable = QSystemVariable.systemVariable;
        QSystemVariableType systemVariableType = QSystemVariableType.systemVariableType;
        return this.factory
                .select(systemVariable).from(systemVariable)
                .leftJoin(systemVariableType).on(systemVariable.vartype.eq(systemVariableType.id))
                .where(systemVariableType.name.eq(typeName)).where(systemVariable.id.in(arrNum)).fetch();
    }
}
