package com.carpxt.myhr.model.dto;

import com.carpxt.myhr.model.Menu;
import lombok.Data;

import java.util.List;

/**
 * @Author: tianjie
 * @Date: 2020/12/24 13:59
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class MenuTreeDto {


    private String name;
    private Integer id;
    private List<Menu> children;


}
