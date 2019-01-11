package com.xwb.api.controller;

import com.xwb.api.model.JobTask;
import com.xwb.api.service.JobTaskService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/job_task")
public class JobTaskController {

    @Resource
    JobTaskService jobTaskService;

    @RequestMapping(value = "/selectAll",method = RequestMethod.POST)
    public List<JobTask> selectAll(@RequestBody JobTask jobTask){
        List<JobTask> jobTasks = jobTaskService.selectAll(jobTask);
        return jobTasks;
    }


    @RequestMapping(value = "/selectByStatus")
    public List<JobTask> selectByStatus(@RequestParam("status") Integer status){
        List<JobTask> jobTasks = jobTaskService.selectByStatus(status);
        return jobTasks;
    }



    @RequestMapping(value = "/selectOne")
    public JobTask selectOne(@RequestParam("id")Long id){
        JobTask jobTask = jobTaskService.selectOne(id);
        return jobTask;
    }

    @RequestMapping(value = "/updateById",method = RequestMethod.POST)
    public String updateById(@RequestBody JobTask jobTask){
        Integer integer = jobTaskService.updateById(jobTask);
        if(integer == -1){
            return "FAILED";
        }
        return "SUCCESS";
    }




    @RequestMapping(value = "/addJob")
    public String addJob(){
        //生成几个任务，第一任务在三分钟之后
        Long unixTime = System.currentTimeMillis() + 60000;
        JobTask task = new JobTask("test-msg-1", 0, unixTime);
        jobTaskService.save(task);
        unixTime += 60000;
        task = new JobTask("test-msg-2", 0, unixTime);
        jobTaskService.save(task);
        unixTime += 60000;
        task = new JobTask("test-msg-3", 0, unixTime);
        jobTaskService.save(task);
        unixTime += 60000;
        task = new JobTask("test-msg-4", 0, unixTime);
        jobTaskService.save(task);
        return "SUCESS";
    }




}
