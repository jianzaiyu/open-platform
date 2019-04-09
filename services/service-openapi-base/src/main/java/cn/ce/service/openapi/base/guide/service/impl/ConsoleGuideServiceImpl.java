package cn.ce.service.openapi.base.guide.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.ce.service.openapi.base.guide.dao.IMysqlGuideDao;
import cn.ce.service.openapi.base.guide.entity.GuideEntity;
import cn.ce.service.openapi.base.guide.entity.QueryGuideEntity;
import cn.ce.service.openapi.base.guide.service.IConsoleGuideService;
import cn.ce.service.openapi.base.users.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.ce.service.openapi.base.common.AuditConstants;
import cn.ce.service.openapi.base.common.ErrorCodeNo;
import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.common.page.Page;
import cn.ce.service.openapi.base.util.RandomUtil;

/**
 *
 * @Title: ConsoleGuideServiceImpl.java
 * @Description
 * @version V1.0
 * @author yang.yu@300.cn
 * @date dat2017年10月12日 time下午7:23:03
 *
 **/
@Slf4j
@Service("consoleGuideService")
@Transactional(propagation=Propagation.REQUIRED)
public class ConsoleGuideServiceImpl implements IConsoleGuideService {
	/** 日志对象 */
//	@Resource
//	private IGuideDao guideDaoImpl;
	@Resource
	private IMysqlGuideDao mysqlGuideDao;

	@Override
	public Result<String> add(User user, GuideEntity g) {
		// TODO Auto-generated method stub
		Result<String> result = new Result<String>();

		// List<GuideEntity> list =
		// guideDaoImpl.getListByNameAndApply(g.getGuideName(), g.getApplyId());
		int guideNum = mysqlGuideDao.checkGuideName(g.getGuideName(), g.getApplyId());

		// if (list != null && list.size() > 0) {
		// result.setErrorCode(ErrorCodeNo.SYS009);
		// result.setErrorMessage("指南已存在");
		// return result;
		// }
		if (guideNum > 0) {
			result.setErrorMessage("指南已存在", ErrorCodeNo.SYS009);
			return result;
		}
		log.info("add guide message");
		g.setCreatUserName(user.getUsername());
		g.setCreatTime(new Date());
		if (g.getCheckState() == null) {
			g.setCheckState(AuditConstants.GUIDE_UNCHECKED);
		}
		// guideDaoImpl.saveOrUpdateGuide(g);
		g.setId(RandomUtil.random32UUID());
		mysqlGuideDao.save(g);
		result.setSuccessMessage("添加成功");
		log.info("add guide message success");
		return result;

	}

	@Override
	public Result<String> update(GuideEntity g) {
		// TODO Auto-generated method stub
		Result<String> result = new Result<String>();

		log.info("update guide message");

		// GuideEntity ge = guideDaoImpl.getById(g.getId());
		GuideEntity ge = mysqlGuideDao.findById(g.getId());
		if (null == ge) {
			result.setErrorMessage("不存在", ErrorCodeNo.SYS015);
			return result;
		}

		// List<GuideEntity> list =
		// guideDaoImpl.findListByNameNeId(g.getGuideName(), g.getId());

		// if (list != null && list.size() > 0) {
		// result.setMessage("指南名称重复!");
		// result.setErrorCode(ErrorCodeNo.SYS010);
		// return result;
		// }
		if (mysqlGuideDao.checkGuideName(g.getGuideName(), g.getId()) > 0) {
			result.setErrorMessage("指南名称重复", ErrorCodeNo.SYS010);
		}

		if (AuditConstants.GUIDE_SUCCESS == ge.getCheckState()) {
			result.setErrorMessage("指南已审核!", ErrorCodeNo.SYS012);
			return result;
		}

		// guideDaoImpl.saveOrUpdateGuide(g);
		//g.setCheckState(AuditConstants.GUIDE_UNCHECKED);
		mysqlGuideDao.updateGuide(g);
		result.setSuccessMessage("修改成功");
		log.info("update guide message success");
		return result;

	}

	@Override
	public Result<Page<GuideEntity>> guideList(QueryGuideEntity entity) {
		// TODO Auto-generated method stub
		Result<Page<GuideEntity>> result = new Result<Page<GuideEntity>>();

		int guideNum = mysqlGuideDao.findTotalNum(entity);
		List<GuideEntity> guideList = mysqlGuideDao.getList(entity);
		Page<GuideEntity> page = new Page<GuideEntity>(
				entity.getCurrentPage(),guideNum,entity.getPageSize());
		page.setItems(guideList);
		result.setSuccessData(page);
		return result;
	}

	@Override
	public Result<String> delete(String id) {
		// TODO Auto-generated method stub
		Result<String> result = new Result<String>();
		log.info("delete guide message");

		// GuideEntity ge = guideDaoImpl.getById(id);
		GuideEntity ge = mysqlGuideDao.findById(id);

		if (null == ge) {
			result.setErrorMessage("指南不存在!", ErrorCodeNo.SYS015);
			return result;
		}

		if (AuditConstants.GUIDE_SUCCESS == ge.getCheckState()) {
			result.setErrorMessage("指南已审核,无法删除!");
			return result;
		} else if (AuditConstants.OPEN_APPLY_CHECKED_COMMITED == ge.getCheckState()) {
			result.setErrorMessage("指南审核中,无法删除!");
			return result;
		}

		// guideDaoImpl.deleteByid(id);
		mysqlGuideDao.deleteById(id);

		result.setSuccessMessage("删除成功");
		log.info("delete guide message success");
		return result;

	}

	@Override
	public Result<GuideEntity> getByid(String id) {
		
		Result<GuideEntity> result = new Result<GuideEntity>();

//		GuideEntity byId = guideDaoImpl.getById(id);
		GuideEntity guide = mysqlGuideDao.findById(id);

		if (guide == null) {
			result.setErrorMessage("指南不存在!",ErrorCodeNo.SYS015);
			return result;
		}
		result.setSuccessData(guide);
		return result;
	}

	@Override
	public Result<String> submitVerify(List<String> ids) {
		Result<String> result = new Result<>();

//			String num = String
//					.valueOf(guideDaoImpl.bachUpdateGuide(asList, AuditConstants.DIY_APPLY_CHECKED_COMMITED, null));
		int num = mysqlGuideDao.bathUpdateCheckState(ids,AuditConstants.DIY_APPLY_CHECKED_COMMITED,null);
		log.info("bachUpdate guide message " + num + " count");
		result.setSuccessMessage("提交审核:" + num + "条");
		return result;

	}

//	@Override
//	public CloudResult<String> migraGuide() {
//		
//		List<GuideEntity> list = guideDaoImpl.findAll();
//		mysqlGuideDao.deleteAll();
//		int i = 0;
//		for (GuideEntity guideEntity : list) {
//			i+= mysqlGuideDao.save(guideEntity);
//		}
//		CloudResult<String> result = new CloudResult<String>();
//		result.setSuccessMessage("一共迁移了"+list.size()+"条数据，成功了"+i+"条");
//		return result;
//	}

}
