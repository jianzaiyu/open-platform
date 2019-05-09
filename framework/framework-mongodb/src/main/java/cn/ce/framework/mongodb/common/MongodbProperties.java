package cn.ce.framework.mongodb.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author: ggs
 * @date: 2019-05-09 14:04
 **/
@Data
@ConfigurationProperties(prefix = "spring.data.mongodb.custom")
public class MongodbProperties {
    private String database;
    private List<String> hosts;
    private List<Integer> ports;
    private String replicaSet;
    private String username;
    private String password;
    private String authenticationDatabase;
    private Integer minConnectionsPerHost = 10;
    private Integer connectionsPerHost = 2;
}
