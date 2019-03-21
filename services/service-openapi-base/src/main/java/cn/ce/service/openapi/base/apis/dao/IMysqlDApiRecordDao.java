package cn.ce.service.openapi.base.apis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ce.service.openapi.base.apis.entity.DApiRecordEntity;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2018年1月31日
*/
public interface IMysqlDApiRecordDao {

	int save(DApiRecordEntity recordEntity);

	int saveBoundApis(@Param("downId") String downId, @Param("apiIds") List<String> apiIds);

	DApiRecordEntity findTotalOneById(String recordId);

}
