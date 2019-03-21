package cn.ce.service.openapi.base.gateway.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.ce.service.openapi.base.gateway.entity.QuerySaasEntity;
import cn.ce.service.openapi.base.gateway.entity.SaasEntity;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2018年3月22日
*/
@Transactional(propagation=Propagation.REQUIRED)
public interface IMysqlSaasDao {


	void clearAll();

	//保存实体
	int save(SaasEntity s);
	
	/**
	 * @Description: 获取不带沙箱id的路由
	 * @author: makangwei 
	 * @date:   2018年3月31日 上午11:58:28 
	 */
	SaasEntity getSaas(@Param("saasId") String saasId, @Param("resourceType") String resourceType);
	
	/**
	 * @Description: 更新不带沙箱id的路由
	 * @author: makangwei 
	 * @date:   2018年3月31日 上午11:58:55 
	 */
	int updateSaas(@Param("saasId") String saasId, @Param("resourceType") String resourceType, @Param("targetUrl") String targetUrl);
	
	/**
	 * @Description: 删除不带沙箱的路由
	 * @author: makangwei 
	 * @date:   2018年3月31日 上午11:59:20 
	 */
	int deleteSaas(@Param("saasId") String saasId, @Param("resourceType") String resourceType);
	
	SaasEntity getById(@Param("routeId") String routeId);

	int updateBoxSaas(SaasEntity saasEntity);

	int deleteById(@Param("routeId") String routeId);
	
	int findListSize(QuerySaasEntity saas);
	
	List<SaasEntity> getPagedList(QuerySaasEntity saas);

	SaasEntity getBoxSaas(@Param("saasId") String saasId, @Param("resourceType") String resourceType, @Param("sandboxId") String sandboxId);



	
}

