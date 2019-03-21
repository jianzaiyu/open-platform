package cn.ce.service.openapi.base.guide.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.common.page.Page;
import cn.ce.service.openapi.base.guide.entity.GuideEntity;
import cn.ce.service.openapi.base.guide.entity.QueryGuideEntity;

/**
 *
 * @Title: IManageGuideService.java
 * @Description
 * @version V1.0
 * @author yang.yu@300.cn
 * @date dat2017年10月12日 time下午7:18:34
 *
 **/
public interface IManageGuideService {

	public Result<Page<GuideEntity>> guideList(QueryGuideEntity guideEntity);

	public Result<GuideEntity> getByid(String id);

	Result<String> batchUpdateCheckState(@Param("guideIds") List<String> guideIds, @Param("checkState") Integer checkState, @Param("checkMem") String checkMem);


}
