package com.cityiot.guanxin.projectTotal.pojo;

import com.gexin.fastjson.annotation.JSONField;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 智慧工地统计数据
 * @author Guoyz
 * createTime   2020/7/21 11:30
 */
@Document(collection = "project_total")
public class ProjectTotal implements Serializable{
    @Id
    private Integer id;
    private Boolean isNewRecord;
    private String companyId;
    private String createName;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    private String code;
    private String name;
    private Integer pileDriverCount;
    private Integer craneDriverCount;
    private Integer liftDriverCount;
    private Integer workStaffCount;
    private Integer managerStaffCount;
    private Integer chauffeurCount;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getNewRecord() {
        return isNewRecord;
    }

    public void setNewRecord(Boolean newRecord) {
        isNewRecord = newRecord;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPileDriverCount() {
        return pileDriverCount;
    }

    public void setPileDriverCount(Integer pileDriverCount) {
        this.pileDriverCount = pileDriverCount;
    }

    public Integer getCraneDriverCount() {
        return craneDriverCount;
    }

    public void setCraneDriverCount(Integer craneDriverCount) {
        this.craneDriverCount = craneDriverCount;
    }

    public Integer getLiftDriverCount() {
        return liftDriverCount;
    }

    public void setLiftDriverCount(Integer liftDriverCount) {
        this.liftDriverCount = liftDriverCount;
    }

    public Integer getWorkStaffCount() {
        return workStaffCount;
    }

    public void setWorkStaffCount(Integer workStaffCount) {
        this.workStaffCount = workStaffCount;
    }

    public Integer getManagerStaffCount() {
        return managerStaffCount;
    }

    public void setManagerStaffCount(Integer managerStaffCount) {
        this.managerStaffCount = managerStaffCount;
    }

    public Integer getChauffeurCount() {
        return chauffeurCount;
    }

    public void setChauffeurCount(Integer chauffeurCount) {
        this.chauffeurCount = chauffeurCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
