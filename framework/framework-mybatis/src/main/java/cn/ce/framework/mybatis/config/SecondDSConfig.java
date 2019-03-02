package cn.ce.framework.mybatis.config;

import cn.ce.framework.mybatis.scanner.MapperScanner;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ObjectUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: ggs
 * @date: 2018-08-31 14:16
 **/
@Slf4j
@Configuration
@AutoConfigureAfter(PrimaryDSConfig.class)
@ConditionalOnProperty(name = "spring.multids.enabled", havingValue = "true")
@MapperScanner(basePackages = "${mybatis.secScanPackages}", sqlSessionFactoryRef = "secSqlSessionFactory")
public class SecondDSConfig {

    @Value("${mybatis.secMapperLocations}")
    private String[] secMapperLocations;


    @Bean("secDataSource")
    @ConfigurationProperties(prefix = "spring.sec-datasource")
    public DataSource dataSource() {
        log.info("Init Second DruidDataSource");
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("secSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("secDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setVfs(SpringBootVFS.class);
        if (!ObjectUtils.isEmpty(resolveMapperLocations())) {
            factory.setMapperLocations(resolveMapperLocations());
        }
        return factory.getObject();
    }

    public Resource[] resolveMapperLocations() {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<Resource> resources = new ArrayList<Resource>();
        if (secMapperLocations != null) {
            for (String mapperLocation : secMapperLocations) {
                try {
                    Resource[] mappers = resourceResolver.getResources(mapperLocation);
                    resources.addAll(Arrays.asList(mappers));
                } catch (IOException e) {
                    // ignore
                }
            }
        }
        return resources.toArray(new Resource[resources.size()]);
    }
}
