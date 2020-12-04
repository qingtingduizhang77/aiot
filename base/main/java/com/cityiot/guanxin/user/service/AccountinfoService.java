package com.cityiot.guanxin.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cityiot.guanxin.user.entity.Accountinfo;
import com.cityiot.guanxin.user.entity.QAccountinfo;
import com.cityiot.guanxin.user.entity.QUser;
import com.cityiot.guanxin.user.entity.User;
import com.cityiot.guanxin.user.repository.AccountinfoRepository;

import swallow.framework.service.BaseService;

@Service
public class AccountinfoService extends BaseService<AccountinfoRepository, Accountinfo> {
	  // 日志对象
    private static final Logger log = LoggerFactory.getLogger(AccountinfoService.class);

    /** 根据用户PCtoken查找用户
     * @param pctoken
     * @return
     */
    public Accountinfo getUserByPCToken(String pctoken){
    	Accountinfo accountinfo = null;
        QAccountinfo qAccountinfo = QAccountinfo.accountinfo;
        accountinfo = getRepsitory()
                .findOneEntityByColumns(query -> {
                    return query.where(qAccountinfo.pctoken.eq(pctoken));
                });
        return accountinfo;
    }
    
    /** 根据用户apptoken查找用户
     * @param apptoken
     * @return
     */
    public Accountinfo getUserByAPPToken(String apptoken){
    	Accountinfo accountinfo = null;
        QAccountinfo qAccountinfo = QAccountinfo.accountinfo;
        accountinfo = getRepsitory()
                .findOneEntityByColumns(query -> {
                    return query.where(qAccountinfo.apptoken.eq(apptoken));
                });
        return accountinfo;
    }
    
    
    /** 根据用户名查找用户
     * @param username
     * @return
     */
    public Accountinfo getUserByAccount(String account){
    	Accountinfo accountinfo = null;
        QAccountinfo qAccountinfo = QAccountinfo.accountinfo;
        accountinfo = getRepsitory()
                .findOneEntityByColumns(query -> {
                    return query.where(qAccountinfo.account.eq(account));
                });
        return accountinfo;
    }
    
    
    /** 根据用户名查找用户
     * @param phone
     * @return
     */
    public Accountinfo getUserByPhone(String phone){
    	Accountinfo accountinfo = null;
        QAccountinfo qAccountinfo = QAccountinfo.accountinfo;
        accountinfo = getRepsitory()
                .findOneEntityByColumns(query -> {
                    return query.where(qAccountinfo.phone.eq(phone));
                });
        return accountinfo;
    }

}
