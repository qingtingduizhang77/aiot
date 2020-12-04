package com.cityiot.guanxin.area.service;

import com.cityiot.guanxin.area.entity.Area;
import com.cityiot.guanxin.area.entity.QArea;
import com.cityiot.guanxin.area.repository.AreaRepository;
import com.cityiot.guanxin.deviceGroup.entity.DeviceGroup;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import swallow.framework.exception.SwallowException;
import swallow.framework.service.BaseService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AreaService extends BaseService<AreaRepository, Area> {
    private static final Logger log = LoggerFactory.getLogger(AreaService.class);
    /**
     * 修改人：应钊
     *新增区域对象：
     * @param entity
     * @return
     * @throws SwallowException
     */
    @Override
    public Area insertItem(Area entity) throws SwallowException {
        this.setParentIdArr(entity);
        return super.insertItem(entity);
    }

    /**
     * 更新区域对象
     * @param entity
     * @return
     * @throws SwallowException
     */
    @Override
    public Area updateItem(Area entity) throws SwallowException {
        this.setParentIdArr(entity);
        var result = super.updateItem(entity);
        return result;
    }


    /**
     * 获取所有的后代模块
     * @param Area
     */
    private List<Area> getAllSonArea(Area Area) {
        List<Area> sonAreaList = new ArrayList<>();
        // 先获取第一层子模块
        var childAreaList = getRepsitory()
                .getAllChildArea(Area);

        for(int i=0;childAreaList.size()>0;){
            var childArea = childAreaList.get(0);
            var list = getRepsitory().getAllChildArea(childArea);
            childAreaList.addAll(list);
            sonAreaList.add(childArea);
            childAreaList.remove(i);
        }
        return sonAreaList;
    }

    public Area getAreaByCode(Long code){
        QArea qArea = QArea.area;
        List<Area> result =  getRepsitory()
                .getAllItems(query -> {
                    return query.where(qArea.code.eq(code));
                });
        if(result != null && result.size()>0){
            return result.get(0);
        }else{
            return null;
        }
    }

    public void setParentIdArr(Area item){
        if (item.getParentId() > 0) {
            Area area = this.getItemById(item.getParentId());
            if (area != null){
                if (StringUtils.isNotEmpty(area.getParentCodeArr())) {
                    item.setParentCodeArr(area.getParentCodeArr() + "," + area.getCode());
                }else{
                    item.setParentCodeArr("" + area.getCode());
                }
            }
        }else {
            item.setParentCodeArr(null);
        }
    }
}
