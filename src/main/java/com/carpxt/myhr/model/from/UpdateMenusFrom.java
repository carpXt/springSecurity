package com.carpxt.myhr.model.from;

import lombok.Data;

import java.util.List;

/**
 * @Author: tianjie
 * @Date: 2020/12/24 15:42
 * @Description: TODO
 * @Version: 1.0
 */

@Data
public class UpdateMenusFrom {

    //角色id
    private Integer rid;
    //菜单id集合
    private List<Integer> mids;
}
