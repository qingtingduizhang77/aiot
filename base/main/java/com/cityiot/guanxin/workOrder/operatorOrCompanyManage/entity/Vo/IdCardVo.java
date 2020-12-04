package com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.Vo;

import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.IDCardAttachment;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.OperatorOrCompanyManage;

import java.util.List;

public class IdCardVo {

    private OperatorOrCompanyManage entity;

    private List<IDCardAttachment> positiveImages;

    private List<IDCardAttachment> deletePositiveImages;

    private List<IDCardAttachment> negativeImages;

    private List<IDCardAttachment> deleteNegativeImages;

    public OperatorOrCompanyManage getEntity() {
        return entity;
    }

    public void setEntity(OperatorOrCompanyManage entity) {
        this.entity = entity;
    }

    public List<IDCardAttachment> getPositiveImages() {
        return positiveImages;
    }

    public void setPositiveImages(List<IDCardAttachment> positiveImages) {
        this.positiveImages = positiveImages;
    }

    public List<IDCardAttachment> getDeletePositiveImages() {
        return deletePositiveImages;
    }

    public void setDeletePositiveImages(List<IDCardAttachment> deletePositiveImages) {
        this.deletePositiveImages = deletePositiveImages;
    }

    public List<IDCardAttachment> getNegativeImages() {
        return negativeImages;
    }

    public void setNegativeImages(List<IDCardAttachment> negativeImages) {
        this.negativeImages = negativeImages;
    }

    public List<IDCardAttachment> getDeleteNegativeImages() {
        return deleteNegativeImages;
    }

    public void setDeleteNegativeImages(List<IDCardAttachment> deleteNegativeImages) {
        this.deleteNegativeImages = deleteNegativeImages;
    }
}
