package com.cityiot.guanxin.user.service;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cityiot.guanxin.common.utils.PasswordHelper;
import com.cityiot.guanxin.permission.entity.Permission;
import com.cityiot.guanxin.user.entity.QUser;
import com.cityiot.guanxin.user.entity.User;
import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.user.repository.UserRepository;

import swallow.framework.exception.SwallowException;
import swallow.framework.service.BaseService;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

@Service
public class UserService extends BaseService<UserRepository, User> {
    // 日志对象
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    /** 根据用户token查找用户
     * @param username
     * @param password
     * @return
     */
    public User getUserBytoken(String token){
        User user = null;
        QUser qUser = QUser.user;
        user = getRepsitory()
                .findOneEntityByColumns(query -> {
                    return query.where(qUser.token.eq(token));
                });
        return user;
    }
    
    /** 根据用户名和密码查找用户
     * @param username
     * @param password
     * @return
     */
    public User getUserByUsernameAndPassword(String username,String password){
        User user = null;
        QUser qUser = QUser.user;
        user = getRepsitory()
                .findOneEntityByColumns(query -> {
                    return query.where(qUser.username.eq(username)
                    .and(qUser.password.eq(password)));
                });
        return user;
    }
    
    
    

    /** 根据用户名查找用户
     * @param username
     * @return
     */
    public User getUserByUsername(String username){
        User user = null;
        QUser qUser = QUser.user;
        user = getRepsitory()
                .findOneEntityByColumns(query -> {
                    return query.where(qUser.username.eq(username));
                });
        return user;
    }

    public void updatePassword(String oldPassword,String newPassword){
        Subject subject = SecurityUtils.getSubject();
        Userview accountinfo = (Userview)subject.getPrincipals().getPrimaryPrincipal();
        
        User user = getItemById(accountinfo.getDid());
        oldPassword = PasswordHelper.encryptPassword(oldPassword);
        if(!oldPassword.equals(user.getPassword())){
           throw new SwallowException("原密码不正确");
        }
        newPassword = PasswordHelper.encryptPassword(newPassword);
        user.setPassword(newPassword);
        updateItem(user);
    }

    /** 根据用户Id获取用户的所有权限
     * @param userId
     * @return
     */
    public List<Permission> getAllPermissionByUserId(long userId) {
        return getRepsitory().getAllPermissionByUserId(userId);
    }

    @Override
    public PageListData<User> getAllItemPageByQuerybean(BasePageQueryBean queryBean) {
        return super.getAllItemPageByQuerybean(queryBean);
    }
}
