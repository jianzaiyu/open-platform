package cn.ce.service.openapi.base.openApply.dao;//package cn.ce.service.openapi.base.openApply.dao;
//
//import java.util.List;
//import java.util.Map;
//
//import cn.ce.service.openapi.base.common.page.Page;
//import cn.ce.service.openapi.base.core.bean.MongoDBWhereEntity;
//import cn.ce.service.openapi.base.openApply.entity.OpenApplyEntity;
//
///**
//* @Description : 说明
//* @Author : makangwei
//* @Date : 2017年10月11日
//*/
//public interface INewOpenApplyDao {
//
//
//	OpenApplyEntity save(OpenApplyEntity apply);
//	
//	int batchSaveApply(List<String> ids,Integer checkState);
//
//	OpenApplyEntity findOpenApplyById(String id);
//
//	List<OpenApplyEntity> findOpenApplyByNameOrKey(String applyName, String applyKey);
//
//	List<OpenApplyEntity> findOpenApplyByEntity(Map<String,MongoDBWhereEntity> whereEntity);
//	
//	Page<OpenApplyEntity> findOpenApplyByEntity(Map<String,MongoDBWhereEntity> whereEntity,Page<OpenApplyEntity> page);
//	
//	List<OpenApplyEntity> findOpenApplyByNeqId(OpenApplyEntity apply);
//
//	List<OpenApplyEntity> findAll();
//
//}
