package com.xwb.api.service;

import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.xwb.api.handler.ElasticJobHandler;
import com.xwb.api.model.JobTask;
import com.xwb.api.utils.CronUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ElasticJobService {

    @Resource
    ElasticJobHandler elasticJobHandler;

    @Resource
    JobTaskService jobTaskService;

    /**
     * 扫描db，并添加任务
     */
    public void scanAddJob() {
        JobTask task = new JobTask();
        task.setStatus(0);
        List<JobTask> jobTasks = jobTaskService.selectAll(task);
        jobTasks.forEach(jobTask -> {
            Long current = System.currentTimeMillis();
            String jobName = "job" + jobTask.getSendTime();
            String cron;
            //说明消费未发送，但是已经过了消息的发送时间，调整时间继续执行任务
            if (jobTask.getSendTime() < current) {
                //设置为一分钟之后执行，把Date转换为cron表达式
                cron = CronUtils.getCron(new Date(current + 60000));
            } else {
                cron = CronUtils.getCron(new Date(jobTask.getSendTime()));
            }
            elasticJobHandler.addJob(jobName, cron, 3, String.valueOf(jobTask.getId()));
        });
    }


}
