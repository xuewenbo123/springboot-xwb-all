package com.xwb.api.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

public class MyElasticJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        //打印出任务相关信息，JobParameter用于传递任务的ID
        System.out.println("======execute===jobName==="+shardingContext.getJobName());
    }

}