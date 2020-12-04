package com.cityiot.guanxin.workOrder.vo;

import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.Id;

@CnName("事项数量")
@Entity
public class WorkNumInfoVo{
    @Id
    private long id;
    @CnName("待处理")
    private Integer todoNum;
    @CnName("处理中")
    private Integer doingNum;
    @CnName("已完成")
    private Integer finishedNum;
    @CnName("退单")
    private Integer chargebackNum;

    public Integer getDoingNum() {
        return doingNum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDoingNum(Integer doingNum) {
        this.doingNum = doingNum;
    }

    public Integer getFinishedNum() {
        return finishedNum;
    }

    public void setFinishedNum(Integer finishedNum) {
        this.finishedNum = finishedNum;
    }

    public Integer getTodoNum() {
        return todoNum;
    }

    public void setTodoNum(Integer todoNum) {
        this.todoNum = todoNum;
    }

    public Integer getChargebackNum() {
        return chargebackNum;
    }

    public void setChargebackNum(Integer chargebackNum) {
        this.chargebackNum = chargebackNum;
    }
}
