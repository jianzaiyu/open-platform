package cn.ce.service.openapi.base.apis.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.ce.service.openapi.base.apis.entity.ApiArgEntity;

/**
* @Description : query参数dao
* @Author : makangwei
* @Date : 2018年3月16日
*/
@Transactional(propagation=Propagation.REQUIRED)
public interface IMysqlApiQueryArgDao {
	
	int save(ApiArgEntity arg);

	List<ApiArgEntity> findByApiId(String apiId);

	int deleteByApiId(String apiId);

	int deleteByImport();

	int saveImport(ApiArgEntity a);
}

