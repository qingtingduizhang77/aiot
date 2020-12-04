package com.cityiot.guanxin.videoGroup.service;

import com.cityiot.guanxin.videoGroup.entity.QVideoGroupToDevice;
import com.cityiot.guanxin.videoGroup.entity.VideoGroupToDevice;
import com.cityiot.guanxin.videoGroup.repository.VideoGroupToDeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

import java.util.List;

/**
 * @author Guoyz
 * createTime   2020/7/31 17:18
 */
@Service
public class VideoGroupToDeviceService extends BaseService<VideoGroupToDeviceRepository, VideoGroupToDevice> {
    // 日志对象
    private static final Logger log = LoggerFactory.getLogger(VideoGroupToDeviceService.class);

    //删除指定平面图分组的中间表
    public void deleteItemByVideoGroupId(long videoGroupId, long deviceId){
        getRepsitory().deleteEntityByColumns(
                query -> query.where(QVideoGroupToDevice.videoGroupToDevice.videoGroupId.eq(videoGroupId)
                        .and(QVideoGroupToDevice.videoGroupToDevice.deviceId.eq(deviceId))));
    }
    //删除指定平面图分组的所有中间表
    public void deleteItemByGroupId(Long groupId){
        getRepsitory().deleteEntityByColumns(
                query -> query.where(QVideoGroupToDevice.videoGroupToDevice.videoGroupId.eq(groupId)));
    }
    //查询平面图列表（按ordering排序）
    public List<VideoGroupToDevice> getVideoGroupToDeviceListByGroupId(long groupId){
        //条件查询
        List<VideoGroupToDevice> entitys = this.getAllItems(query -> {
            return query.where(QVideoGroupToDevice.videoGroupToDevice.videoGroupId.eq(groupId))
                    .orderBy(QVideoGroupToDevice.videoGroupToDevice.ordering.asc());
        });
        return entitys;
    }
}
