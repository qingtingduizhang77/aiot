package com.cityiot.guanxin.branchLeader.service;

import com.cityiot.guanxin.branchLeader.entity.BranchToDeviceModel;
import com.cityiot.guanxin.branchLeader.entity.QBranchToDeviceModel;
import com.cityiot.guanxin.branchLeader.repository.BranchToDeviceModelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * yingzhao
 * 设备型号与负责人配置表
 */
@Service
public class BranchToDeviceModelService extends BaseService<BranchToDeviceModelRepository, BranchToDeviceModel> {
    private static final Logger log = LoggerFactory.getLogger(BranchToDeviceModelService.class);

    //删除指定branchId的对象
    public void deleteItemByBranchId(Long branchId) {
        QBranchToDeviceModel branchToDeviceModel = QBranchToDeviceModel.branchToDeviceModel;
        getRepsitory()
                .deleteEntityByColumns(query -> {
                    return query.where(
                            branchToDeviceModel.branchId.eq(branchId));
                });

    }

    public Long[] getAllDeviceModelIdsByBranchId(Long branchId) {
        //条件查询
        List<BranchToDeviceModel> entitys = getRepsitory().findEntitysByColumns(query -> {
            return query.where(QBranchToDeviceModel.branchToDeviceModel.branchId.eq(branchId));
        });
        //取出区域集合
        List<Long> modelList = entitys.stream().map(BranchToDeviceModel::getDeviceModelId).collect(Collectors.toList());
        //集合转成数组
        Long[] re = modelList.toArray(new Long[modelList.size()]);
        return re;

    }
}
