package com.xwb.job.config;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 配置类
 * @Author Mr.Xue
 */
@Configuration
//判断是否配置了zookeeper 地址
@ConditionalOnExpression("'${spring.elasticjob.serverList}'.length() > 0")
public class ElasticJobConfig {

    @Value("${rxQuartz.app.zkAddress}")
    private String zkNodes;

    @Value("${rxQuartz.app.namespace}")
    private String namespace;

    @Bean
    public ZookeeperConfiguration zkConfig() {
        return new ZookeeperConfiguration(zkNodes, namespace);
    }

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter(ZookeeperConfiguration config) {
        return new ZookeeperRegistryCenter(config);
    }

    public String getZkNodes() {
        return zkNodes;
    }

    public void setZkNodes(String zkNodes) {
        this.zkNodes = zkNodes;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
