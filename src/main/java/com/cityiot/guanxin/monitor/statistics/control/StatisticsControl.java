package com.cityiot.guanxin.monitor.statistics.control;

import swallow.framework.web.BasePageQueryBean;

import java.util.Date;

public class StatisticsControl {
    /**
     * 查询对象
     * @author aohanhe
     *
     */
    public static class QueryBean extends BasePageQueryBean {
        private Long areaId;
        private Long streetId;
        private Date startTime;
        private Date endTime;

        public Long getAreaId() {
            return areaId;
        }

        public void setAreaId(Long areaId) {
            this.areaId = areaId;
        }

        public Long getStreetId() {
            return streetId;
        }

        public void setStreetId(Long streetId) {
            this.streetId = streetId;
        }

        public Date getStartTime() {
            return startTime;
        }

        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }

        public Date getEndTime() {
            return endTime;
        }

        public void setEndTime(Date endTime) {
            this.endTime = endTime;
        }
    }
}
