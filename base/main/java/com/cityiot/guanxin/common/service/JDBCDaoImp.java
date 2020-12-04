package com.cityiot.guanxin.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class JDBCDaoImp  {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	
	
	public <T> List<T> getListBySql(String sql, RowMapper<T> objMapper, Object[] objs) {
		try {

			return this.getJdbcTemplate().query(sql, objMapper, objs);

		} catch (EmptyResultDataAccessException e) {
			 e.printStackTrace();
			return null;
		}
	}

	public <T> List<T> getListBySql(String sql, RowMapper<T> objMapper, Map<String, Object> map) {
		try {

			return namedParameterJdbcTemplate.query(sql,map, objMapper);

		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public <T> T queryForObject(Class<T> c, String sql, Object[] objs) {

		try {
			return this.getJdbcTemplate().queryForObject(sql, c, objs);

		} catch (EmptyResultDataAccessException e) {
			 e.printStackTrace();
			return null;
		}

	}

	
	public boolean executeSql(String sql, Object[] objs) {

		if(objs==null)
		{
			this.getJdbcTemplate().update(sql);
		}
		else
		{
			this.getJdbcTemplate().update(sql, objs);
		}
		
		return true;
	}

	
	public List<Map<String, Object>> queryForMap(String sql, Object[] objs) {

		try {
			
			return this.getJdbcTemplate().queryForList(sql, objs);
		} catch (Exception e) {
            e.printStackTrace();
			return null;
		}
	}

	public List<Map<String, Object>> queryForMaps(String sql,Map<String, Object> map) {

		try {

			return namedParameterJdbcTemplate.queryForList(sql, map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public <T> List<T> queryForList(Class<T> c, String sql, Object[] objs) {
		try {
			return this.getJdbcTemplate().queryForList(sql, c, objs);
		} catch (EmptyResultDataAccessException e) {
			 e.printStackTrace();
			return null;
		}
	}

	
	public boolean batchExecuteSql(List<String> sqlList) {
		
		String[] sqlArray = new String[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++) {
			sqlArray[i] = sqlList.get(i);
		}
		this.getJdbcTemplate().batchUpdate(sqlArray);
		return true;
	}


	public int queryForInt(String sql, Object[] objs) {
		
		return this.getJdbcTemplate().queryForObject(sql,Integer.class, objs);
	}

	
	public int queryForInt(String sql,Map<String, Object> map) {

		return namedParameterJdbcTemplate.queryForObject(sql,map,Integer.class);
	}
	

	
	public boolean executeSql(String sql) {
		
		this.getJdbcTemplate().update(sql);
		
		return true;
	}


	
	
	
	
	
}
