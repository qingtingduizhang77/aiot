package com.cityiot.guanxin.videoGroup.service;

import com.cityiot.guanxin.videoGroup.entity.QVideoGroup;
import com.cityiot.guanxin.videoGroup.entity.VideoGroup;
import com.cityiot.guanxin.videoGroup.repository.VideoGroupRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swallow.framework.service.BaseService;

import java.util.List;

/**
 * @author Guoyz
 * createTime   2020/7/31 17:18
 */
@Service
public class VideoGroupService extends BaseService<VideoGroupRepository, VideoGroup> {
    // 日志对象
    private static final Logger log = LoggerFactory.getLogger(VideoGroupService.class);

    @Autowired
    private VideoGroupToDeviceService VGTDService;

    @Transactional
    public void deleteItemById(long []ids) throws Exception{
        for(var id:ids){
            if (this.getVideoGroupListByParentId(id).size() > 0) {
                throw new Exception("有子级分组，不予以删除！");
            }
            this.deleteItemById(id); //删除主表
            VGTDService.deleteItemByGroupId(id);// 删除附表
        }
    }

    //查询分组对应上级所有分组
    public List<VideoGroup> getVideoGroupListByParentId(long id){
        QVideoGroup qVideoGroup = QVideoGroup.videoGroup;
        return this.getAllItems(
                query -> query.where(qVideoGroup.parentId.eq(id).or(qVideoGroup.parentIdArr.like(id+",")))
        );
    }

    public void checkIsGtLevel(VideoGroup item) throws Exception {
        // 设备分组属于第几级别
        if (item.getParentId() > 0) {
            VideoGroup group = this.getItemById(item.getParentId());
            if (group != null){
                item.setLevel(group.getLevel() + 1);
                if (StringUtils.isNotEmpty(group.getParentIdArr())) {
                    item.setParentIdArr("" + group.getParentIdArr() + ",'" + group.getId() + "'");
                }else{
                    item.setParentIdArr("'" + group.getId()+ "'");
                }
            }
        }else {
            item.setLevel(1);
            item.setParentIdArr(null);
        }
        if (item.getLevel() > 9) {
            throw new Exception("分组层级最多为9！");
        }
    }
}
