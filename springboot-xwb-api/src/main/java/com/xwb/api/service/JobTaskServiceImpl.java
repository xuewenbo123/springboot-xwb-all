package com.xwb.api.service;

import com.xwb.api.mapper.JobTaskMapper;
import com.xwb.api.model.JobTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobTaskServiceImpl implements JobTaskService{

    @Autowired
    JobTaskMapper jobTaskMapper;

    @Override
    public JobTask selectOne(Long id) {
        JobTask jobTask = jobTaskMapper.selectOne(id);
        return jobTask;
    }

    @Override
    public Long save(JobTask jobTask) {
        Long id = jobTaskMapper.save(jobTask);
        return id;
    }

    @Override
    public List<JobTask> selectAll(JobTask jobTask) {
        List<JobTask> jobTasks = jobTaskMapper.selectAll(jobTask);
        return jobTasks;
    }


    @Override
    public List<JobTask> selectByStatus(Integer status) {
        List<JobTask> jobTasks = jobTaskMapper.selectByStatus(status);
        return jobTasks;
    }


    @Override
    public Integer updateById(JobTask jobTask) {
        if(jobTask.getId() == null){
            return -1;
        }
        Integer row = jobTaskMapper.updateById(jobTask);
        return row;
    }


}
