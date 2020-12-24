package com.carpxt.myhr.mapper;

import com.carpxt.myhr.model.JobLevel;
import com.carpxt.myhr.model.Position;
import com.carpxt.myhr.model.from.DeletePosFrom;
import com.carpxt.myhr.model.from.Jobdata;

import java.util.List;

public interface JobLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobLevel record);

    int insertSelective(JobLevel record);

    JobLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobLevel record);

    int updateByPrimaryKey(JobLevel record);

    List<JobLevel> getJobLevelList();

    int deleteJobLevelByIds(DeletePosFrom deletePosFrom);

    void save(List<Jobdata> list);
}