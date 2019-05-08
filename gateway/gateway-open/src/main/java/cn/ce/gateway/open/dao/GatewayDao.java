package cn.ce.gateway.open.dao;

import java.util.List;
import java.util.Map;

/**
 * @author: ggs
 * @date: 2019-05-08 16:43
 **/
public interface GatewayDao {

    List<Map<String,String>> selectClientProductMap();

    List<Map<String,String>> selectPathVersionResourceMap();

    List<Map<String,String>> selectSaasResourceTargetMap();
}
