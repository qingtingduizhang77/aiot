package com.cityiot.guanxin.module.service;

import com.cityiot.guanxin.module.entity.Module;
import com.cityiot.guanxin.module.entity.QModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swallow.framework.exception.SwallowException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ModuleExtendsService {
    // 日志对象
    private static final Logger log = LoggerFactory.getLogger(ModuleExtendsService.class);
    @Autowired
    private ModuleService moduleService;
    /**
     *
     * @param entity
     * @return
     * @throws SwallowException
     */
    public Module addModule(Module entity) throws SwallowException {
        if(entity.getParentId()==0L) {
            var count = moduleService.getRepsitory()
                    .count(QModule.module.parentId.eq(0L));
            var suffix = String.format("%03d", count + 1);
            entity.setCode("module"+suffix);
        }
        else {
            // 获取父模块编码
            var parentModule = moduleService.getItemById(entity.getParentId());
            var parentCode = parentModule.getCode();
            // 获取父模块下的子模块数
            var count = moduleService.getRepsitory()
                    .count(QModule.module.parentId.eq(entity.getParentId()));
            // 根据传入的模块排序号一起计算模块编码
            // 计算后缀
            var suffix = String.format("%03d", count + 1);
            String Code = parentCode + suffix;
            entity.setCode(Code);
        }
        return moduleService.insertItem(entity);
    }

    /**
     * @param entity
     * @return
     * @throws SwallowException
     */
    public Module updateModule(Module entity) throws SwallowException {

        Module result = null;
        // 获取原来的模块信息
        var module = moduleService.getItemById(entity.getId());
        entity.setLastmodi(new Date());

        if(entity.getParentId()!=0) {
            // 获取新父模块
            var newParentModule = moduleService.getItemById(entity.getParentId());
            // 获取旧父模块
            var oldParentModule = moduleService.getItemById(module.getParentId());
            // 当改变父模块时
            if (newParentModule.getId() != oldParentModule.getId()) {
                // 计算新的模块编码
                var newParentModuleSonCounts = moduleService.getRepsitory()
                        .count(QModule.module.parentId.eq(newParentModule.getId()));

                var newCode = newParentModule.getCode() + String.format("%03d", newParentModuleSonCounts + 1);
                entity.setCode(newCode);
                // 更新后代模块编码
                updateCode(module,newCode);

                // 更新自己
                // 将新的值合并到原来对象当中
                BeanUtils.copyProperties(entity,module);
                result = moduleService.updateItem(entity);

                // 获取新父模块的子模块
                var childModuleList = moduleService.getRepsitory()
                        .getAllChildModule(newParentModule);
                var i = 1;
                for (Module childModule : childModuleList) {
                    var Code = newParentModule.getCode() + String.format("%03d", i);
                    childModule.setCode(Code);
                    updateCode(childModule,Code);
                    i++;
                }
            }
            // 当只改变改变模块排序号时
            else if(module.getOrderNumber()!=entity.getOrderNumber()) {

                // 更新自己
                // 将新的值合并到原来对象当中
                BeanUtils.copyProperties(entity,module);
                result =
                        moduleService.updateItem(entity);
                // 获取父模块
                var parentModule = moduleService.getItemById(module.getParentId());

                // 获取新父模块的子模块
                var childModuleList = moduleService.getRepsitory()
                        .getAllChildModule(parentModule);
                var i = 1;
                for (Module childModule : childModuleList) {
                    var Code = parentModule.getCode() + String.format("%03d", i);
                    updateCode(childModule,Code);
                    childModule.setCode(Code);
                    moduleService.updateItem(childModule);
                    i++;
                }
            }
            else {

                // 将新的值合并到原来对象当中
                BeanUtils.copyProperties(entity,module);
                result = moduleService.updateItem(entity);
            }
        }else{
            entity.setParentId(module.getParentId());
            // 将新的值合并到原来对象当中
            BeanUtils.copyProperties(entity,module);
            result = moduleService.updateItem(entity);
        }


        return result;
    }

    private void updateCode(Module module,String newCode) {

        // 获取模块所有后代模块
        var sonModuleList = getAllSonModule(module);
        // 没有要修改的后代模块
        if(null==sonModuleList) return;
        sonModuleList.forEach(sonModule -> {
            // 不更改自身
            if (sonModule.getId() == module.getId()) return;
            // 替换原有模块前缀
            sonModule.setCode(sonModule.getCode().replace(module.getCode(), newCode));
            try {
                moduleService.updateItem(sonModule);
            } catch (SwallowException e) {
                log.error("更新模块出错", e);
            }
        });
    }

    /**
     * 获取所有的后代模块
     * @param module
     */
    private List<Module> getAllSonModule(Module module) {
        List<Module> sonModuleList = new ArrayList<>();
        // 先获取第一层子模块
        var childModuleList = moduleService.getRepsitory()
                .getAllChildModule(module);

        for(int i=0;childModuleList.size()>0;){
            var childModule = childModuleList.get(0);
            var list = moduleService.getRepsitory().getAllChildModule(childModule);
            childModuleList.addAll(list);
            sonModuleList.add(childModule);
            childModuleList.remove(i);
        }
        return sonModuleList;
    }
}
