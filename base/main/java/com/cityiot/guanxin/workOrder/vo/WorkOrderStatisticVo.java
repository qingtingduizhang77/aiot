package com.cityiot.guanxin.workOrder.vo;

import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.Id;

@CnName("工单数量统计")
@Entity
public class WorkOrderStatisticVo {
    @Id
    private long id;

    @CnName("工单类型")
    private Integer workOrderType;

    @CnName("已完成工单数")
    private Integer finishedNum;

    @CnName("未完成工单数")
    private Integer unfinishedNum;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getWorkOrderType() {
        return workOrderType;
    }

    public void setWorkOrderType(Integer workOrderType) {
        this.workOrderType = workOrderType;
    }

    public Integer getFinishedNum() {
        if(this.finishedNum == null){
            return 0;
        }
        return finishedNum;
    }

    public void setFinishedNum(Integer finishedNum) {
        this.finishedNum = finishedNum;
    }

    public Integer getUnfinishedNum() {
        if(this.unfinishedNum == null){
            return 0;
        }
        return unfinishedNum;
    }

    public void setUnfinishedNum(Integer unfinishedNum) {
        this.unfinishedNum = unfinishedNum;
    }
}
