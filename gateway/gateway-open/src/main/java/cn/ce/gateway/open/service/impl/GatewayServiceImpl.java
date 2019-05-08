package cn.ce.gateway.open.service.impl;

import cn.ce.gateway.open.dao.GatewayDao;
import cn.ce.gateway.open.service.GatewayService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: ggs
 * @date: 2019-05-08 17:07
 **/
@Service
public class GatewayServiceImpl implements GatewayService {
    @Autowired
    private GatewayDao gatewayDao;
    @Getter
    private Map<String, String> clientToSaas = new HashMap<>();
    @Getter
    private Map<String, String> pathVersionToResourceType = new HashMap<>();
    @Getter
    private Map<String, String> saasTypeToUrl = new HashMap<>();
    @Override
    public void builtMeta() {
        List<Map<String, String>> list1 = gatewayDao.selectClientProductMap();
        List<Map<String, String>> list2 = gatewayDao.selectPathVersionResourceMap();
        List<Map<String, String>> list3 = gatewayDao.selectSaasResourceTargetMap();
        for (Map<String, String> map1 : list1) {
            clientToSaas.put(map1.get("client_id"), map1.get("product_instance_id"));
        }
        for (Map<String, String> map2 : list2) {
            pathVersionToResourceType.put(map2.get("listen_path") + map2.get("version"), map2.get("resource_type"));
        }
        for (Map<String, String> map3 : list3) {
            saasTypeToUrl.put(map3.get("saas_id") + map3.get("resource_type"), map3.get("target_url"));
        }
    }

    @Override
    public List<Map<String, String>> selectPathVersionResourceMap() {
        return gatewayDao.selectPathVersionResourceMap();
    }
}
