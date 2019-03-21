package cn.ce.service.openapi.base.apis.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.ce.service.openapi.base.apis.entity.ApiCodeEntity;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2018年1月19日
*/
@Transactional(propagation=Propagation.REQUIRED)
public interface IMysqlApiCodeDao {

	int save(ApiCodeEntity code);

	List<ApiCodeEntity> findByApiId(String apiId);

	int deleteByApiId(String apiId);

}
