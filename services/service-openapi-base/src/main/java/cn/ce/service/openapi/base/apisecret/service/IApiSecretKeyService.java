package cn.ce.service.openapi.base.apisecret.service;//package cn.ce.service.openapi.base.apisecret.service;
//
//import java.util.List;
//
//import cn.ce.service.openapi.base.apisecret.entity.ApiSecretKey;
//import cn.ce.service.openapi.base.common.CloudResult;
//import cn.ce.service.openapi.base.common.page.Page;
//
///***
// *
// * 管理Api秘钥服务类
// * @author lida
// * @date 2017年8月9日16:53:07
// * 
// */
//public interface IApiSecretKeyService {
//	
//	  /**
//     * 新增秘钥
//     * @param api 新增的API对象
//     */
//    void addApiSecretKey(ApiSecretKey api);
//    
//    /**
//     * 查询功能分组下唯一API
//     * @param groupId: 功能分组ID
//     * @return API
//     */
//    ApiSecretKey findOneByKey(String secretKey);
//    
//    /***
//     * 根据apiId查询秘钥
//     * @param apiId 
//     * @return
//     */
//    List<ApiSecretKey> findSecretKeyByApiIds(List<String> apiIds);
//
//    
//    List<ApiSecretKey> findSecretKeyEntity(ApiSecretKey secretKey);
//    
//    Page<ApiSecretKey> findSecretKeyEntityPage(ApiSecretKey secretKey,int currentPage,int pageSize);
//    
//    /**
//     * 删除秘钥
//     */
//    void delApi(String id);
//    
//    /**
//     * 修改秘钥
//     */
//    void updateApiSecretKey(ApiSecretKey secretKey);
//
//	CloudResult<String> allowKey(String secretKey);
//	
//}
