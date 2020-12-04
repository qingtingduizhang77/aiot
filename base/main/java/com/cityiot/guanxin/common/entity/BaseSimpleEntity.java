package com.cityiot.guanxin.common.entity;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.cityiot.guanxin.user.entity.Userview;

import io.swagger.annotations.ApiModelProperty;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;

@MappedSuperclass
public class BaseSimpleEntity extends OnlyIdEntity {
	@ApiModelProperty(value="创建时间",name="created",example="")
	@CnName("创建时间")
    @CreatedDate
    private Date created;
	 
    @ApiModelProperty(value="最后修改时间",name="lastmodi",example="")
    @CnName("最后修改时间")
    @LastModifiedDate
    private Date lastmodi;
    
    
    
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getLastmodi() {
		return lastmodi;
	}
	public void setLastmodi(Date lastmodi) {
		this.lastmodi = lastmodi;
	}
    
    
    
}
