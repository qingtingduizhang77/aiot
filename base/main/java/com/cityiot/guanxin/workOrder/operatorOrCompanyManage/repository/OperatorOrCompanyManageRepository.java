package com.cityiot.guanxin.workOrder.operatorOrCompanyManage.repository;


import com.cityiot.guanxin.common.utils.Result;
import com.cityiot.guanxin.user.entity.QUser;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.EntityPath;

import swallow.framework.jpaquery.repository.SwallowRepository;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.QOperatorOrCompanyManage;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.OperatorOrCompanyManage;

/**
 * 运维人员/公司管理 的数据访问
 * 注意 因为使用的是自定义的SwallBaseRepository,使用@Repository无效
 * @author aohanhe
 *
 */
@Service
public class OperatorOrCompanyManageRepository extends SwallowRepository<OperatorOrCompanyManage>{

	@Override
	protected EntityPath<?> getMainTableExpression() {		
         return QOperatorOrCompanyManage.operatorOrCompanyManage;
	}

    public Result uploadHeadInfo(Long userId, String headImgUrl) {
        QOperatorOrCompanyManage qOperatorOrCompanyManage = QOperatorOrCompanyManage.operatorOrCompanyManage;
        this.factory.update(qOperatorOrCompanyManage).set(qOperatorOrCompanyManage.headPortrait, headImgUrl)
                .where(qOperatorOrCompanyManage.id.eq(userId)).execute();
        return Result.successResult();
    }

}
