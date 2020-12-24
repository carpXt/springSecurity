package com.carpxt.myhr.model.from;

import com.alibaba.excel.annotation.ExcelProperty;
import com.carpxt.myhr.controller.config.CustomBooleanConverterImport;
import lombok.Data;

import java.util.Date;

/**
 * @Author: tt
 * @Date: 2020/12/18 14:40
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class Jobdata {

        private String name;

        private String titleLevel;

        private Date createDate;

        @ExcelProperty(value="是否启用", converter = CustomBooleanConverterImport.class)
        private Boolean enabled;
}
