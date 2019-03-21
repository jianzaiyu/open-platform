package cn.ce.service.openapi.base.apis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.ce.service.openapi.base.apis.entity.ApiArgEntity;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2018年1月19日
*/
@Transactional(propagation=Propagation.REQUIRED)
public interface IMysqlApiArgDao {

	int save(ApiArgEntity arg);

	List<ApiArgEntity> findByApiId(String apiId);

	int deleteByApiId(String apiId);

	List<ApiArgEntity> getAllGetParam();

	int updateImport(@Param("argId") String argId);

	int deleteImport();

}
