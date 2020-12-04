package com.cityiot.guanxin.mongo;

import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Shape;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 条件数据
 *
 * @author hxl 正则、范围（大于、小于等） 嵌套级AND、OR
 */
public class CriteriaMap {

    private Map<String, Data> map = new HashMap<String, Data>();

    private Criteria criteria;

    /**
     * 条件进AND操作
     *
     * @param criteriaMap
     * @return
     */
    public CriteriaMap andOperator(
            CriteriaMap... criteriaMap) {
        Criteria c = new Criteria();
        for (CriteriaMap cm : criteriaMap) {
            if (cm.criteria == null) {
                cm.criteria = criteriaMap2Criteria(cm);
            }
            c = c.andOperator(cm.criteria);
        }
        if (this.criteria == null) {
            this.criteria = criteriaMap2Criteria(this);
        }
        this.criteria = this.criteria.andOperator(c);
        return this;
    }

    /**
     * 条件OR操作
     *
     * @param criteriaMap
     * @return
     */
    public CriteriaMap orOperator(
            CriteriaMap... criteriaMap) {
        Criteria c = new Criteria();
        for (CriteriaMap cm : criteriaMap) {
            if (cm.criteria == null) {
                cm.criteria = criteriaMap2Criteria(cm);
            }
            c = c.orOperator(cm.criteria);
        }
        this.criteria = this.criteria.andOperator(c);
        return this;
    }

    /**
     * 条件OR操作
     *
     * @param criteriaMap
     * @return
     */
    public CriteriaMap orOperator2(List<CriteriaMap> orgArr) {
        Criteria c = new Criteria();
        for (CriteriaMap cm : orgArr) {
            if (cm.criteria == null) {
                cm.criteria = criteriaMap2Criteria(cm);
            }
            c = c.orOperator(cm.criteria);
        }
        this.criteria = this.criteria.andOperator(c);
        return this;
    }

    private Criteria getCriteria() {
        return this.criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    protected static Criteria criteriaMap2Criteria(
            CriteriaMap criteriaMap) {
        if (criteriaMap == null) {
            return new Criteria();
        }
        if (criteriaMap.getCriteria() != null) {
            return criteriaMap.getCriteria();
        }
        if (criteriaMap.map.size() == 0) {
            return new Criteria();
        }
        Criteria criteria = null;
        for (String key : criteriaMap.keySet()) {
            CriteriaMap.Data d = criteriaMap.get(key);
            switch (d.getType()) {
                case IS:
                    if (criteria == null) {
                        criteria = Criteria.where(key).is(d.getObj());
                    } else {
                        criteria = criteria.and(key).is(d.getObj());
                    }
                    break;
                case REGEX:
                    if (criteria == null) {
                        criteria = Criteria.where(key).regex(d.getObj().toString());
                    } else {
                        criteria = criteria.and(key).regex(d.getObj().toString());
                    }
                    break;
                case GT:
                    if (criteria == null) {
                        criteria = Criteria.where(key).gt(d.getObj());
                    } else {
                        criteria = criteria.and(key).gt(d.getObj());
                    }
                    break;
                case GTE:
                    if (criteria == null) {
                        criteria = Criteria.where(key).gte(d.getObj());
                    } else {
                        criteria = criteria.and(key).gte(d.getObj());
                    }
                    break;
                case LT:
                    if (criteria == null) {
                        criteria = Criteria.where(key).lt(d.getObj());
                    } else {
                        criteria = criteria.and(key).lt(d.getObj());
                    }
                    break;
                case LTE:
                    if (criteria == null) {
                        criteria = Criteria.where(key).lte(d.getObj());
                    } else {
                        criteria = criteria.and(key).lte(d.getObj());
                    }
                    break;

                case WITHIN: {
                    if (criteria == null) {
                        // Box box = new Box(new Point(10, 11), new Point(10,
                        // 20));
                        criteria = Criteria.where(key).within((Shape) d.getObj());
                    } else {
                        criteria = criteria.and(key).within((Shape) d.getObj());
                    }
                    break;
                }
                case WITHINSPHERE: {
                    if (criteria == null) {
                        // Box box = new Box(new Point(10, 11), new Point(10,
                        // 20));
                        criteria = Criteria.where(key).withinSphere((Circle) d.getObj());
                    } else {
                        criteria = criteria.and(key).withinSphere((Circle) d.getObj());
                    }
                    break;
                }
            }
        }

        return criteria;

    }

    /**
     * 条件设置
     *
     * @param key
     * @param value
     */
    public void put(
            String key,
            Object value) {
        map.put(key, new Data(value));
    }

    public Data get(
            String key) {
        return map.get(key);
    }

    /**
     * 条件设置 并带有范围查找（大、小、等、正则）
     *
     * @param key
     * @param value
     * @param type
     */
    public void put(
            String key,
            Object value,
            Type type) {
        map.put(key, new Data(value, type));
    }

    public Set<String> keySet() {
        return map.keySet();
    }

    class Data {

        private Type type;
        private Object obj;

        Data(Object obj) {
            this.obj = obj;
            this.type = Type.IS;
        }

        Data(Object obj, Type type) {
            this.obj = obj;
            this.type = type;
        }

        public Object getObj() {
            return obj;
        }

        public Type getType() {
            return type;
        }

    }

    public enum Type {
        /**
         * 等于
         */
        IS,
        /**
         * 正则
         */
        REGEX,
        /**
         * 大于
         */
        GT,
        /**
         * 大于等于
         */
        GTE,
        /**
         * 小于
         */
        LT,
        /**
         * 小于等于
         */
        LTE,
        /**
         * 矩形、圆、查询, (box,circle)
         */
        WITHIN,
        /**
         * 球星查询
         */
        WITHINSPHERE
    }

}
