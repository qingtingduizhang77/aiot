package com.cityiot.guanxin.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 用户验证码处理器
 * @author aohanhe
 *
 */
@Service
public class UserCheckCodeService {
	// 是否固定验证码  如果设置了，验证码为1234
	@Value("${gx.isFixCheckCode:false}")
	private boolean isFixCheckCode;
	/**
	 * 验证码无效时间的时长，以秒为单位
	 */
	@Value("${gx.checkCodeInValidTime:60}")
	private int checkCodeInValidTime;
	
	@Value("${gx.smsCodeCanRefreshTime:30}")
	private int smsCodeInValidTime;
		
	/**
	 * 当前激活的配置表
	 */
	@Value("${spring.profiles.active:prod}")
	private String activeProfile;
	
	private Random rnd=new Random(System.currentTimeMillis());
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Autowired
	private RedisConnectionFactory redisConnFactory;
	
	
	private RedisTemplate<String, Long> redisAuthFailCountTemplate;
	
	@PostConstruct
	public void init() {
		redisAuthFailCountTemplate=new RedisTemplate<>();
		redisAuthFailCountTemplate.setConnectionFactory(this.redisConnFactory);
		redisAuthFailCountTemplate.afterPropertiesSet();
	}
	
	/**
	 * 增长用户认证失败次数
	 * @param userId
	 * @param isMangerUser
	 */
	public void addUserAuthFailCount(long userId,boolean isMangerUser) {
		String key=getUserKey(userId, isMangerUser);
		var value=redisAuthFailCountTemplate.opsForValue().get(key);
		if(value==null)	value=0l;
		
		value+=1;
			
		redisAuthFailCountTemplate.opsForValue().set(key, value);;
	}
	/**
	 * 设置用户需要验证
	 * @param userId
	 * @param isMangerUser
	 */
	public void setUserWantCheckCode(long userId,boolean isMangerUser) {
				
		redisAuthFailCountTemplate.opsForValue().increment(getUserKey(userId,isMangerUser), 3);
	}
	
	/**
	 * 添空用户验证失败次数
	 * @param userId
	 * @param isMangerUser
	 */
	public void clearUserAuthFailCount(long userId,boolean isMangerUser) {
		redisAuthFailCountTemplate.opsForValue().set(getUserKey(userId,isMangerUser), 0l);
	}
	
	/**
	 * 取得用户在redis中存贮的key值
	 * @param userId
	 * @param isMangerUser
	 * @return
	 */
	private String getUserKey(long userId,boolean isMangerUser) {
		return String.format("FailKey%s_%d", isMangerUser?"M":"C",userId);
	}
	
	
	
	/**
	 * 指定用户是否需要验证码
	 * @param userId
	 * @param isMangerUser
	 * @return
	 */
	public boolean isUserWantCheckCode(long userId,boolean isMangerUser) {
		Assert.isTrue(userId>0,"参数userId不能小于等于0");
		var value=redisAuthFailCountTemplate.opsForValue().get(getUserKey(userId, isMangerUser));
		
		if(value==null) return false;
		
		return value>=3;
	}
	
	
	/**
	 * 创建一个唯一codekey
	 * @return
	 */
	public String createChechCodeKey() {
		var key=String.format("CKey%d_%d", System.currentTimeMillis(),rnd.nextInt(1000));
		return key;
	}
	
	/**
	 * 保存验证码到缓存
	 * @param key
	 * @param checkCode
	 * @param checkCode
	 */
	public void putUserCheckCode(String key,String checkCode) {
		
		Assert.hasText(key,"参数key不能为空");		
		redisTemplate.opsForValue().set(key, checkCode,this.checkCodeInValidTime,TimeUnit.SECONDS);
	}
	
	/**
	 * 写入短信验证失效控制key
	 * @param key
	 */
	public void putPhoneValidKey(String key) {
		Assert.hasText(key,"参数key不能为空");	
		redisTemplate.opsForValue().set(key, "1",this.smsCodeInValidTime,TimeUnit.SECONDS);
	}
	
	/**
	 * 是否有短信验证失效控制key
	 * @param key
	 */
	public boolean hasPhoneKey(String key) {
		Assert.hasText(key,"参数key不能为空");	
		var res= redisTemplate.opsForValue().get(key);
		if(!StringUtils.hasText(res)) return true;
		return false;
	}
	
	/**
	 * 取得用户当前的验证码
	 * @param key
	 * @param isCleanCode
	 * @return
	 */
	public String getCurUserCheckCode(String key,boolean isCleanCode) {
				
		Assert.hasText(key,"参数key不能为空");
		var res= redisTemplate.opsForValue().get(key);
		
		if(!StringUtils.hasText(res)) return null;
		// 使用过的验证码立即清理
		if(isCleanCode)
			redisTemplate.opsForValue().set(key, "",this.checkCodeInValidTime,TimeUnit.SECONDS);
		
		return res;
	}
	
	/**
	 * 当前是否在开发模式下
	 * @return
	 */
	private boolean isInDevMode() {
		return this.activeProfile.equals("dev");
	}
	
	/**
	 * 保存手机验证码
	 * @param phone
	 * @param checkCode
	 */
	public void putPhoneCheckCode(String phone,String checkCode) {		
		Assert.hasText(phone,"参数phone不允许为空");
		Assert.hasText(checkCode,"参数checkCode不允许为空");
		
		Assert.isTrue(this.hasPhoneKey(phone),"不允许高频发送短信验证码");
		
		
		if(this.isFixCheckCode) checkCode="1234";
		this.putUserCheckCode("CkSmsCode_"+phone, checkCode);
		this.putPhoneValidKey(phone);
	}
	

	
	/**
	 * 取得短信验证码
	 * @param phone
	 * @param phone
	 * @return
	 */
	public String getPhoneCheckCode(String phone) {
		return this.getCurUserCheckCode("CkSmsCode_"+phone,false);
	}
	
	

}
