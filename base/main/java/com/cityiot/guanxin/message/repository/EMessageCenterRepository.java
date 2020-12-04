package com.cityiot.guanxin.message.repository;


import com.cityiot.guanxin.message.entity.EMessageCenter;
import com.cityiot.guanxin.message.entity.QEMessageCenter;

import org.springframework.stereotype.Service;

import swallow.framework.jpaquery.repository.SwallowRepository;

/**
 * 消息中心 的数据访问
 * 注意 因为使用的是自定义的SwallBaseRepository,使用@Repository无效
 * @author aohanhe
 *
 */
@Service
public class EMessageCenterRepository extends SwallowRepository<EMessageCenter>{
	
	
	public long notReadCount(long id) {
		QEMessageCenter eMessageCenter = QEMessageCenter.eMessageCenter;
		return this.factory
				.select(eMessageCenter.id).from(eMessageCenter)
				.where(eMessageCenter.targetId.eq(id))
				.where(eMessageCenter.messageStatus.eq(1)).fetchCount();
	}

	public void tagMessage(long messageId, int status) {
		QEMessageCenter eMessageCenter = QEMessageCenter.eMessageCenter;
		if(status == 2 || status == 3){
			this.factory.update(eMessageCenter)
					.set(eMessageCenter.messageStatus, status)
					.where(eMessageCenter.id.eq(messageId)).execute();
		}
	}

	public void tagMessageAll(long id) {
		QEMessageCenter eMessageCenter = QEMessageCenter.eMessageCenter;
		this.factory.update(eMessageCenter)
				.set(eMessageCenter.messageStatus, 2)
				.where(eMessageCenter.targetId.eq(id)).execute();
	}
}
