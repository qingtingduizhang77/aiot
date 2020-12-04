package com.cityiot.guanxin.alarmRule.service;

import com.cityiot.guanxin.alarmRule.entity.AlarmRuleToRole;
import com.cityiot.guanxin.alarmRule.entity.QAlarmRule;
import com.cityiot.guanxin.alarmRule.entity.QAlarmRuleToRole;
import com.cityiot.guanxin.alarmRule.repository.AlarmRuleToRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;


@Service
public class AlarmRuleToRoleService extends BaseService<AlarmRuleToRoleRepository, AlarmRuleToRole> {
    private static final Logger log = LoggerFactory.getLogger(AlarmRuleToRoleService.class);

    public void deleteItemByAlarmRuleId(long alarmRuleId) {
        getRepsitory().deleteEntityByColumns(
                query -> query.where(QAlarmRuleToRole.alarmRuleToRole.alarmRuleId.eq(alarmRuleId)));
    }
}
