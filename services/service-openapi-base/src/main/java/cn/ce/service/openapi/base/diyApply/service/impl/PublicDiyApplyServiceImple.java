package cn.ce.service.openapi.base.diyApply.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import cn.ce.service.openapi.base.diyApply.entity.tenantAppPage.*;
import cn.ce.service.openapi.base.diyApply.entity.DiyApplyEntity;
import cn.ce.service.openapi.base.diyApply.entity.DiyBoundApi;
import cn.ce.service.openapi.base.diyApply.entity.tenantAppPage.*;
import cn.ce.service.openapi.base.diyApply.entity.tenantAppsEntity.TenantApps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.ce.service.openapi.base.apis.dao.IMysqlApiDao;
import cn.ce.service.openapi.base.apis.entity.ApiEntity;
import cn.ce.service.openapi.base.apis.entity.NewApiEntity;
import cn.ce.service.openapi.base.apis.service.IConsoleApiService;
import cn.ce.service.openapi.base.apis.util.ApiTransform;
import cn.ce.service.openapi.base.common.AuditConstants;
import cn.ce.service.openapi.base.common.ErrorCodeNo;
import cn.ce.service.openapi.base.common.HttpClientUtil;
import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.common.page.Page;
import cn.ce.service.openapi.base.diyApply.dao.IMysqlDiyApplyDao;
import cn.ce.service.openapi.base.diyApply.entity.appsEntity.Apps;
import cn.ce.service.openapi.base.diyApply.service.IPlublicDiyApplyService;
import cn.ce.service.openapi.base.util.PropertiesUtil;

/**
 *
 * @Title: PublicDiyApplyServiceImple.java
 * @Description
 * @version V1.0
 * @author yang.yu@300.cn
 * @date dat2017年10月18日 time下午7:52:40
 *
 **/
@Slf4j
@Service("publicDiyApplyServiceImple")
@Transactional(propagation=Propagation.REQUIRED)
public class PublicDiyApplyServiceImple implements IPlublicDiyApplyService {
	@Autowired
	private PropertiesUtil propertiesUtil;
	@Resource
	private IMysqlApiDao mysqlApiDao;
	@Resource
	private IMysqlDiyApplyDao mysqlDiyApplyDao;
	@Resource
	private IConsoleApiService consoleApiService;
	
	@Override
	public Result<Apps> findPagedApps(String sourceConfig, String owner, String name, int pageNum, int pageSize) {
		Result<Apps> result = new Result<>();
		String url = propertiesUtil.getSourceConfigValue(sourceConfig,"findPagedApps");
		String o$ = Pattern.quote("#{o}");// ${o} 所属企业 CE 为中企动力 不填为所有
		String n$ = Pattern.quote("#{n}");// ${n} 名称 模糊搜索  不填为所有
		String p$ = Pattern.quote("#{p}");// ${p} 当前第几页
		String z$ = Pattern.quote("#{z}");// ${z} 每页显示多少条
		if (StringUtils.isBlank(owner)) {
			owner = "";
		}
		if (StringUtils.isBlank(name)) {	
			name = "";
		}

		String replacedurl = url.replaceAll(o$, owner).replaceAll(n$, name).replaceAll(p$, String.valueOf(pageNum))
				.replaceAll(z$, String.valueOf(pageSize));
		log.info("请求产品中心Url:"+url);
		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
		classMap.put("list", cn.ce.service.openapi.base.diyApply.entity.appsEntity.AppList.class);
		classMap.put("appTypes", cn.ce.service.openapi.base.diyApply.entity.appsEntity.AppTypes.class);

		try {
			/* get请求方法 */
			Apps apps = (Apps) HttpClientUtil.getUrlReturnObject(replacedurl, Apps.class, classMap);
			log.info("调用产品Http请求发送成功");
			/* 无接口时的测试方法 */
			// Apps apps = (Apps) testgetUrlReturnObject("findPagedApps", replacedurl,
			// Apps.class, classMap);
			if (apps.getStatus().equals("200") || apps.getStatus().equals("110")) {
				result.setSuccessData(apps);
				return result;
			} else {
				log.error("findPagedApps data http getfaile return code :" + apps.getMsg() + " ");
				result.setErrorCode(ErrorCodeNo.SYS006);
				return result;
			}
		} catch (Exception e) {
			
			log.error("findPagedApps http error " + e + "");
			result.setErrorCode(ErrorCodeNo.SYS018);
			result.setErrorMessage("请求失败");
			return result;
		}

	}

	@Override
	public Result<TenantApps> findTenantAppsByTenantKey(String sourceConfig, String key) {

		// TODO 需要把key查询出来的定制应用和多个开放应用的绑定关系存入数据库

		Result<TenantApps> result = new Result<>();
		String url = propertiesUtil.getSourceConfigValue(sourceConfig,"findTenantAppsByTenantKey");
		String key$ = Pattern.quote("#{key}");
		String replacedurl = url.replaceAll(key$, key);
		try {
			/* get请求方法 */
			TenantApps applyproduct = (TenantApps) HttpClientUtil.getUrlReturnObject(replacedurl, TenantApps.class, null);

			if (applyproduct.getStatus() != null && applyproduct.getStatus().equals("200")) {
				result.setSuccessData(applyproduct);
				return result;
			} else {
				log.error(
						"findTenantAppsByTenantKey data http getfaile return code :" + applyproduct.getMsg() + " ");
				result.setErrorCode(ErrorCodeNo.SYS024);
				return result;
			}
		} catch (Exception e) {
			log.error("findTenantAppsByTenantKey http error " + e + "");
			result.setErrorCode(ErrorCodeNo.SYS024);
			result.setErrorMessage("请求失败");
			return result;
		}

	}

	@Override
	public Result<TenantAppPage> findTenantAppsByTenantKeyPage(String sourceConfig, String key, String appName, int pageNum, int pageSize) {

		Result<TenantAppPage> result = new Result<>();
		String url =  propertiesUtil.getSourceConfigValue(sourceConfig,"findTenantAppsByTenantKeypage");

		String key$ = Pattern.quote("#{key}");
		String n$ = Pattern.quote("#{n}");
		String p$ = Pattern.quote("#{p}");
		String z$ = Pattern.quote("#{z}");
		if (StringUtils.isBlank(appName)) {
			appName = "";
		}
		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();

		classMap.put("tenant", Tenant.class);
		classMap.put("pages", Pages.class);
		classMap.put("list", cn.ce.service.openapi.base.diyApply.entity.tenantAppPage.List.class);
		classMap.put("attr", Attr.class);
		classMap.put("params", Params.class);
		classMap.put("instanceList", InstanceList.class);

		String replacedurl = url.replaceAll(key$, key).replaceAll(n$, appName).replaceAll(z$, String.valueOf(pageSize))
				.replaceAll(p$, String.valueOf(pageNum));
		try {
			TenantAppPage applyproduct = (TenantAppPage) HttpClientUtil.getUrlReturnObject(replacedurl,
					TenantAppPage.class, classMap);

			if (applyproduct.getStatus().equals("200")) {
				result.setData(applyproduct);
				result.setSuccessMessage("");
				return result;
			} else {
				log.error(
						"findTenantAppsByTenantKey data http getfaile return code :" + applyproduct.getMsg() + " ");
				result.setErrorCode(ErrorCodeNo.SYS006);
				return result;
			}
		} catch (Exception e) {
			log.error("findTenantAppsByTenantKey http error " + e + "");
			result.setErrorCode(ErrorCodeNo.SYS001);
			result.setErrorMessage("请求失败");
			return result;
		}
	}

	@Override
	public Result<?> limitScope(String diyApplyId, String openApplyId, String apiName, Integer currentPage, Integer pageSize ) {

		Result<Page<ApiEntity>> result = new Result<Page<ApiEntity>>();
		DiyApplyEntity diyEntity = mysqlDiyApplyDao.findById(diyApplyId);
		if (null == diyEntity) {
			result.setErrorMessage("当前id不存在", ErrorCodeNo.SYS015);
			return result;
		}
		
		List<DiyBoundApi> boundApis = mysqlDiyApplyDao.findBoundApi(diyApplyId, openApplyId);
		if(boundApis.size() < 1){
			result.setErrorMessage("当前定制应用暂时未绑定该开放应用", ErrorCodeNo.SYS017);
			return result;
		}
		List<String> apiIds = new ArrayList<String>();
		for (DiyBoundApi diyBoundApi : boundApis) {
			apiIds.add(diyBoundApi.getApiId());
		}
		
		int num = mysqlApiDao.findByIdsAndNameLikeNum(apiIds,apiName,AuditConstants.API_CHECK_STATE_SUCCESS,(currentPage-1)*pageSize,pageSize);
		List<NewApiEntity> apiList = mysqlApiDao.findByIdsAndNameLike(apiIds, apiName, AuditConstants.API_CHECK_STATE_SUCCESS, (currentPage-1)*pageSize, pageSize);
		for (NewApiEntity api : apiList) {
			api.setOrgPath(null); //通过定制应用看到的api列表不显示回源地址
		}
		
		Page<ApiEntity> page = new Page<ApiEntity>(currentPage,num,pageSize);
		page.setItems(ApiTransform.transToApis(apiList));
		result.setSuccessData(page);
		return result;
	}
}
