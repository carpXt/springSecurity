package com.carpxt.myhr.mapper;

import com.carpxt.myhr.model.Position;
import com.carpxt.myhr.model.from.DeletePosFrom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);

    List<Position> getPositionList();

    int deletePosByIds(DeletePosFrom from);
}