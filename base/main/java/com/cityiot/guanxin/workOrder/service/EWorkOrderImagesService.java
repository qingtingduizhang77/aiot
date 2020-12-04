package com.cityiot.guanxin.workOrder.service;


import com.cityiot.guanxin.workOrder.entity.QEWorkOrderImages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swallow.framework.service.BaseService;

import com.cityiot.guanxin.workOrder.entity.EWorkOrderImages;
import com.cityiot.guanxin.workOrder.repository.EWorkOrderImagesRepository;

import java.util.List;

/**
 * EWorkOrderImages 数据Service
 * @author aohanhe
 *
 */
@Service
public class EWorkOrderImagesService extends BaseService<EWorkOrderImagesRepository, EWorkOrderImages>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(EWorkOrderImagesService.class);

	public List<EWorkOrderImages> getItemsByWorkOrderId(long workOrderId, int workOrderType) {
		QEWorkOrderImages eWorkOrderImages = QEWorkOrderImages.eWorkOrderImages;
		return this.getAllItems(query -> query
				.where(eWorkOrderImages.workOrderId.eq(workOrderId))
				.where(eWorkOrderImages.workOrderType.eq(workOrderType)));
	}

	@Transactional(rollbackFor = Exception.class)
	public void insertNewItem(long workOrderId, String imageUrls, int workOrderType, int imgType) {
		String[] images = imageUrls.split(",");
		for (String image : images) {
			EWorkOrderImages workOrderImages = new EWorkOrderImages();
			workOrderImages.setWorkOrderId(workOrderId);
			workOrderImages.setImageUrl(image);
			workOrderImages.setWorkOrderType(workOrderType);
			workOrderImages.setImgType(imgType);
			this.insertItem(workOrderImages);
		}
	}
}
