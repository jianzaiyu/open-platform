package cn.ce.service.openapi.base.openApply.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import cn.ce.service.openapi.base.openApply.entity.OpenApplyEntity;
import cn.ce.service.openapi.base.openApply.entity.QueryOpenApplyEntity;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2018年1月18日
*/
@Transactional
public interface IMysqlOpenApplyDao {
	
	int save(OpenApplyEntity openApplyEntity);

	int batchUpdateCheckState(@Param("ids") List<String> ids,
                              @Param("checkState") Integer checkState, @Param("checkMem") String checkMem);

	List<OpenApplyEntity> getListByids(@Param("ids") List<String> ids);

	OpenApplyEntity findById(@Param("id") String id);

	OpenApplyEntity findByAppId(String id);

	int findListSize(QueryOpenApplyEntity entity);

	List<OpenApplyEntity> getPagedList(QueryOpenApplyEntity entity);

	int checkApplyName(String applyName);

	int checkApplyKey(String applyKey);

	int checkApplyNameById(@Param("applyName") String applyName, @Param("applyId") String applyId);

	int checkApplyKeyById(@Param("applyKey") String applyKey, @Param("applyId") String applyId);

	int update(OpenApplyEntity openApply);

	int deleteAll();

    int deleteAllSuccess();

	int batchInsert(List<OpenApplyEntity> openApplyEntityList);

	int findSuccessSize(QueryOpenApplyEntity name);

	List<OpenApplyEntity> getSuccessedList(QueryOpenApplyEntity queryOpenApplyEntity);


}
