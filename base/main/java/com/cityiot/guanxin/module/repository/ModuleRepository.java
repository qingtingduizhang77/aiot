package com.cityiot.guanxin.module.repository;

import com.cityiot.guanxin.common.repository.BaseRepository;
import com.cityiot.guanxin.module.entity.Module;
import com.cityiot.guanxin.module.entity.QModule;
import com.querydsl.core.types.EntityPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleRepository extends BaseRepository<Module> {
    private static final Logger log = LoggerFactory.getLogger(ModuleRepository.class);
   
    /**
     * 根据模块编码模糊查询所有的后代模块
     * @param entity
     * @return
     */
    public List<Module> getAllSonModule(Module entity){
        QModule qModule = QModule.module;
        return getQuery()
                .select(qModule)
                .from(qModule)
                .where(qModule.code.startsWith(entity.getCode()))
                .fetch();
    }

    // 获取所有的子模块
    public  List<Module> getAllChildModule(Module entity) {
        QModule qModule = QModule.module;
        return getQuery().select(qModule)
                .from(qModule)
                .where(qModule.parentId.eq((Long) entity.getId()))
                .orderBy(qModule.orderNumber.asc())
                .fetch();

    }
}
