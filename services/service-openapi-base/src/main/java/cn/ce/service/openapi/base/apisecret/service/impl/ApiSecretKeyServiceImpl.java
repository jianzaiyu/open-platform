package cn.ce.service.openapi.base.apisecret.service.impl;//package cn.ce.service.openapi.base.apisecret.service.impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import cn.ce.service.openapi.base.apisecret.dao.IApiSecretKeyDao;
//import cn.ce.service.openapi.base.apisecret.entity.ApiSecretKey;
//import cn.ce.service.openapi.base.apisecret.service.IApiSecretKeyService;
//import cn.ce.service.openapi.base.common.CloudResult;
//import cn.ce.service.openapi.base.common.page.Page;
//
//@Service("apiSecretKeyService")
//public class ApiSecretKeyServiceImpl implements IApiSecretKeyService {
//
//	@Autowired @Qualifier("apiSecretDao") 
//	private IApiSecretKeyDao apiSecretDao;
//	
//	@Override
//	public void addApiSecretKey(ApiSecretKey api) {
//		apiSecretDao.addApiSecretKey(api);
//	}
//
//	@Override
//	public ApiSecretKey findOneByKey(String secretKey) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<ApiSecretKey> findSecretKeyByApiIds(List<String> apiIds) {
//		// TODO Auto-generated method stub
//		return apiSecretDao.findSecretKeyByApiIds(apiIds);
//	}
//
//	@Override
//	public List<ApiSecretKey> findSecretKeyEntity(ApiSecretKey secretKey) {
//		return apiSecretDao.findSecretKeyByEntity(secretKey);
//	}
//
//	@Override
//	public void delApi(String id) {
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	public void updateApiSecretKey(ApiSecretKey secretKey) {
//		apiSecretDao.updateApiSecretKey(secretKey);
//	}
//
//	@Override
//	public Page<ApiSecretKey> findSecretKeyEntityPage(ApiSecretKey secretKey,int currentPage,int pageSize) {
//		return apiSecretDao.findSecretKeyPageByEntity(secretKey, currentPage, pageSize);	
//	}
//	
//	@Override
//	public CloudResult<String> allowKey(String secretKey) {
//		
//		CloudResult<String> result = new CloudResult<String>();
//		//1、添加key到网关
//		ApiSecretKey keyEntity = apiSecretDao.findOneByField(secretKey,"secretKey");
//		
//		if(keyEntity == null){
//			result.setMessage("数据库中不存在密钥。请检查后重新输入密钥");
//		}
//		
//		
//		//2、如果添加key到网关成功，则修改数据库数据，否则回滚网关
//		
//		return null;
//	}
//}
