package com.xwb.api.listener;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.AbstractDistributeOnceElasticJobListener;
import com.xwb.api.model.JobTask;
import com.xwb.api.service.JobTaskService;

import javax.annotation.Resource;

public class JobListener extends AbstractDistributeOnceElasticJobListener {

    @Resource
    private JobTaskService jobTaskService;

    public JobListener(long startedTimeoutMilliseconds, long completedTimeoutMilliseconds) {
        super(startedTimeoutMilliseconds, completedTimeoutMilliseconds);
    }

    @Override
    public void doBeforeJobExecutedAtLastStarted(ShardingContexts shardingContexts) {
    }

    @Override
    public void doAfterJobExecutedAtLastCompleted(ShardingContexts shardingContexts) {
        //任务执行完成后更新状态为已执行
        JobTask jobTask = jobTaskService.selectOne(Long.valueOf(shardingContexts.getJobParameter()));
        jobTask.setStatus(1);
        jobTaskService.updateById(jobTask);
    }
}