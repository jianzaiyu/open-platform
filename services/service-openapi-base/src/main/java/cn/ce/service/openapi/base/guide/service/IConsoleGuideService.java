package cn.ce.service.openapi.base.guide.service;

import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.common.page.Page;
import cn.ce.service.openapi.base.guide.entity.GuideEntity;
import cn.ce.service.openapi.base.guide.entity.QueryGuideEntity;
import cn.ce.service.openapi.base.users.entity.User;

import java.util.List;

/**
 *
 * @Title: IConsoleGuideService.java
 * @Description
 * @version V1.0
 * @author yang.yu@300.cn
 * @date dat2017年10月12日 time下午7:12:15
 *
 **/
public interface IConsoleGuideService {

	public Result<String> add(User user, GuideEntity g);

	public Result<String> update(GuideEntity g);
	
	public Result<String> delete(String id);
	
	public Result<GuideEntity> getByid(String id);

	public Result<Page<GuideEntity>> guideList(QueryGuideEntity entity);

	Result<String> submitVerify(List<String> ids);

//	public CloudResult<String> migraGuide();


}
