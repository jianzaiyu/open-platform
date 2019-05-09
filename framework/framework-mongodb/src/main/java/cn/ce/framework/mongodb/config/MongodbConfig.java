package cn.ce.framework.mongodb.config;

import cn.ce.framework.mongodb.common.MongodbProperties;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ggs
 * @date: 2019-05-09 14:05
 **/
@Slf4j
@Configuration
@EnableConfigurationProperties(MongodbProperties.class)
public class MongodbConfig {
    @Autowired
    private MongodbProperties mongodbProperties;

    @Bean
    public MongoDbFactory mongoDbFactory() {
        //客户端配置（连接数、副本集群验证）
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.connectionsPerHost(mongodbProperties.getConnectionsPerHost());
        builder.minConnectionsPerHost(mongodbProperties.getMinConnectionsPerHost());
        if (mongodbProperties.getReplicaSet() != null) {
            builder.requiredReplicaSetName(mongodbProperties.getReplicaSet());
        }
        MongoClientOptions mongoClientOptions = builder.build();

        // MongoDB地址列表
        List<ServerAddress> serverAddresses = new ArrayList<>();
        for (String host : mongodbProperties.getHosts()) {
            Integer index = mongodbProperties.getHosts().indexOf(host);
            Integer port = mongodbProperties.getPorts().get(index);

            ServerAddress serverAddress = new ServerAddress(host, port);
            serverAddresses.add(serverAddress);
        }
        log.info("serverAddresses:" + serverAddresses.toString());
        MongoCredential mongoCredential = null;
        // 连接认证
        if (mongodbProperties.getUsername() != null) {
            mongoCredential = MongoCredential.createScramSha1Credential(
                    mongodbProperties.getUsername(),
                    mongodbProperties.getAuthenticationDatabase() != null ? mongodbProperties.getAuthenticationDatabase() : mongodbProperties.getDatabase(),
                    mongodbProperties.getPassword().toCharArray());
        }

        if (mongoCredential != null) {
            return new SimpleMongoDbFactory(new MongoClient(serverAddresses, mongoCredential, mongoClientOptions), mongodbProperties.getDatabase());
        } else {
            return new SimpleMongoDbFactory(new MongoClient(serverAddresses, mongoClientOptions), mongodbProperties.getDatabase());
        }
    }
}
