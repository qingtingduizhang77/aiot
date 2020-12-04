package com.cityiot.guanxin.app.entity;

import javax.persistence.Entity;

import com.cityiot.guanxin.area.entity.Area;
import com.cityiot.guanxin.common.entity.BaseEntity;

import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;

@Entity
public class Test  extends BaseEntity {
	
	    @CnName("区域名称")
	    private String name;
	  
	    @CnName("区域类型")
	    private int areaType;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAreaType() {
			return areaType;
		}

		public void setAreaType(int areaType) {
			this.areaType = areaType;
		}
	    
	    
	    

}
