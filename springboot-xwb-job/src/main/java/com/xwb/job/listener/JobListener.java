package com.xwb.job.listener;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;

public class JobListener implements ElasticJobListener{

    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        System.out.println("============JOB=====beforeJobExecuted==========");
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        //此处可以更新任务状态
        System.out.println("============JOB=====afterJobExecuted==========");
    }
}
