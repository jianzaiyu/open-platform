package cn.ce.service.openapi.base.apis.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.ce.service.openapi.base.apis.entity.ApiResultEntity;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2018年1月19日
*/
@Transactional(propagation=Propagation.REQUIRED)
public interface IMysqlApiResultDao {

	int save(ApiResultEntity result);

	List<ApiResultEntity> findByApiId(String apiId);

	int deleteByApiId(String apiId);

}
