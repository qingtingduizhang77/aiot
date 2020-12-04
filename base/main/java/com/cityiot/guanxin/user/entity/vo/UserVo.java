package com.cityiot.guanxin.user.entity.vo;

import swallow.framework.jpaquery.repository.annotations.CnName;

public class UserVo {
    private long id;
    // 姓名
    @CnName("姓名")
    private String name;
    // 用户名
    @CnName("用户名")
    private String username;
    @CnName("性别")
    // 0为女 1为男
    private int sex;
    // 手机号码
    @CnName("手机号")
    private String phone;
    // 是否禁用
    @CnName("是否禁用")
    private int disable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDisable() {
        return disable;
    }

    public void setDisable(int disable) {
        this.disable = disable;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
