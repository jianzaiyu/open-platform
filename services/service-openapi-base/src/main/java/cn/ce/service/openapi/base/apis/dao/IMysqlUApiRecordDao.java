package cn.ce.service.openapi.base.apis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ce.service.openapi.base.apis.entity.UApiRecordEntity;
import cn.ce.service.openapi.base.apis.entity.UApiRecordList;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2018年1月31日
*/
public interface IMysqlUApiRecordDao {

	int save(UApiRecordEntity recordEntity);

	int saveBoundApi(@Param("uploadId") String uploadId, @Param("records") List<UApiRecordList> records);

}
