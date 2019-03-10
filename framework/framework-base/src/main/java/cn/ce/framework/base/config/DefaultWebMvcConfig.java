package cn.ce.framework.base.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ggs
 * @date 2018/8/5 16:17
 */

@Configuration
public class DefaultWebMvcConfig implements WebMvcConfigurer {


    @Bean
    @ConditionalOnMissingBean
    public GlobalExceptionAdvice globalExceptionAdvice() {
        return new GlobalExceptionAdvice();
    }

    @Bean
    @ConditionalOnMissingBean
    public GlobalResponseAdvice globalResponseAdvice() {
        return new GlobalResponseAdvice();
    }

    /**
     * 处理1000个样本：
     a.在样本量为1 10 100的时候，也就是对象大小为1k 10k 100k 的时候，GSON的性能一直领先.
     在这三个量级的情况下， GSON > JSONSMART > JACKSON >  FASTJSON
     b.在样本量 1000 ，也就是对象大小为 1M 的时候，SMARTJSON 反超 GSON
     在这个量级下， SMARTJSON > GSON >  FASTJSON > JACKSON
     c.在样本量 10000 100000，也就是对象大小为 10M 100M的时候，JSON 变慢的最明显,JACKSON 与 FASTJSON 性能最优最稳定
     在这个量级下， JACKSON > FASTJSON > SMARTJSON >  GSON 。
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        super.configureMessageConverters(converters);
        //1.需要先定义一个 convert 转换消息的对象;
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();

        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,SerializerFeature.DisableCheckSpecialChar);
        // 不忽略对象属性中的null值
        // WriteNullListAsEmpty,
        // WriteNullStringAsEmpty);
        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //3、在convert中添加配置信息.
//        converters.clear();
//        converters.add(fastConverter);
        converters.add(7,fastConverter);
    }

    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

    /**
     * tomcat默认不解析PUT DELETE的BODY
     * 声明这个过滤器来解决 HttpPutFormContentFilter
     */
}
