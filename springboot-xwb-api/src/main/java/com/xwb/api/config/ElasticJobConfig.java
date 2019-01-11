package com.xwb.api.config;

import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.xwb.api.listener.JobListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class ElasticJobConfig {

    @Value("${elasticjob.serverlists}")
    private String serverlists;

    @Value("${elasticjob.namespace}")
    private String namespace;

    @Resource
    private DataSource dataSource;

    @Bean
    public ZookeeperConfiguration zkConfig() {
        return new ZookeeperConfiguration(serverlists, namespace);
    }

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter(ZookeeperConfiguration config) {
        return new ZookeeperRegistryCenter(config);
    }

    /**
     * 将作业运行的痕迹进行持久化到DB
     *
     * @return
     */
    @Bean
    public JobEventConfiguration jobEventConfiguration() {
        return new JobEventRdbConfiguration(dataSource);
    }

    @Bean
    public JobListener elasticJobListener() {
        return new JobListener(100, 100);
    }


    public String getServerlists() {
        return serverlists;
    }

    public void setServerlists(String serverlists) {
        this.serverlists = serverlists;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }



}
