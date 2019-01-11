package com.xwb.api.mapper;

import com.xwb.api.model.JobTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobTaskMapper {

    JobTask selectOne(@Param("id") Long id);

    List<JobTask> selectAll(@Param("jobTask") JobTask jobTask);

    List<JobTask> selectByStatus(@Param("status") Integer status);

    Long save(@Param("jobTask") JobTask jobTask);

    Integer updateById(@Param("jobTask") JobTask jobTask);

}
