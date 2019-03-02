package cn.ce.framework.hbase.config;

import cn.ce.framework.hbase.support.HBaseHelper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author: ggs
 * @date: 2018-11-28 14:00
 **/
@Configuration
@EnableConfigurationProperties(HBaseProperty.class)
public class HBaseConfig {

    @Bean
    public HBaseConnectPool hBaseConnectPool(HBaseProperty hBaseProperty) {
        HBaseConnectPool hBaseConnectPool = HBaseConnectPool.getInstance();
        hBaseConnectPool.init(hBaseProperty);
        return hBaseConnectPool;
    }

    @Bean
    public HBaseHelper hBaseHelper(HBaseConnectPool hBaseConnectPool){
        return new HBaseHelper(hBaseConnectPool);
    }
}
