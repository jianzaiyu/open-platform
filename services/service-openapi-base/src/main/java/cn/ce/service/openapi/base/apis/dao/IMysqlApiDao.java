package cn.ce.service.openapi.base.apis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.ce.service.openapi.base.apis.entity.ApiEntity;
import cn.ce.service.openapi.base.apis.entity.DiyApplyBound;
import cn.ce.service.openapi.base.apis.entity.NewApiEntity;
import cn.ce.service.openapi.base.apis.entity.OpenApplyBound;
import cn.ce.service.openapi.base.apis.entity.QueryApiEntity;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2018年1月18日
*/
@Transactional(propagation=Propagation.REQUIRED)
public interface IMysqlApiDao {
	
	public int save(ApiEntity apiEntity);

	public void save1(NewApiEntity apiEntity);

	public List<NewApiEntity> findByListenPath(String listenPath);

	public NewApiEntity findById(String apiId);

	public int checkListenPath(String listenPath);

	public int saveOrUpdateEntity(NewApiEntity apiEntity);

	public int findVersionNum(@Param("versionId") String versionId, @Param("version") String version);
	
	public int findVersionNumExpId(@Param("apiId") String apiId, @Param("versionId") String versionId, @Param("version") String version);

	public int updateVersionByVersionId(@Param("versionId") String versionId, @Param("newVersion") boolean b);

	public int updateCheckState(@Param("apiId") String apiId, @Param("checkState") Integer checkState);

	public NewApiEntity findTotalOneById(String apiId);

	/**
	 * 
	 * @Description: 根据条件查询条数
	 * @author: makangwei 
	 * @date:   2018年1月23日 下午3:23:50 
	 */
	public int findListSize(QueryApiEntity entity);

	public List<NewApiEntity> getPagedList(QueryApiEntity entity);

	public int checkApiChName(@Param("apiChName") String apiChName, @Param("openApplyId") String openApplyId);
	
	public int checkVersion(@Param("versionId") String versionId, @Param("version") String version);

	public int checkId(String id);
	
	public List<NewApiEntity> findByOpenApply(String openApplyId);

	public int findByIdsAndNameLikeNum(@Param("apiIds") List<String> apiIds, @Param("apiName") String apiName,
                                       @Param("checkState") Integer checkState, @Param("startIndex") int startIndex, @Param("pageSize") Integer pageSize);

	public List<NewApiEntity> findByIdsAndNameLike(@Param("apiIds") List<String> apiIds, @Param("apiName") String apiName,
                                                   @Param("checkState") Integer checkState, @Param("startIndex") int startIndex, @Param("pageSize") Integer pageSize);

	public List<NewApiEntity> findByIds(@Param("apiIds") List<String> apiIds);

	public List<NewApiEntity> findByVersionId(String versionId);

	public List<NewApiEntity> findByVersionIdExp(@Param("versionId") String versionId, @Param("id") String id);

	public List<String> findIdByCheckState(Integer checkState);

	public List<String> findIdByIdsOrOpenApplys(@Param("apiIds") List<String> apiIds, @Param("appIds") List<String> appIds, @Param("checkState") Integer checkState);

	public List<NewApiEntity> findTotalOnesByIdsAndCheckState(@Param("apiIds") List<String> apiIds, @Param("checkState") Integer checkState);

	public int deleteTotalOnesByIds(List<String> apiIds);

	public int clearAll();

	public List<NewApiEntity> findApiByApplyIdsAndCheckState(@Param("openApplyIds") List<String> openApplyIds, @Param("checkState") int checkState,
                                                             String apiTypeOpen);

	// warn 返回的是开放应用绑定的version_id 作为api_id
	// 该接口作为网关获取开放应用和api绑定关系的调用。不作为其它调用
	public List<OpenApplyBound> getOpenApplyBound();

    // warn 返回的是开放应用绑定的version_id 作为api_id
 	// 该接口作为定制应用绑定开放应用和api绑定关系的调用。不作为其它调用
	public List<DiyApplyBound> getDiyApplyBound();

	public List<NewApiEntity> findByVersionIds(List<String> versionIds);
	
	


    NewApiEntity findByListenPathAndVersion(@Param("listenPath") String listenPath, @Param("version") String version);

    int findTotalSuccessApi();

	int getTotalOpenApply();
}