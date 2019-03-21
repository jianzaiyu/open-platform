package cn.ce.service.openapi.base.apisecret.dao;//package cn.ce.service.openapi.base.apisecret.dao;
//
//import java.util.List;
//
//import cn.ce.service.openapi.base.apisecret.entity.ApiSecretKey;
//import cn.ce.service.openapi.base.common.page.Page;
//
///***
// * 
// * 操作 
// * @author lida
// * @date 2017年8月9日16:23:13
// *
// */
//public interface IApiSecretKeyDao {
//	
//    /**
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
//    /***
//     * 根据实体对象分页查询SecretKey集合
//     * @param entity 查询的实体对象
//     * @param currentPage 
//     * @param pageSize
//     * @return
//     */
//    Page<ApiSecretKey> findSecretKeyPageByEntity(ApiSecretKey entity,int currentPage,int pageSize);
//    
//    /***
//     * 根据实体对象查询SecretKey集合
//     * @param entity 查询的实体对象
//     * @param currentPage 
//     * @param pageSize
//     * @return
//     */
//    List<ApiSecretKey> findSecretKeyByEntity(ApiSecretKey entity);
//    
//    /**
//     * 删除秘钥
//     */
//    int delApi(String id);
//    
//    /**
//     * 修改秘钥
//     */
//    void updateApiSecretKey(ApiSecretKey secretKey);
//
//    /**
//     * 根据属性查询secretKey
//     * @param secretKey
//     * @param string
//     * @return
//     */
//    ApiSecretKey findOneByField(String key, String value);
//	
//}
