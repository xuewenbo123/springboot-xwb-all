package com.xwb.api.service;

import com.xwb.api.model.JobTask;

import java.util.List;

public interface JobTaskService {

    JobTask selectOne(Long id);

    Long save(JobTask jobTask);

    List<JobTask> selectAll(JobTask jobTask);

    List<JobTask> selectByStatus(Integer status);

    Integer updateById(JobTask jobTask);


}
