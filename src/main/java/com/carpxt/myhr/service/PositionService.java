package com.carpxt.myhr.service;

import com.carpxt.myhr.mapper.PositionMapper;
import com.carpxt.myhr.model.Position;
import com.carpxt.myhr.model.RespResult;
import com.carpxt.myhr.model.from.DeletePosFrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author: tianjie
 * @Date: 2020/12/16 13:39
 * @Description: TODO
 * @Version: 1.0
 */

@Service
@Transactional
public class PositionService {

    @Autowired
    PositionMapper positionMapper;

    public List<Position> getPositionList() {
        return positionMapper.getPositionList();
    }

    public Integer addPosition(Position position) {
        position.setCreateDate(new Date());
        position.setEnabled(true);
        return positionMapper.insertSelective(position);
    }

    public Integer updatePos(Position position) {
        return positionMapper.updateByPrimaryKeySelective(position);
    }

    public Integer deletePos(Integer id) {
        return positionMapper.deleteByPrimaryKey(id);
    }

    public int deletePosByIds(DeletePosFrom deletePosFrom) {
        return positionMapper.deletePosByIds(deletePosFrom);
    }
}
