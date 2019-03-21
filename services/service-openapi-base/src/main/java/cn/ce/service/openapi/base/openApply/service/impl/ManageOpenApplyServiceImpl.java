package cn.ce.service.openapi.base.openApply.service.impl;

import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.ce.service.openapi.base.common.AuditConstants;
import cn.ce.service.openapi.base.common.ErrorCodeNo;
import cn.ce.service.openapi.base.common.HttpClientUtil;
import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.common.page.Page;
import cn.ce.service.openapi.base.diyApply.entity.inparameter.SaveOrUpdateAppsInParameterEntity;
import cn.ce.service.openapi.base.diyApply.entity.interfaceMessageInfo.InterfaMessageInfoString;
import cn.ce.service.openapi.base.openApply.dao.IMysqlOpenApplyDao;
import cn.ce.service.openapi.base.openApply.entity.OpenApplyEntity;
import cn.ce.service.openapi.base.openApply.entity.QueryOpenApplyEntity;
import cn.ce.service.openapi.base.openApply.service.IManageOpenApplyService;
import cn.ce.service.openapi.base.util.PropertiesUtil;
import net.sf.json.JSONArray;

/**
 * @ClassName: openApplyServiceImpl
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: author
 * @date: 2017年10月11日 下午5:24:58
 * @Copyright: 2017 中企动力科技股份有限公司 © 1999-2017 300.cn All Rights Reserved
 */
@Slf4j
@Service(value = "manageOpenApplyService")
@Transactional(propagation=Propagation.REQUIRED)
public class ManageOpenApplyServiceImpl implements IManageOpenApplyService {

	@Autowired
	private PropertiesUtil propertiesUtil;
	/** 功能分组数据库操作对象 */
//	@Resource
//	private IOpenApplyDao openApplyDao;
	@Resource
	private IMysqlOpenApplyDao mysqlOpenApplyDao;

//	public void addApp(OpenApplyEntity app) {
//		openApplyDao.addApp(app);
//	}

//	@Override
//	public void modifyById(OpenApplyEntity app) {
//		openApplyDao.modifyById(app.getId(), app);
//	}

	@Override
	public Result<OpenApplyEntity> findById(String id) {
		Result<OpenApplyEntity> result = new Result<>();
		//OpenApplyEntity findById = openApplyDao.findById(id);
		OpenApplyEntity findById = mysqlOpenApplyDao.findById(id);
		if (null == findById) {
			result.setErrorMessage("应用不存在!",ErrorCodeNo.SYS009);
			return result;
		}
		result.setSuccessData(findById);
		return result;
	}

//	@Override
//	public List<OpenApplyEntity> getAll() {
//		return openApplyDao.getAll();
//	}

//	@Override
//	public Page<OpenApplyEntity> getAppList(String userId, int currentPage, int pageSize) {
//		PageContext.setCuurentPage(currentPage);
//		PageContext.setPageSize(pageSize);
//		return openApplyDao.getAppList(userId);
//	}

//	@Override
//	public void delById(String id) {
//		openApplyDao.delById(id);
//
//	}

//	@Override
//	public OpenApplyEntity findAppByAppName(String appName) {
//		return openApplyDao.findAppByAppName(appName);
//	}

//	@Override
//	public Page<OpenApplyEntity> getAppListByDBWhere(OpenApplyEntity appentity, int currentPage, int pageSize) {
//		return openApplyDao.findAppsByEntity(appentity, currentPage, pageSize);
//	}
//
//	@Override
//	public Page<OpenApplyEntity> findAppsByEntity(OpenApplyEntity entity, int currentPage, int pageSize) {
//		return openApplyDao.findAppsByEntity(entity, currentPage, pageSize);
//	}
//
//	@Override
//	public List<OpenApplyEntity> findAppsByEntity(OpenApplyEntity entity) {
//		return openApplyDao.findAppsByEntity(entity);
//	}
//
//	@Override
//	public CloudResult<OpenApplyEntity> checkAppIsHave(OpenApplyEntity entity) {
//		boolean isTrue = false;
//		CloudResult<OpenApplyEntity> result = new CloudResult<>();
//		OpenApplyEntity findAppByAppName = this.findAppByAppName(entity.getApplyName());
//		if (null == findAppByAppName) {
//			OpenApplyEntity app = new OpenApplyEntity();
//			app.setApplyKey(entity.getApplyKey());
//			List<OpenApplyEntity> findAppsByEntity = findAppsByEntity(app);
//			if (findAppsByEntity.isEmpty()) {
//				isTrue = true;
//			} else {
//				result.setStatus(Status.FAILED);
//				result.setMessage("服务分类标识已存在!");
//			}
//		} else {
//			result.setStatus(Status.FAILED);
//			result.setMessage("服务分类名称已存在!");
//		}
//
//		if (isTrue) {
//			result.setStatus(Status.SUCCESS);
//		}
//		return result;
//	}
//
//	@Override
//	public CloudResult<JSONObject> appList(HttpServletRequest request, HttpServletResponse response) {
//
//		CloudResult<JSONObject> result = new CloudResult<JSONObject>();
//
//		try {
//			List<OpenApplyEntity> list = getAll();
//
//			JSONArray jsonArray = new JSONArray();
//			JSONObject data = new JSONObject();
//			for (int i = 0; i < list.size(); i++) {
//				JSONObject obj = new JSONObject();
//				OpenApplyEntity app = list.get(i);
//				obj.put("id", app.getId());
//				obj.put("appname", app.getApplyName());
//				jsonArray.put(obj);
//			}
//			data.put("items", jsonArray);
//			result.setSuccessData(data);
//			return result;
//		} catch (Exception e) {
//			log.info("error happens when get app list", e);
//			result.setErrorMessage("");
//			return result;
//		}
//	}
//
//	@Override
//	public CloudResult<Page<OpenApplyEntity>> groupList(String userId, int currentPage, int pageSize) {
//		CloudResult<Page<OpenApplyEntity>> result = new CloudResult<Page<OpenApplyEntity>>();
//		try {
//			OpenApplyEntity entity = new OpenApplyEntity();
//			entity.setUserId(userId);
//			Page<OpenApplyEntity> ds = findAppsByEntity(entity, currentPage, pageSize);
//			result.setSuccessData(ds);
//
//		} catch (Exception e) {
//			log.info("error happens when group list");
//			result.setErrorMessage("");
//		}
//
//		return result;
//	}
//
//	@Override
//	public CloudResult<String> submitVerify(String id) {
//		CloudResult<String> result = new CloudResult<String>();
//		try {
//			OpenApplyEntity app = openApplyDao.findById(id);
//			app.setCheckState(1);
//			modifyById(app);
//
//			result.setSuccessMessage("");
//		} catch (Exception e) {
//			log.error("error happens when submit verify", e);
//
//			result.setErrorMessage("");
//
//		}
//		return result;
//	}
//
//	@Override
//	public CloudResult<String> addGroup1(HttpSession session, OpenApplyEntity app) {
//		CloudResult<String> result = new CloudResult<String>();
//		try {
//
//			CloudResult<OpenApplyEntity> checkAppIsHave = checkAppIsHave(app);
//
//			if (Status.SUCCESS == checkAppIsHave.getStatus()) {
//				User user = (User) session.getAttribute(Constants.SES_LOGIN_USER);
//				String uuid = UUID.randomUUID().toString().replace("-", "");
//				app.setId(uuid);
//				app.setCreateDate(new Date());
//				app.setCheckState(2); // 后台系统添加服务分类默认审核通过
//				app.setUserId(user.getId());
//				app.setUserName(user.getUserName());
//				addApp(app);
//				result.setSuccessData(uuid);
//			} else {
//				// TODO
//				result.setErrorMessage("");
//			}
//		} catch (Exception e) {
//			log.info("error happens when execute add group", e);
//			result.setErrorMessage("");
//		}
//		return result;
//	}
//
//	@Override
//	public CloudResult<Page<OpenApplyEntity>> groupList1(String appName, String checkState, int currentPage, int pageSize) {
//		CloudResult<Page<OpenApplyEntity>> result = new CloudResult<Page<OpenApplyEntity>>();
//
//		try {
//			// Map<String,Object> condMap = new HashMap<String, Object>(2);
//			OpenApplyEntity appParam = new OpenApplyEntity();
//
//			if (StringUtils.isNotBlank(appName)) {
//				appParam.setApplyName(appName);
//			}
//
//			if (StringUtils.isNotBlank(checkState)) {
//				appParam.setCheckState(Integer.valueOf(checkState));
//			}
//
//			Page<OpenApplyEntity> ds = getAppListByDBWhere(appParam, currentPage, pageSize);
//
//			result.setSuccessData(ds);
//		} catch (Exception e) {
//			log.info("error happens when execute group list1", e);
//			result.setErrorMessage("");
//		}
//		return result;
//	}
//
//	@Override
//	public CloudResult<String> modifyGroup1(OpenApplyEntity app) {
//		CloudResult<String> result = new CloudResult<String>();
//		try {
//
//			OpenApplyEntity appAfter = openApplyDao.findById(app.getId());
//			if (null == appAfter) {
//				result.setErrorMessage("当前分组不存在", ErrorCodeNo.SYS006);
//			} else {
//				app.setNeqId(app.getId());// 查询非当前修改数据进行判断
//				List<OpenApplyEntity> findAppsByEntity = findAppsByEntity(app);
//				if (findAppsByEntity.isEmpty()) {
//					appAfter.setApplyName(app.getApplyName().trim());
//					appAfter.setApplyKey(app.getApplyKey().trim());
//					modifyById(appAfter);
//					result.setSuccessMessage("");
//				} else {
//					result.setErrorMessage("分组名称或分组key不可重复！", ErrorCodeNo.SYS010);
//				}
//			}
//		} catch (Exception e) {
//			log.info("error happens when execute modify app group ", e);
//			result.setErrorMessage("");
//		}
//		return result;
//	}

	@Override
	public Result<Page<OpenApplyEntity>> findOpenApplyList(QueryOpenApplyEntity entity) {
		
		Result<Page<OpenApplyEntity>> result = new Result<Page<OpenApplyEntity>>();
		int totalNum = mysqlOpenApplyDao.findListSize(entity);
		List<OpenApplyEntity> openList = mysqlOpenApplyDao.getPagedList(entity);
		Page<OpenApplyEntity> page = new Page<OpenApplyEntity>(entity.getCurrentPage(),
				totalNum, entity.getPageSize());
		page.setItems(openList);
		result.setSuccessData(page);
		return result;
	}

	@Override
	public Result<String> batchUpdate(String sourceConfig, List<String> ids, Integer checkState, String checkMem) {
		// TODO Auto-generated method stub
		Result<String> result = new Result<String>();
		/* 审核失败返回 */
		if (checkState == AuditConstants.OPEN_APPLY_CHECKED_FAILED) {
			// String message = openApplyDao.bathUpdateByid(ids, checkState,
			// checkMem);
			int uNum = mysqlOpenApplyDao.batchUpdateCheckState(ids, checkState, checkMem);
			log.info("bachUpdate message " + uNum + " count");
			result.setSuccessMessage("审核成功:" + uNum + "条");
			return result;
		}

		// List<OpenApplyEntity> list = openApplyDao.getListByids(ids);
		List<OpenApplyEntity> list = mysqlOpenApplyDao.getListByids(ids);
		SaveOrUpdateAppsInParameterEntity[] queryVO = new SaveOrUpdateAppsInParameterEntity[list.size()];
		for (int i = 0; i < list.size(); i++) {
			SaveOrUpdateAppsInParameterEntity sue = new SaveOrUpdateAppsInParameterEntity();
			sue.setAppName(list.get(i).getApplyName());
			sue.setAppDesc(list.get(i).getApplyDesc());
			sue.setAppCode(list.get(i).getApplyKey());
			sue.setAppType("1");
			sue.setOwner(list.get(i).getEnterpriseName());
			queryVO[i] = sue;
		}
		/* 调用接口推送信息 */
		log.info("saveOrUpdateApps to interface satar");
		InterfaMessageInfoString interfaMessageInfoJasonObjectResult = this
				.saveOrUpdateApps(sourceConfig, JSONArray.fromObject(queryVO).toString()).getData();
		log.info("saveOrUpdateApps to interface states " + interfaMessageInfoJasonObjectResult.getStatus() + "");
		/* 审核成功提交 */
		if (interfaMessageInfoJasonObjectResult.getStatus() == AuditConstants.INTERFACE_RETURNSATAS_SUCCESS) {
			// String message = openApplyDao.bathUpdateByid(ids, checkState,
			// checkMem);
			int uNum = mysqlOpenApplyDao.batchUpdateCheckState(ids, checkState, checkMem);
			log.info("bachUpdate message " + uNum + " count");
			result.setSuccessMessage("成功审核:" + uNum + "条");
		} else {
			result.setErrorMessage("审核失败", ErrorCodeNo.SYS001);
		}
		return result;
	}

	private Result<InterfaMessageInfoString> saveOrUpdateApps(String sourceConfig,String apps) {
		// TODO Auto-generated method stub
		Result<InterfaMessageInfoString> result = new Result<>();
		String url = propertiesUtil.getSourceConfigValue(sourceConfig,"saveOrUpdateApps");
		String apps$ = Pattern.quote("#{apps}");
		String replacedurl = url.replaceAll(apps$, apps);
		try {
			/* get请求方法 */
			InterfaMessageInfoString messageInfo = (InterfaMessageInfoString) HttpClientUtil
					.getUrlReturnObject(replacedurl, InterfaMessageInfoString.class, null);

			if (messageInfo.getStatus() == 200 || messageInfo.getStatus() == 110) {
				result.setSuccessData(messageInfo);
				return result;
			} else {
				log.error("saveOrUpdateApps data http getfaile return code :" + messageInfo.getMsg() + " ");
				result.setErrorCode(ErrorCodeNo.SYS006);
				result.setErrorMessage("请求失败");
				return result;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("saveOrUpdateApps http error " + e + "");
			result.setErrorCode(ErrorCodeNo.SYS001);
			result.setErrorMessage("系统错误,请求失败");
			return result;
		}
	}

}
