package com.cityiot.guanxin.projectTotal.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import swallow.framework.jpaquery.repository.annotations.CnName;

import java.io.Serializable;

/**
 * 管网数据
 * @author Guoyz
 * createTime   2020/7/22 14:36
 */
@Document(collection = "guan_wang")
public class guanWang {
    @Id
    private Integer id;
    @CnName("年度")
    private Integer year;
    @CnName("总沟程")
    private String gouLength;
    @CnName("总孔程")
    private String kongLength;
    @CnName("项目数")
    private Integer items;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGouLength() {
        return gouLength;
    }

    public void setGouLength(String gouLength) {
        this.gouLength = gouLength;
    }

    public String getKongLength() {
        return kongLength;
    }

    public void setKongLength(String kongLength) {
        this.kongLength = kongLength;
    }

    public Integer getItems() {
        return items;
    }

    public void setItems(Integer items) {
        this.items = items;
    }
}
