package com.cityiot.guanxin.alarmRule.service;

import com.cityiot.guanxin.alarmRule.entity.AlarmRule;
import com.cityiot.guanxin.alarmRule.entity.AlarmRuleToRole;
import com.cityiot.guanxin.alarmRule.repository.AlarmRuleRepository;
import com.cityiot.guanxin.common.service.JDBCDaoImp;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swallow.framework.exception.SwallowException;
import swallow.framework.service.BaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AlarmRuleService extends BaseService<AlarmRuleRepository, AlarmRule> {
    private static final Logger log = LoggerFactory.getLogger(AlarmRuleService.class);

    @Autowired
    private AlarmRuleToRoleService alarmRuleToRoleService;

    @Autowired
    private JDBCDaoImp jdbcDaoImp;


    //查询以code为分组的告警信息数量
    public List<Map<String, Object>> getCodeCountWarning(String code){
        List<Object> objs = new ArrayList<>();
        String sql = "SELECT \n" +
                "  ar.`name`,\n" +
                "  ar.`code`,\n" +
                "  COUNT(1) AS count\n" +
                "FROM\n" +
                "  `device_warning_msg` dw\n" +
                "  LEFT JOIN `alarm_rule` ar \n" +
                "    ON dw.`rule_id` = ar.`id`\n";
        if (StringUtils.isNotEmpty(code)) {
            sql += "where ar.`code` in ("+code+")\n";
            objs.add(code);
        }
        sql += "GROUP BY ar.`id`";
        return jdbcDaoImp.queryForMap(sql, null);
    }


    //新增一条数据
    @Override
    public AlarmRule insertItem(AlarmRule item) throws SwallowException {
        AlarmRule entity = super.insertItem(item);
        insertOrderInfo(entity);
        return entity;
    }

    // 添加关联表数据,更新副表数据
    private void insertOrderInfo(AlarmRule item) throws SwallowException {
        // 告警规则与角色中间表
        if (StringUtils.isNotEmpty(item.getRoleIds())) {
            String[] roleIds = item.getRoleIds().split(",");
            for (var i =0; i<roleIds.length;i++) {
                AlarmRuleToRole alarmRuleToRole = new AlarmRuleToRole();
                alarmRuleToRole.setAlarmRuleId(item.getId());
                alarmRuleToRole.setRoleId(Long.valueOf(roleIds[i]));
                alarmRuleToRoleService.insertItem(alarmRuleToRole);
            }
        }
    }

    //删除一条数据
    public void deleteItemByAlarmRuleId(long id){
        this.deleteItemById(id); //删除主表
        alarmRuleToRoleService.deleteItemByAlarmRuleId(id);//删除副表
    }

    //更新一条数据
    @Override
    @Transactional
    public AlarmRule updateItem(AlarmRule enity){
        AlarmRule itemById = this.getItemById(enity.getId());
        if(itemById==null){throw new SwallowException("数据不存在");}
        alarmRuleToRoleService.deleteItemByAlarmRuleId(enity.getId());//删除副表
        insertOrderInfo(enity);// 更新副表
        return super.updateItem(enity);//更新主表
    }
}
