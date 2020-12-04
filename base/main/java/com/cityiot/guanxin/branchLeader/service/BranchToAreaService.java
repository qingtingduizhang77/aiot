package com.cityiot.guanxin.branchLeader.service;

import com.cityiot.guanxin.area.entity.Area;
import com.cityiot.guanxin.area.entity.QArea;
import com.cityiot.guanxin.area.service.AreaService;
import com.cityiot.guanxin.branchLeader.entity.BranchToArea;
import com.cityiot.guanxin.branchLeader.entity.QBranchToArea;
import com.cityiot.guanxin.branchLeader.repository.BranchToAreaRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * yingzhao
 * 区域与负责人配置表
 */
@Service
public class BranchToAreaService extends BaseService<BranchToAreaRepository, BranchToArea> {
    private static final Logger log = LoggerFactory.getLogger(BranchToAreaService.class);

    @Autowired
    private AreaService areaService;

    //删除指定branchId的对象
    public void deleteItemByBranchId(Long branchId) {
        QBranchToArea branchToArea = QBranchToArea.branchToArea;
        getRepsitory()
                .deleteEntityByColumns(query -> {
                    return query.where(
                            branchToArea.branchId.eq(branchId));
                });

    }

    //根据区域管理人配置表id查询，返回branchids[]
    public Long[] getAllAreaIdsByBranchId(Long branchId){
        //条件查询
        List<BranchToArea> entitys = this.getAllItemByQuerybean(null,query -> {
            return query.where(QBranchToArea.branchToArea.branchId.eq(branchId));
        });
        //取出区域集合
        List<Long> arealist = entitys.stream().map(BranchToArea::getAreaId).collect(Collectors.toList());
        //集合转成数组
        Long[] re = arealist.toArray(new Long[arealist.size()]);
        return re;

    }

    public Long[] getAllAreaIdsIncludeParentByBranchId(Long branchId){
        //条件查询
        List<Area> areas = areaService.getAllItems(query ->
            query.leftJoin(QBranchToArea.branchToArea).on(QArea.area.id.eq(QBranchToArea.branchToArea.areaId))
                    .where(QBranchToArea.branchToArea.branchId.eq(branchId))
        );

        if (areas != null && areas.size() > 0) {

            List<Long> codes = new ArrayList<>();

            for (Area area : areas) {

                codes.add(area.getCode());// 添加负责人管理的区域代码

                List<Area> childAreas = areaService.getAllItems(query ->
                        query.where(QArea.area.parentId.eq(area.getId())
                                .or(QArea.area.parentCodeArr.like("%"+area.getCode()+",%"))));
                if (childAreas != null && childAreas.size() > 0) {
                    for (Area child : childAreas) {
                        codes.add(child.getCode());// 添加负责人管理的下属区域的编码
                    }
                }
            }

            //集合转成数组
            Long[] re = codes.toArray(new Long[codes.size()]);
            return re;
        }

        return null;

    }
}
