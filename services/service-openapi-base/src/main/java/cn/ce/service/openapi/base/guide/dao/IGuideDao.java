package cn.ce.service.openapi.base.guide.dao;//package cn.ce.service.openapi.base.guide.dao;
//
//import java.util.List;
//
//import org.springframework.data.mongodb.core.query.Query;
//
//import cn.ce.service.openapi.base.common.page.Page;
//import cn.ce.service.openapi.base.guide.entity.GuideEntity;
//
///**
// *
// * @Title: IGuideDao.java
// * @Description
// * @version V1.0
// * @author yang.yu@300.cn
// * @date dat2017年10月12日 time下午5:49:31
// *
// **/
//public interface IGuideDao {
//
//	public void saveOrUpdateGuide(GuideEntity g);
//
//	@Deprecated
//	public Page<GuideEntity> listByPage(Page<GuideEntity> page, Query query);
//
//	@Deprecated
//	public List<GuideEntity> list(Query query);
//	
//	public List<GuideEntity> getListByNameAndApply(String guideName, String applyId);
//
//	public GuideEntity getById(String id);
//
//	public void deleteByid(String id);
//
//	String bachUpdateGuide(List<String> ids, Integer state, String checkMem);
//
//	public Page<GuideEntity> listPage(String guideName, String creatUserName, String applyId, Integer checkState,
//			int currentPage, int pageSize);
//
//	public List<GuideEntity> findListByNameNeId(String guideName, String id);
//
//	public Page<GuideEntity> listPage2(GuideEntity entity, int currentPage, int pageSize);
//
//	public List<GuideEntity> findAll();
//
//
//}
