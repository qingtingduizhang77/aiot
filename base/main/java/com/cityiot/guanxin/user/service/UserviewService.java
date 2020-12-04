package com.cityiot.guanxin.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cityiot.guanxin.user.entity.QUserview;
import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.user.repository.UserviewRepository;

import swallow.framework.service.BaseService;

@Service
public class UserviewService extends BaseService<UserviewRepository, Userview>{
	 private static final Logger log = LoggerFactory.getLogger(UserviewService.class);
	 
	 public List<Userview> getUserviewByType(int type) {
		  List<Userview> list=null;
		  QUserview qUserview=QUserview.userview;
		  
		
		  
		 
	    
//	        list=getRepsitory().findEntitysByColumns(query -> {
//	    	   
//	    	   //初始化组装条件(类似where 1=1)
//	 		    Predicate predicate = qUserview.isNotNull().or(qUserview.isNull());
//	 		  //执行动态条件拼装
//	 	       predicate = type == 1 ? predicate : ExpressionUtils.and(predicate, qUserview.type.eq(1));
//	 	       predicate = type == 2 ? predicate : ExpressionUtils.or(predicate, qUserview.type.eq(2));
//	              return query.where(predicate).orderBy(qUserview.username.desc());
//	          });
	       
		  
		  if(type==1 ||type==2 )
		  {
			
			  list=getRepsitory().findEntitysByColumns(query -> {
	              return query.where(qUserview.type.eq(type),qUserview.disable.eq(0)).orderBy(qUserview.username.desc());
	          });
		  }
		  else
		  {
			  list=getRepsitory().getAllItems(query -> query);
		  }
		 
		  return list;
		  
	    }
	  
	  
	  /** 根据用户名查找用户
	     * @param username
	     * @return
	     */
	    public Userview getUserByAccount(String account){
	    	Userview userview = null;
	    	  QUserview qUserview=QUserview.userview;
	    	  userview = getRepsitory()
	                .findOneEntityByColumns(query -> {
	                    return query.where(qUserview.account.eq(account));
	                });
	        return userview;
	    }
	    
	  
	    
}
