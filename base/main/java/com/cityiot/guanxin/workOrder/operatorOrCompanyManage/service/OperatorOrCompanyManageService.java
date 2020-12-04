package com.cityiot.guanxin.workOrder.operatorOrCompanyManage.service;


import com.cityiot.guanxin.common.utils.PasswordHelper;
import com.cityiot.guanxin.common.utils.Result;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.IDCardAttachment;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.OperatorOrCompanyManage;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.QOperatorOrCompanyManage;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.repository.OperatorOrCompanyManageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swallow.framework.exception.SwallowException;
import swallow.framework.service.BaseService;

import java.util.List;

/**
 * OperatorOrCompanyManage 数据Service
 * @author aohanhe
 *
 */
@Service
public class OperatorOrCompanyManageService extends BaseService<OperatorOrCompanyManageRepository, OperatorOrCompanyManage>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(OperatorOrCompanyManageService.class);

	@Autowired
    private OperatorOrCompanyManageRepository repository;

	@Autowired
    private IDCardAttachmentService idCardAttachmentService;

    public OperatorOrCompanyManage getItemByPhoneNumber(String phoneNumber) {
        QOperatorOrCompanyManage qOperatorOrCompanyManage = QOperatorOrCompanyManage.operatorOrCompanyManage;
        return this.getItem(query -> query.where(qOperatorOrCompanyManage.phone.eq(phoneNumber)));
    }

    public Result updateUseStatus(OperatorOrCompanyManage user) {
        this.updateItem(user);
        return Result.successResult();
    }

    @Transactional(rollbackFor = Exception.class)
    public Result uploadHeadInfo(Long userId, String headImgUrl) {
        return this.repository.uploadHeadInfo(userId, headImgUrl);
    }



    public OperatorOrCompanyManage insertItem(OperatorOrCompanyManage entity
            ,List<IDCardAttachment> positiveImages
            ,List<IDCardAttachment> negativeImages) throws SwallowException {
    	if(entity.getPassword()!=null)
    	{
    		 entity.setPassword(PasswordHelper.encryptPassword(entity.getPassword()));
    	}
    	else
    	{ 
    		  entity.setPassword(PasswordHelper.encryptPassword("123456"));
    	}
      
        entity = super.insertItem(entity);

        addPatrolPictures(entity.getId(),positiveImages,1);
        addPatrolPictures(entity.getId(),negativeImages,0);
        return entity;
    }

    public OperatorOrCompanyManage insertItem(OperatorOrCompanyManage entity) throws SwallowException {
        if(entity.getPassword()!=null)
        {
            entity.setPassword(PasswordHelper.encryptPassword(entity.getPassword()));
        }
        else
        {
            entity.setPassword(PasswordHelper.encryptPassword("123456"));
        }
        entity = super.insertItem(entity);
        return entity;
    }

    public OperatorOrCompanyManage updateItem(OperatorOrCompanyManage entity
            ,List<IDCardAttachment> positiveImages
            ,List<IDCardAttachment> deletePositiveImages
            ,List<IDCardAttachment> negativeImages
            ,List<IDCardAttachment> deleteNegativeImages) throws SwallowException {
    	
        addPatrolPictures(entity.getId(),positiveImages,1);
        addPatrolPictures(entity.getId(),negativeImages,0);

        deleteCarBrandImages(deletePositiveImages);
        deleteCarBrandImages(deleteNegativeImages);
        OperatorOrCompanyManage oc=super.updateItem(entity);
        
        if(oc.getPassword()==null)
        	{
        	   oc.setPassword(PasswordHelper.encryptPassword("123456"));
        	}
        	

            return oc;
    }



    public void addPatrolPictures(long id, List<IDCardAttachment> patrolPictures, int type) throws SwallowException {
        if(null!=patrolPictures && patrolPictures.size()!=0) {
            for(var images:patrolPictures){
                IDCardAttachment idCardAttachment = images;
                idCardAttachment.setOperatorId(id);
                idCardAttachment.setImgType(type);
                idCardAttachmentService.insertItem(idCardAttachment);
            }
        }
    }

    public void deleteCarBrandImages(List<IDCardAttachment> deletepatrolPictures) {
        if(null!=deletepatrolPictures && deletepatrolPictures.size()!=0) {
            for(var images:deletepatrolPictures){
                IDCardAttachment idCardAttachment = images;
                idCardAttachmentService.deleteItemById(idCardAttachment.getId());
            }
        }
    }
}
