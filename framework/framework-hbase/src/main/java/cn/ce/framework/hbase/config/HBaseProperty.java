package cn.ce.framework.hbase.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: ggs
 * @date: 2018-11-27 14:42
 **/
@Data
@ConfigurationProperties(prefix = "hbase")
public class HBaseProperty {

    private int maxIdel = 1;

    private int maxTotal = 8;

    private int minEvictIdleTimeMillis = 60000;

    private int minIdle = 0;

    private String clusterDistributed;

    private String zookeeperQuorum;

    private String zookeeperZnodeParent;

    private String zookeeperUseMulti;

    private String zookeeperSessionTimeout;
}
