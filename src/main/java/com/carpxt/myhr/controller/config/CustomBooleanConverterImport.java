package com.carpxt.myhr.controller.config;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * @Author: tianjie
 * @Date: 2020/12/17 20:38
 * @Description: TODO
 * @Version: 1.0
 */
public class CustomBooleanConverterImport implements Converter<Boolean> {


    @Override
    public Class supportJavaTypeKey() {
        return null;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return null;
    }


    @Override
    public Boolean convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        Boolean b = false;
        if (cellData.getStringValue().equals("是")){
            b=true;
        }
        return b;
    }

    @Override
    public CellData convertToExcelData(Boolean value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return null;
    }
}
