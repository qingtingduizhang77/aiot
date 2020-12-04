package com.cityiot.guanxin.config;

import java.util.Optional;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.user.service.UserviewService;

@Component
public class EntityAuditorAware implements AuditorAware<Long> {
	@Autowired
   	private UserviewService userviewService;
	
    @Override
    public Optional<Long> getCurrentAuditor() {
        Subject subject =null;
        try{
            subject = SecurityUtils.getSubject();
        }catch (Exception ex){

        }

        Userview user =null;// new Userview();
        if(subject!=null)
        {
        	 if(subject.isAuthenticated()) {
        		 
        		 if(subject.getPrincipals()!=null)
        		 {
        			  PrincipalCollection principalCollection = subject.getPrincipals();
        			  
        			  if(principalCollection.getPrimaryPrincipal()!=null && principalCollection.getPrimaryPrincipal() instanceof Userview)
        			  {
        				  user = (Userview) principalCollection.getPrimaryPrincipal();
        			  }
                     
                      
        		 }
               
             }
        }
        
        if(user ==null && userviewService!=null)
        {
        	user= userviewService.getUserByAccount("admin");
        }
        else if(user ==null && userviewService==null)
        {
        	 return Optional.of(null);
        }
        
       if(user!=null)
       {
    	   return Optional.of(user.getId());
       }
       return Optional.of(null);
    }
}
