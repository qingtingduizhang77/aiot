package com.cityiot.guanxin.warning.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import com.cityiot.guanxin.common.repository.BaseRepository;
import com.cityiot.guanxin.warning.entity.DeviceWarningMsg;

@Service
public class DeviceWarningRepository extends BaseRepository<DeviceWarningMsg> {

	/**
	 * 设备监控大屏统计
	 * @return
	 */
	public Map<String,Object> getIndexDeviceWarningStatistics(){
		String sql ="SELECT  warning_evel warningevel,COUNT(id) AS countval FROM  device_warning_msg WHERE   state=1  GROUP BY warning_evel ";
		 Map<String,Object> re=new HashMap<String, Object>();
		 re.put("To_do_urgent", 0);  //未处理紧急
		 re.put("To_do_important", 0);//未处理重要
		 re.put("To_do_commonly", 0);//未处理一般
		
		 
		Query query = this.em.createNativeQuery(sql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);  
		List<Map<String,Object>> list=query.getResultList();
		if(list!=null && list.size()>0)
		{
			for(Map<String,Object> map:list)
			{
				if(map.get("warningevel").toString().equals("10"))
				{
					 re.put("To_do_urgent", map.get("countval"));
				}
				else if(map.get("warningevel").toString().equals("20"))
				{
					 re.put("To_do_important", map.get("countval"));
				}
				else if(map.get("warningevel").toString().equals("30"))
				{
					 re.put("To_do_commonly", map.get("countval"));
				}
			}
		}
		
		return re;
	}
	
	
	/**
	 * 移动APP告警管理统计
	 * @return
	 */
	public Map<String,Object> getAppDeviceWarningStatistics(){
		String sql ="SELECT  warning_evel warningevel,COUNT(id) AS countval FROM  device_warning_msg WHERE   state=2  GROUP BY warning_evel ";
		 Map<String,Object> re= getIndexDeviceWarningStatistics();
		 re.put("finish_urgent", 0);//已处理紧急
		 re.put("finish_important", 0);//已处理重要
		 re.put("finish_commonly", 0);//已处理一般
		
		 
		Query query = this.em.createNativeQuery(sql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);  
		List<Map<String,Object>> list=query.getResultList();
		if(list!=null && list.size()>0)
		{
			for(Map<String,Object> map:list)
			{
				if(map.get("warningevel").toString().equals("10"))
				{
					 re.put("finish_urgent", map.get("countval"));
				}
				else if(map.get("warningevel").toString().equals("20"))
				{
					 re.put("finish_important", map.get("countval"));
				}
				else if(map.get("warningevel").toString().equals("30"))
				{
					 re.put("finish_commonly", map.get("countval"));
				}
			}
		}
		
		return re;
	}
	
	
	
	
	
}
