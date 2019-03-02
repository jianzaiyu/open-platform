package cn.ce.framework.swagger.config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 通用Swagger配置
 *
 * @author GGs
 **/
@Configuration
@EnableSwagger2
@Profile("!pro")
@ConditionalOnProperty(name = "swagger2.enabled", havingValue = "true")
@EnableConfigurationProperties({Swagger2Properties.class})
public class Swagger2AutoConfiguration implements WebMvcConfigurer {

    @Autowired
    private Swagger2Properties swagger2Properties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(Swagger2Constants.swaggerUri.split(","))
                .addResourceLocations(Swagger2Constants.swaggerLocation.split(","));
    }

    @Bean
    @Primary
    @ConditionalOnProperty(name = "swagger2.gateway", havingValue = "true")
    public DocumentationConfig documentationConfig() {
        return new DocumentationConfig(swagger2Properties.getServiceSources());
    }

    @Bean
    public Docket createRestApi(Swagger2Properties swagger2Properties) {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).securitySchemes(Lists.newArrayList(apiKey()));
        if (!swagger2Properties.isGateway()) {
            docket = docket.select()
                    .apis(RequestHandlerSelectors.basePackage(swagger2Properties.getBasePackage()))
                    .paths(PathSelectors.any()).build();
        }
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swagger2Properties.getTitle())
//                .description(swagger2Properties.getDescription())
//                .termsOfServiceUrl(swagger2Properties.getTermsOfServiceUrl())
//                .contact(new Contact("", "", ""))
//                .version(swagger2Properties.getVersion())
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey(swagger2Properties.getApiName(), swagger2Properties.getApiKeyName(), ApiKeyVehicle.HEADER.getValue());
    }

}
