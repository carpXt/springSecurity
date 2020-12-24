package com.carpxt.myhr.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.carpxt.myhr.controller.config.CustomBooleanConverter;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public class JobLevel {

    @ExcelIgnore
    private Integer id;

    @ExcelProperty({"职位名称"})
    private String name;

    @ExcelProperty({"职位等级"})
    private String titleLevel;

    @ExcelProperty({"创建时间"})
    @DateTimeFormat("yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date createDate;

    @ExcelProperty(value={"是否启用"}, converter = CustomBooleanConverter.class)
    private Boolean enabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTitleLevel() {
        return titleLevel;
    }

    public void setTitleLevel(String titleLevel) {
        this.titleLevel = titleLevel;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}