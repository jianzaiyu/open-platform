package cn.ce.service.openapi.base.openApply.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import cn.ce.service.openapi.base.diyApply.entity.appsEntity.AppList;
import cn.ce.service.openapi.base.diyApply.entity.appsEntity.Apps;
import cn.ce.service.openapi.base.diyApply.entity.appsEntity.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;
import cn.ce.service.openapi.base.common.AuditConstants;
import cn.ce.service.openapi.base.common.ErrorCodeNo;
import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.common.Status;
import cn.ce.service.openapi.base.common.page.Page;
import cn.ce.service.openapi.base.diyApply.service.IPlublicDiyApplyService;
import cn.ce.service.openapi.base.openApply.dao.IMysqlOpenApplyDao;
import cn.ce.service.openapi.base.openApply.entity.OpenApplyEntity;
import cn.ce.service.openapi.base.openApply.entity.QueryOpenApplyEntity;
import cn.ce.service.openapi.base.openApply.service.IConsoleOpenApplyService;
import cn.ce.service.openapi.base.users.entity.User;
import cn.ce.service.openapi.base.util.RandomUtil;

/***
 * 
 * 
 * @ClassName: ConsoleOpenApplyServiceImpl
 * @Description:开放应用控制service实现类
 * @author: lida
 * @date: 2017年10月14日 下午2:48:59
 * @Copyright: 2017 中企动力科技股份有限公司 © 1999-2017 300.cn All Rights Reserved
 *
 */
@Service(value = "colsoleOpenApplyService")
@Transactional(propagation=Propagation.REQUIRED)
public class ConsoleOpenApplyServiceImpl implements IConsoleOpenApplyService {

	private static Logger _LOGGER = LoggerFactory.getLogger(ConsoleOpenApplyServiceImpl.class);

	@Resource
	private IMysqlOpenApplyDao mysqlOpenApplyDao;	
	
	@Resource
	private IPlublicDiyApplyService plublicDiyApplyService;

	/**
	 * @Title: addApply
	 * @Description: 废弃mongoWhereEntity改为mysql
	 * @author: makangwei 
	 * @date:   2018年1月26日 上午11:34:49 
	 */
	@Override
	public Result<?> addApply(User user, OpenApplyEntity apply) {

		_LOGGER.info("addConsoleApply begin apply:" + JSON.toJSONString(apply));

		Result<String> result = new Result<String>();


		int cNum = mysqlOpenApplyDao.checkApplyName(apply.getApplyName());
		if (cNum > 0) {
			result.setErrorMessage("应用名称已存在!", ErrorCodeNo.SYS009);
			return result;
		}

		int kNum = mysqlOpenApplyDao.checkApplyKey(apply.getApplyKey());
		if (kNum > 0) {
			result.setErrorMessage("应用key已存在!", ErrorCodeNo.SYS009);
			return result;
		}

		// TODO 管理员添加直接是审核通过的
		if(null == apply.getCheckState()){
			apply.setCheckState(AuditConstants.OPEN_APPLY_UNCHECKED);
		}else if (AuditConstants.OPEN_APPLY_UNCHECKED != apply.getCheckState()
				&& AuditConstants.OPEN_APPLY_CHECKED_COMMITED != apply.getCheckState()) {
			result.setErrorMessage("当前审核状态不可用", ErrorCodeNo.SYS012);
			return result;
		}

		apply.setCreateDate(new Date());
		apply.setUserId(user.getId());
		apply.setUserName(user.getUserName());
		apply.setEnterpriseName(user.getEnterpriseName());

		// appKey不能以/开头和结尾
		if (apply.getApplyKey().startsWith("/") || apply.getApplyKey().endsWith("/")) {
			result.setErrorMessage("appKey不能以/开头和/结尾");
			return result;
		}
		apply.setId(RandomUtil.random32UUID());
		mysqlOpenApplyDao.save(apply);

		_LOGGER.info("addApply end general applyId:" + apply.getId());

		result.setSuccessMessage("保存成功!");

		return result;
	}

	/**
	 * 
	 * @Title: modifyApply
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author: makangwei 
	 * @date:   2018年1月26日 上午11:35:56 
	 */
	@Override
	public Result<?> modifyApply(OpenApplyEntity openApply) {

		_LOGGER.info("modifyConsoleOpenApply begin apply:" + JSON.toJSONString(openApply));

		Result<String> result = new Result<String>();

		OpenApplyEntity apply = mysqlOpenApplyDao.findById(openApply.getId());

		if (null == apply) {

			result.setErrorMessage("当前开放应用不存在", ErrorCodeNo.SYS006);
		} else if (null == openApply.getCheckState()) {
			
			openApply.setCheckState(AuditConstants.OPEN_APPLY_UNCHECKED);
		} else if (AuditConstants.OPEN_APPLY_CHECKED_SUCCESS == apply.getCheckState()) {

			result.setErrorMessage("当前状态不支持修改", ErrorCodeNo.SYS023);
			return result;
		}

		int cNum = mysqlOpenApplyDao.checkApplyNameById(openApply.getApplyName(), openApply.getId());
		if (cNum > 0) {
			result.setErrorMessage("应用名称已存在!", ErrorCodeNo.SYS009);
			return result;
		}

		int kNum = mysqlOpenApplyDao.checkApplyKeyById(openApply.getApplyKey(), openApply.getId());
		if (kNum > 0) {
			result.setErrorMessage("应用key已存在!", ErrorCodeNo.SYS009);
			return result;
		}

		int uNum = mysqlOpenApplyDao.update(openApply);

		_LOGGER.info("modifyConsoleOpenApply end success!" + " change num is:" + uNum);

		result.setSuccessMessage("保存成功!");

		return result;
	}

	@Override
	public Result<?> deleteApplyById(String appId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<Page<OpenApplyEntity>> applyList(QueryOpenApplyEntity entity) {

		Result<Page<OpenApplyEntity>> result = new Result<Page<OpenApplyEntity>>();
		int uNum = mysqlOpenApplyDao.findListSize(entity);
		List<OpenApplyEntity> openList = mysqlOpenApplyDao.getPagedList(entity);
		Page<OpenApplyEntity> page = new Page<OpenApplyEntity>(entity.getCurrentPage(),uNum,entity.getPageSize());
		page.setItems(openList);
		result.setSuccessData(page);
		return result;
	}

	@Override
	public Result<?> submitVerify(List<String> ids, Integer checkState) {

		_LOGGER.info("consoleOpenApplySubmitVerify begin ids:" + ids + ",checkState:" + checkState);

		Result<Page<OpenApplyEntity>> result = new Result<>();

		int num = mysqlOpenApplyDao.batchUpdateCheckState(ids, checkState, null);

		_LOGGER.info("consoleOpenApplySubmitVerify success! batch update num is "+num);

		result.setSuccessMessage("保存成功!");
		return result;
	}

	@Override
	public Result<?> getApplyById(String id) {
		Result<OpenApplyEntity> result = new Result<>();
		//OpenApplyEntity findOpenApplyById = newOpenApplyDao.findOpenApplyById(id);
		OpenApplyEntity findOpenApplyById = mysqlOpenApplyDao.findById(id);
		if (null == findOpenApplyById) {
			result.setErrorMessage("开放应用不存在!",ErrorCodeNo.SYS015);
			return result;
		}
		result.setSuccessData(findOpenApplyById);
		return result;
	}



	@Override
	public Result<?> checkApplyName(String applyName) {

		int aNum = mysqlOpenApplyDao.checkApplyName(applyName);
		if(aNum > 0){
			return Result.errorResult("当前应用名称已经存在！", ErrorCodeNo.SYS009, null, Status.FAILED);
		}
		
		return Result.errorResult("可以使用", ErrorCodeNo.SYS000, null, Status.SUCCESS);
	}

	@Override
	public Result<?> checkApplyKey(String applyKey) {

		int aNum = mysqlOpenApplyDao.checkApplyKey(applyKey);
		if(aNum > 0){
			return Result.errorResult("当前应用key已经存在！", ErrorCodeNo.SYS009, null, Status.FAILED);
		}
		
		return Result.errorResult("当前key可以使用", ErrorCodeNo.SYS000, null, Status.SUCCESS);
	}

	@Override
	public Result batchDeleteOpenApply() {
		int temp = mysqlOpenApplyDao.deleteAllSuccess();
		return Result.successResult("success",temp);
	}

	@Override
	public Result batchInsertOpenApply() {
		Result<Apps> appsResult = plublicDiyApplyService.findPagedApps(null,null,null,0,Integer.MAX_VALUE);
		Data data = appsResult.getData().getData();
		List<AppList> list = data.getList();
		List<OpenApplyEntity> openApplyEntityList = new ArrayList<>();
		for (AppList app: list) {
			openApplyEntityList.add(
					new OpenApplyEntity(
							RandomUtil.random32UUID(),
							app.getAppId(),
							app.getAppCode(),
							app.getAppIcon(),
							app.getAppName(),
							app.getAppDesc(),
							2,
							StringUtils.isBlank(app.getAppCreateTime()) ? new Date() : new Date(Long.parseLong(app.getAppCreateTime()))
					)
			);
		}
		int temp = mysqlOpenApplyDao.batchInsert(openApplyEntityList);
		return Result.successResult("",temp);
	}

	@Override
	public Result getOpenApplyList(String owner, String name, int currentPage, int pageSize) {
		//分页
		QueryOpenApplyEntity queryOpenApplyEntity = new QueryOpenApplyEntity();
		queryOpenApplyEntity.setApplyName(name);
		queryOpenApplyEntity.setCurrentPage(currentPage);
		queryOpenApplyEntity.setPageSize(pageSize);
		queryOpenApplyEntity.setCheckState(2);

		int totalSize = mysqlOpenApplyDao.findSuccessSize(queryOpenApplyEntity);
		List<OpenApplyEntity> list = mysqlOpenApplyDao.getSuccessedList(queryOpenApplyEntity);
		Page<OpenApplyEntity> page = new Page(currentPage,totalSize,pageSize,list);
		return Result.successResult("",page);
	}

}
