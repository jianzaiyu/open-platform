package cn.ce.framework.swagger.config;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DocumentationConfig implements SwaggerResourcesProvider {
    private LinkedHashMap<String, String> serviceSources;

    public DocumentationConfig(LinkedHashMap<String, String> serviceSources) {
        this.serviceSources = serviceSources;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        for (String key : serviceSources.keySet()) {
            resources.add(swaggerResource(serviceSources.get(key), "/" + key + "/v2/api-docs"));
        }
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}