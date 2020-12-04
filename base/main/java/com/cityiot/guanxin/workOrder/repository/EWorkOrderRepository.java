package com.cityiot.guanxin.workOrder.repository;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.cityiot.guanxin.common.repository.BaseRepository;
import com.cityiot.guanxin.workOrder.entity.EWorkOrder;
import com.cityiot.guanxin.workOrder.vo.WorkNumInfoVo;
import com.cityiot.guanxin.workOrder.vo.WorkOrderStatisticVo;

/**
 * 工单 的数据访问
 * 注意 因为使用的是自定义的SwallBaseRepository,使用@Repository无效
 * @author aohanhe
 *
 */
@Service
public class EWorkOrderRepository extends BaseRepository<EWorkOrder>{

	

	public List<EWorkOrder> workOrderList(Integer page, Integer pageSize,String pLongitude,String pLatitude,
							 Integer workOrderType, Integer handleStatus, Integer workOrderSort,long id){
		String sql = "SELECT\n" +
				"\t`work_order`.*,\n" +
				"\t`deviceinformation`.`device_code` AS `device_code`,\n" +
				"\t`deviceinformation`.`device_name` AS `device_name`,\n" +
				"\t`device_model`.`device_model` AS `device_model`,\n" +
				"\t`device_type`.`device_type_name` AS `device_type`,\n" +
				"\t`deviceinformation`.`area` AS `area`,\n" +
				"\t`deviceinformation`.`longitude` AS `longitude`,\n" +
				"\t`deviceinformation`.`latitude` AS `latitude`,\n" +
				"\tGET_DISTANCE(:pLatitude, :pLongitude, `deviceinformation`.`latitude`, `deviceinformation`.`longitude`) AS `distance`\n" +
				"FROM\n" +
				"\t(\n" +
				"\t\t(\n" +
				"\t\t\t(\n" +
				"\t\t\t\t(\n" +
				"\t\t\t\t\t(\n" +
				"\t\t\t\t\t\tSELECT\n" +
				"\t\t\t\t\t\t\tconcat(\n" +
				"\t\t\t\t\t\t\t\t1,\n" +
				"\t\t\t\t\t\t\t\t`einspection_work_order`.`id`\n" +
				"\t\t\t\t\t\t\t) AS `w_id`,\n" +
				"\t\t\t\t\t\t\t`einspection_work_order`.`id` AS `id`,\n" +
				"\t\t\t\t\t\t\t`einspection_work_order`.`version` AS `version`,\n" +
				"\t\t\t\t\t\t\t`einspection_work_order`.`created` AS `created`,\n" +
				"\t\t\t\t\t\t\t`einspection_work_order`.`creator` AS `creator`,\n" +
				"\t\t\t\t\t\t\t`einspection_work_order`.`lastmodi` AS `lastmodi`,\n" +
				"\t\t\t\t\t\t\t`einspection_work_order`.`modifier` AS `modifier`,\n" +
				"\t\t\t\t\t\t\t`einspection_work_order`.`code` AS `code`,\n" +
				"\t\t\t\t\t\t\t`einspection_work_order`.`handle_status` AS `handle_status`,\n" +
				"\t\t\t\t\t\t\t`einspection_work_order`.`handle_time` AS `handle_time`,\n" +
				"\t\t\t\t\t\t\t`einspection_work_order`.`handler_id` AS `handler_id`,\n" +
				"\t\t\t\t\t\t\t`einspection_work_order`.`record_id` AS `record_id`,\n" +
				"\t\t\t\t\t\t\t`einspection_work_order`.`remark` AS `remark`,\n" +
				"\t\t\t\t\t\t\t`einspection_work_order`.`des_content` AS `des_content`,\n" +
				"\t\t\t\t\t\t\t`einspection_work_order`.`device_info_id` AS `device_info_id`,\n" +
				"\t\t\t\t\t\t\t1 AS `work_order_type`\n" +
				"\t\t\t\t\t\tFROM\n" +
				"\t\t\t\t\t\t\t`einspection_work_order`\n" +
				"\t\t\t\t\t)\n" +
				"\t\t\t\t\tUNION ALL\n" +
				"\t\t\t\t\t\tSELECT\n" +
				"\t\t\t\t\t\t\tconcat(\n" +
				"\t\t\t\t\t\t\t\t2,\n" +
				"\t\t\t\t\t\t\t\t`emaintenance_work_order`.`id`\n" +
				"\t\t\t\t\t\t\t) AS `w_id`,\n" +
				"\t\t\t\t\t\t\t`emaintenance_work_order`.`id` AS `id`,\n" +
				"\t\t\t\t\t\t\t`emaintenance_work_order`.`version` AS `version`,\n" +
				"\t\t\t\t\t\t\t`emaintenance_work_order`.`created` AS `created`,\n" +
				"\t\t\t\t\t\t\t`emaintenance_work_order`.`creator` AS `creator`,\n" +
				"\t\t\t\t\t\t\t`emaintenance_work_order`.`lastmodi` AS `lastmodi`,\n" +
				"\t\t\t\t\t\t\t`emaintenance_work_order`.`modifier` AS `modifier`,\n" +
				"\t\t\t\t\t\t\t`emaintenance_work_order`.`code` AS `code`,\n" +
				"\t\t\t\t\t\t\t`emaintenance_work_order`.`handle_status` AS `handle_status`,\n" +
				"\t\t\t\t\t\t\t`emaintenance_work_order`.`handle_time` AS `handle_time`,\n" +
				"\t\t\t\t\t\t\t`emaintenance_work_order`.`handler_id` AS `handler_id`,\n" +
				"\t\t\t\t\t\t\t`emaintenance_work_order`.`record_id` AS `record_id`,\n" +
				"\t\t\t\t\t\t\t`emaintenance_work_order`.`remark` AS `remark`,\n" +
				"\t\t\t\t\t\t\t`emaintenance_work_order`.`des_content` AS `des_content`,\n" +
				"\t\t\t\t\t\t\t`emaintenance_work_order`.`device_info_id` AS `device_info_id`,\n" +
				"\t\t\t\t\t\t\t2 AS `work_order_type`\n" +
				"\t\t\t\t\t\tFROM\n" +
				"\t\t\t\t\t\t\t`emaintenance_work_order`\n" +
				"\t\t\t\t\t\tUNION ALL\n" +
				"\t\t\t\t\t\t\tSELECT\n" +
				"\t\t\t\t\t\t\t\tconcat(\n" +
				"\t\t\t\t\t\t\t\t\t3,\n" +
				"\t\t\t\t\t\t\t\t\t`erepair_work_order`.`id`\n" +
				"\t\t\t\t\t\t\t\t) AS `w_id`,\n" +
				"\t\t\t\t\t\t\t\t`erepair_work_order`.`id` AS `id`,\n" +
				"\t\t\t\t\t\t\t\t`erepair_work_order`.`version` AS `version`,\n" +
				"\t\t\t\t\t\t\t\t`erepair_work_order`.`created` AS `created`,\n" +
				"\t\t\t\t\t\t\t\t`erepair_work_order`.`creator` AS `creator`,\n" +
				"\t\t\t\t\t\t\t\t`erepair_work_order`.`lastmodi` AS `lastmodi`,\n" +
				"\t\t\t\t\t\t\t\t`erepair_work_order`.`modifier` AS `modifier`,\n" +
				"\t\t\t\t\t\t\t\t`erepair_work_order`.`code` AS `code`,\n" +
				"\t\t\t\t\t\t\t\t`erepair_work_order`.`handle_status` AS `handle_status`,\n" +
				"\t\t\t\t\t\t\t\t`erepair_work_order`.`handle_time` AS `handle_time`,\n" +
				"\t\t\t\t\t\t\t\t`erepair_work_order`.`handler_id` AS `handler_id`,\n" +
				"\t\t\t\t\t\t\t\t`erepair_work_order`.`record_id` AS `record_id`,\n" +
				"\t\t\t\t\t\t\t\t`erepair_work_order`.`remark` AS `remark`,\n" +
				"\t\t\t\t\t\t\t\t`erepair_work_order`.`des_content` AS `des_content`,\n" +
				"\t\t\t\t\t\t\t\t`erepair_work_order`.`device_info_id` AS `device_info_id`,\n" +
				"\t\t\t\t\t\t\t\t3 AS `work_order_type`\n" +
				"\t\t\t\t\t\t\tFROM\n" +
				"\t\t\t\t\t\t\t\t`erepair_work_order`\n" +
				"\t\t\t\t) `work_order`\n" +
				"\t\t\t\tLEFT JOIN `deviceinformation` ON (\n" +
				"\t\t\t\t\t`work_order`.`device_info_id` = `deviceinformation`.`id`\n" +
				"\t\t\t\t)\n" +
				"\t\t\t)\n" +
				"\t\t\tLEFT JOIN `device_model` ON (\n" +
				"\t\t\t\t`deviceinformation`.`device_model_id` = `device_model`.`id`\n" +
				"\t\t\t)\n" +
				"\t\t)\n" +
				"\t\tLEFT JOIN `device_type` ON (\n" +
				"\t\t\t`device_model`.`device_type_id` = `device_type`.`id`\n" +
				"\t\t)\n" +
				"\t)\n" +
				"WHERE work_order.handler_id = :handlerId";
		if(workOrderType != null){
			sql = sql + " and work_order_type = :workOrderType";
		}
		if(handleStatus != null){
			sql = sql + " and handle_status = :handleStatus";
		}
		if(workOrderSort != null && workOrderSort == 1){
			sql = sql + " order by distance asc";
		}else if(workOrderSort != null && workOrderSort == 2){
			sql = sql + " order by created asc";
		}
		Query query = this.em.createNativeQuery(sql, EWorkOrder.class)
				.setFirstResult((page-1)*pageSize).setMaxResults(pageSize);
		query.setParameter("pLongitude", pLongitude);
		query.setParameter("pLatitude", pLatitude);
		query.setParameter("handlerId", id);
		if(workOrderType != null){
			query.setParameter("workOrderType", workOrderType);
		}
		if(handleStatus != null){
			query.setParameter("handleStatus", handleStatus);
		}
		List resultList = query.getResultList();
		return resultList;
	}


	public WorkNumInfoVo queryMyWorkNum(long id) {
		String sql = "SELECT 1 as id, toDoTable.toDo as todo_num, doingTable.doing as doing_num, doneTable.done as finished_num, backTable.chargeback AS chargeback_num FROM\n" +
				"\t\t\t\t(SELECT count(*) as toDo FROM ework_order WHERE handle_status = 1 AND handler_id = :handlerId) AS toDoTable\n" +
				"\t\t\t\tJOIN\n" +
				"\t\t\t\t(SELECT count(*) AS doing FROM ework_order WHERE handle_status = 2 AND handler_id = :handlerId) AS doingTable\n" +
				"\t\t\t\tJOIN\n" +
				"\t\t\t\t(SELECT count(*) AS done FROM ework_order WHERE handle_status = 3 AND handler_id = :handlerId) AS doneTable\n" +
				"\t\t\t\tJOIN\n" +
				"\t\t\t\t(SELECT count(*) AS chargeback FROM ework_order WHERE handle_status = 4 AND handler_id = :handlerId) AS backTable";
		Query query = this.em.createNativeQuery(sql, WorkNumInfoVo.class);
		query.setParameter("handlerId", id);
		List resultList = query.getResultList();
		return (WorkNumInfoVo) resultList.get(0);
	}
	
	
	
	

	public List<WorkOrderStatisticVo> workOrderStatistic(Integer scopeType,Long userId) {
		
		if(scopeType != null && scopeType == 1){//当日记录
			Query query = this.em.createNativeQuery(this.getDaySql(userId), WorkOrderStatisticVo.class);
			if(userId!=null)
			{
				query.setParameter("handlerId1", userId);
				query.setParameter("handlerId2", userId);
			}
		
			return query.getResultList();
		}else if(scopeType != null && scopeType == 2){
			Query query = this.em.createNativeQuery(this.getWeekSql(userId), WorkOrderStatisticVo.class);
			if(userId!=null)
			{
				query.setParameter("handlerId1", userId);
				query.setParameter("handlerId2", userId);
			}
			return query.getResultList();
		}else if(scopeType != null && scopeType == 3){
			Query query = this.em.createNativeQuery(this.getMonthSql(userId), WorkOrderStatisticVo.class);
			if(userId!=null)
			{
				query.setParameter("handlerId1", userId);
				query.setParameter("handlerId2", userId);
			}
			return query.getResultList();
		}else if(scopeType != null && scopeType == 4) {
			Query query = this.em.createNativeQuery(this.getYearSql(userId), WorkOrderStatisticVo.class);
			if(userId!=null)
			{
				query.setParameter("handlerId1", userId);
				query.setParameter("handlerId2", userId);
			}
			return query.getResultList();
		}
		Query query = this.em.createNativeQuery(this.getSql(userId), WorkOrderStatisticVo.class);
		if(userId!=null)
		{
			query.setParameter("handlerId1", userId);
			query.setParameter("handlerId2", userId);
		}
		return query.getResultList();
	}

	public String getSql(Long userId){
		
		
		if(userId==null)
		{
			return  "SELECT typeTable.variable as id, doingTable.doing as unfinished_num, doneTable.done as finished_num,typeTable.variable AS work_order_type\n" +
					"\t\t\t\tFROM\n" +
					"\t\t\t\t(SELECT variable FROM system_variable\n" +
					"\t\t\t\tLEFT JOIN system_variable_type ON system_variable.vartype = system_variable_type.id\n" +
					"\t\t\t\tWHERE system_variable_type.name = '工单类型') AS typeTable\n" +
					"\t\t\t\tleft JOIN \n" +
					"\t\t\t\t(SELECT count(*) AS doing, work_order_type FROM ework_order WHERE handle_status IN(1,2) GROUP BY work_order_type) AS doingTable\n" +
					"\t\t\t\tON typeTable.variable = doingTable.work_order_type\n" +
					"\t\t\t\tleft JOIN\n" +
					"\t\t\t\t(SELECT count(*) AS done, work_order_type FROM ework_order WHERE handle_status = 3 GROUP BY work_order_type) AS doneTable\n" +
					"\t\t\t\tON typeTable.variable = doneTable.work_order_type";
		}
		else
		{
			return  "SELECT typeTable.variable as id, doingTable.doing as unfinished_num, doneTable.done as finished_num,typeTable.variable AS work_order_type\n" +
					"\t\t\t\tFROM\n" +
					"\t\t\t\t(SELECT variable FROM system_variable\n" +
					"\t\t\t\tLEFT JOIN system_variable_type ON system_variable.vartype = system_variable_type.id\n" +
					"\t\t\t\tWHERE system_variable_type.name = '工单类型') AS typeTable\n" +
					"\t\t\t\tleft JOIN \n" +
					"\t\t\t\t(SELECT count(*) AS doing, work_order_type FROM ework_order WHERE handler_id=:handlerId1 and handle_status IN(1,2) GROUP BY work_order_type) AS doingTable\n" +
					"\t\t\t\tON typeTable.variable = doingTable.work_order_type\n" +
					"\t\t\t\tleft JOIN\n" +
					"\t\t\t\t(SELECT count(*) AS done, work_order_type FROM ework_order WHERE handler_id=:handlerId2 and handle_status = 3 GROUP BY work_order_type) AS doneTable\n" +
					"\t\t\t\tON typeTable.variable = doneTable.work_order_type";
		}
	
	}

	public String getDaySql(Long userId){
		
		if(userId==null)
		{
			return  "SELECT typeTable.variable as id, doingTable.doing as unfinished_num, doneTable.done as finished_num,typeTable.variable AS work_order_type\n" +
					"\t\t\t\tFROM\n" +
					"\t\t\t\t(SELECT variable FROM system_variable\n" +
					"\t\t\t\tLEFT JOIN system_variable_type ON system_variable.vartype = system_variable_type.id\n" +
					"\t\t\t\tWHERE system_variable_type.name = '工单类型') AS typeTable\n" +
					"\t\t\t\tleft JOIN \n" +
					"\t\t\t\t(SELECT count(*) AS doing, work_order_type FROM ework_order WHERE  handle_status IN(1,2) \n" +
					"\t\t\t\t\tAND to_days(created) = to_days(now()) GROUP BY work_order_type) AS doingTable\n" +
					"\t\t\t\tON typeTable.variable = doingTable.work_order_type\n" +
					"\t\t\t\tleft JOIN\n" +
					"\t\t\t\t(SELECT count(*) AS done, work_order_type FROM ework_order WHERE  handle_status = 3 \n" +
					"\t\t\t\t\tAND to_days(created) = to_days(now()) GROUP BY work_order_type) AS doneTable\n" +
					"\t\t\t\tON typeTable.variable = doneTable.work_order_type";
		}
		else
		{
			return  "SELECT typeTable.variable as id, doingTable.doing as unfinished_num, doneTable.done as finished_num,typeTable.variable AS work_order_type\n" +
					"\t\t\t\tFROM\n" +
					"\t\t\t\t(SELECT variable FROM system_variable\n" +
					"\t\t\t\tLEFT JOIN system_variable_type ON system_variable.vartype = system_variable_type.id\n" +
					"\t\t\t\tWHERE system_variable_type.name = '工单类型') AS typeTable\n" +
					"\t\t\t\tleft JOIN \n" +
					"\t\t\t\t(SELECT count(*) AS doing, work_order_type FROM ework_order WHERE handler_id=:handlerId1 and handle_status IN(1,2) \n" +
					"\t\t\t\t\tAND to_days(created) = to_days(now()) GROUP BY work_order_type) AS doingTable\n" +
					"\t\t\t\tON typeTable.variable = doingTable.work_order_type\n" +
					"\t\t\t\tleft JOIN\n" +
					"\t\t\t\t(SELECT count(*) AS done, work_order_type FROM ework_order WHERE handler_id=:handlerId2 and handle_status = 3 \n" +
					"\t\t\t\t\tAND to_days(created) = to_days(now()) GROUP BY work_order_type) AS doneTable\n" +
					"\t\t\t\tON typeTable.variable = doneTable.work_order_type";
		}
		
		
	}

	public String getWeekSql(Long userId){
		
		if(userId==null)
		{
			return  "SELECT typeTable.variable as id, doingTable.doing as unfinished_num, doneTable.done as finished_num,typeTable.variable AS work_order_type\n" +
					"\t\t\t\tFROM\n" +
					"\t\t\t\t(SELECT variable FROM system_variable\n" +
					"\t\t\t\tLEFT JOIN system_variable_type ON system_variable.vartype = system_variable_type.id\n" +
					"\t\t\t\tWHERE system_variable_type.name = '工单类型') AS typeTable\n" +
					"\t\t\t\tleft JOIN \n" +
					"\t\t\t\t(SELECT count(*) AS doing, work_order_type FROM ework_order WHERE  handle_status IN(1,2) \n" +
					"\t\t\t\tAND YEARWEEK(date_format(created,'%Y-%m-%d')) = YEARWEEK(now()) GROUP BY work_order_type) AS doingTable\n" +
					"\t\t\t\tON typeTable.variable = doingTable.work_order_type\n" +
					"\t\t\t\tleft JOIN\n" +
					"\t\t\t\t(SELECT count(*) AS done, work_order_type FROM ework_order WHERE handle_status = 3 \n" +
					"\t\t\t\tAND YEARWEEK(date_format(created,'%Y-%m-%d')) = YEARWEEK(now()) GROUP BY work_order_type) AS doneTable\n" +
					"\t\t\t\tON typeTable.variable = doneTable.work_order_type";
		}
		else
		{
			return  "SELECT typeTable.variable as id, doingTable.doing as unfinished_num, doneTable.done as finished_num,typeTable.variable AS work_order_type\n" +
					"\t\t\t\tFROM\n" +
					"\t\t\t\t(SELECT variable FROM system_variable\n" +
					"\t\t\t\tLEFT JOIN system_variable_type ON system_variable.vartype = system_variable_type.id\n" +
					"\t\t\t\tWHERE system_variable_type.name = '工单类型') AS typeTable\n" +
					"\t\t\t\tleft JOIN \n" +
					"\t\t\t\t(SELECT count(*) AS doing, work_order_type FROM ework_order WHERE handler_id=:handlerId1 and handle_status IN(1,2) \n" +
					"\t\t\t\tAND YEARWEEK(date_format(created,'%Y-%m-%d')) = YEARWEEK(now()) GROUP BY work_order_type) AS doingTable\n" +
					"\t\t\t\tON typeTable.variable = doingTable.work_order_type\n" +
					"\t\t\t\tleft JOIN\n" +
					"\t\t\t\t(SELECT count(*) AS done, work_order_type FROM ework_order WHERE handler_id=:handlerId2 and handle_status = 3 \n" +
					"\t\t\t\tAND YEARWEEK(date_format(created,'%Y-%m-%d')) = YEARWEEK(now()) GROUP BY work_order_type) AS doneTable\n" +
					"\t\t\t\tON typeTable.variable = doneTable.work_order_type";
		}
		
	}

	public String getMonthSql(Long userId){
		
		if(userId==null)
		{
			return  "SELECT typeTable.variable as id, doingTable.doing as unfinished_num, doneTable.done as finished_num,typeTable.variable AS work_order_type\n" +
					"\t\t\t\tFROM\n" +
					"\t\t\t\t(SELECT variable FROM system_variable\n" +
					"\t\t\t\tLEFT JOIN system_variable_type ON system_variable.vartype = system_variable_type.id\n" +
					"\t\t\t\tWHERE system_variable_type.name = '工单类型') AS typeTable\n" +
					"\t\t\t\tleft JOIN \n" +
					"\t\t\t\t(SELECT count(*) AS doing, work_order_type FROM ework_order WHERE  handle_status IN(1,2) \n" +
					"\t\t\t\t\tAND DATE_FORMAT( created,'%Y%m') = DATE_FORMAT(CURDATE(),'%Y%m') GROUP BY work_order_type) AS doingTable\n" +
					"\t\t\t\tON typeTable.variable = doingTable.work_order_type\n" +
					"\t\t\t\tleft JOIN\n" +
					"\t\t\t\t(SELECT count(*) AS done, work_order_type FROM ework_order WHERE handle_status = 3 \n" +
					"\t\t\t\t\tAND DATE_FORMAT( created,'%Y%m') = DATE_FORMAT(CURDATE(),'%Y%m') GROUP BY work_order_type) AS doneTable\n" +
					"\t\t\t\tON typeTable.variable = doneTable.work_order_type";
		}
		else
		{
			return  "SELECT typeTable.variable as id, doingTable.doing as unfinished_num, doneTable.done as finished_num,typeTable.variable AS work_order_type\n" +
					"\t\t\t\tFROM\n" +
					"\t\t\t\t(SELECT variable FROM system_variable\n" +
					"\t\t\t\tLEFT JOIN system_variable_type ON system_variable.vartype = system_variable_type.id\n" +
					"\t\t\t\tWHERE system_variable_type.name = '工单类型') AS typeTable\n" +
					"\t\t\t\tleft JOIN \n" +
					"\t\t\t\t(SELECT count(*) AS doing, work_order_type FROM ework_order WHERE handler_id=:handlerId1 and handle_status IN(1,2) \n" +
					"\t\t\t\t\tAND DATE_FORMAT( created,'%Y%m') = DATE_FORMAT(CURDATE(),'%Y%m') GROUP BY work_order_type) AS doingTable\n" +
					"\t\t\t\tON typeTable.variable = doingTable.work_order_type\n" +
					"\t\t\t\tleft JOIN\n" +
					"\t\t\t\t(SELECT count(*) AS done, work_order_type FROM ework_order WHERE handler_id=:handlerId2 and handle_status = 3 \n" +
					"\t\t\t\t\tAND DATE_FORMAT( created,'%Y%m') = DATE_FORMAT(CURDATE(),'%Y%m') GROUP BY work_order_type) AS doneTable\n" +
					"\t\t\t\tON typeTable.variable = doneTable.work_order_type";
		}
	
	}

	public String getYearSql(Long userId){
		
		if(userId==null)
		{
			return  "SELECT typeTable.variable as id, doingTable.doing as unfinished_num, doneTable.done as finished_num,typeTable.variable AS work_order_type\n" +
					"\t\t\t\tFROM\n" +
					"\t\t\t\t(SELECT variable FROM system_variable\n" +
					"\t\t\t\tLEFT JOIN system_variable_type ON system_variable.vartype = system_variable_type.id\n" +
					"\t\t\t\tWHERE system_variable_type.name = '工单类型') AS typeTable\n" +
					"\t\t\t\tleft JOIN \n" +
					"\t\t\t\t(SELECT count(*) AS doing, work_order_type FROM ework_order WHERE  handle_status IN(1,2) \n" +
					"\t\t\t\t\tAND YEAR(created)=YEAR(NOW()) GROUP BY work_order_type) AS doingTable\n" +
					"\t\t\t\tON typeTable.variable = doingTable.work_order_type\n" +
					"\t\t\t\tleft JOIN\n" +
					"\t\t\t\t(SELECT count(*) AS done, work_order_type FROM ework_order WHERE  handle_status = 3 \n" +
					"\t\t\t\t\tAND YEAR(created)=YEAR(NOW()) GROUP BY work_order_type) AS doneTable\n" +
					"\t\t\t\tON typeTable.variable = doneTable.work_order_type";
		}
		else
		{
			return  "SELECT typeTable.variable as id, doingTable.doing as unfinished_num, doneTable.done as finished_num,typeTable.variable AS work_order_type\n" +
					"\t\t\t\tFROM\n" +
					"\t\t\t\t(SELECT variable FROM system_variable\n" +
					"\t\t\t\tLEFT JOIN system_variable_type ON system_variable.vartype = system_variable_type.id\n" +
					"\t\t\t\tWHERE system_variable_type.name = '工单类型') AS typeTable\n" +
					"\t\t\t\tleft JOIN \n" +
					"\t\t\t\t(SELECT count(*) AS doing, work_order_type FROM ework_order WHERE handler_id=:handlerId1 and handle_status IN(1,2) \n" +
					"\t\t\t\t\tAND YEAR(created)=YEAR(NOW()) GROUP BY work_order_type) AS doingTable\n" +
					"\t\t\t\tON typeTable.variable = doingTable.work_order_type\n" +
					"\t\t\t\tleft JOIN\n" +
					"\t\t\t\t(SELECT count(*) AS done, work_order_type FROM ework_order WHERE handler_id=:handlerId2 and handle_status = 3 \n" +
					"\t\t\t\t\tAND YEAR(created)=YEAR(NOW()) GROUP BY work_order_type) AS doneTable\n" +
					"\t\t\t\tON typeTable.variable = doneTable.work_order_type";
		}
		
	}

}
