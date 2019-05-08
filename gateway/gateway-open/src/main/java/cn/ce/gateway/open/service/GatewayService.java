package cn.ce.gateway.open.service;


import java.util.List;
import java.util.Map;

/**
 * @author: ggs
 * @date: 2019-05-08 15:52
 **/
public interface GatewayService {
    void builtMeta();

    Map<String, String> getClientToSaas();

    Map<String, String> getPathVersionToResourceType();

    Map<String, String> getSaasTypeToUrl();

    List<Map<String,String>> selectPathVersionResourceMap();
}
