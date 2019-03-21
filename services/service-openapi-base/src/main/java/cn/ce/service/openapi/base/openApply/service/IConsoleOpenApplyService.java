package cn.ce.service.openapi.base.openApply.service;

import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.openApply.entity.OpenApplyEntity;
import cn.ce.service.openapi.base.openApply.entity.QueryOpenApplyEntity;
import cn.ce.service.openapi.base.users.entity.User;

import java.util.List;

/***
 * 
 * 
 * @ClassName:  IConsoleOpenApplyService   
 * @Description:开放应用服务类   
 * @author: lida
 * @date:   2017年10月14日 下午2:49:30   
 * @Copyright: 2017 中企动力科技股份有限公司 © 1999-2017 300.cn All Rights Reserved
 *
 */
public interface IConsoleOpenApplyService {
	
	/***
	 * 
	 * @Title: addApply
	 * @Description: 添加开放应用(其中 applyKey 与 applyName 均不可重复)
	 * @param : @param session 需获取session中用户信息
	 * @param : @param apply 应用实体
	 * @param : @return
	 * @return: CloudResult<?>
	 * @throws
	 */
	public Result<?> addApply(User user, OpenApplyEntity apply);
	
	/***
	 * 
	 * @Title: addApply
	 * @Description: 修改开放应用(其中 applyKey 与 applyName 均不可重复)
	 * @param : @param apply 应用实体
	 * @param : @return
	 * @return: CloudResult<?>
	 * @throws
	 */
	public Result<?> modifyApply(OpenApplyEntity app);
	
	/***
	 * 
	 * @Title: deleteApplyById
	 * @Description: 删除开放应用 暂未实现
	 * @param : @param applyId 应用id
	 * @param : @return
	 * @return: CloudResult<?>
	 * @throws
	 */
	public Result<?> deleteApplyById(String applyId);

	/***
	 * 
	 * @Title: applyList
	 * @Description: 查询开放应用列表
	 * @param : @param entity 查询条件 (部分支持模糊查询)
	 * @param : @return
	 * @return: CloudResult<?>
	 * @throws
	 */
	public Result<?> applyList(QueryOpenApplyEntity entity);
	
	/***
	 * 
	 * @Title: applyList
	 * @Description: 查询开放应用列表(分页)
	 * @param : @param entity 查询条件 (部分支持模糊查询)
	 * @param : @return
	 * @return: CloudResult<?>
	 * @throws
	 */
//	public CloudResult<?> applyList(OpenApplyEntity entity,Page<OpenApplyEntity> page);

	/***
	 * 
	 * @Title: submitVerify
	 * @Description: 审核开放应用
	 * @param : @param id
	 * @param : @param checkState 0:初始，1:提交审核，2:通过，3:未通过
	 * @param : @return
	 * @param id
     * @return: CloudResult<?>
	 * @throws
	 */
	public Result<?> submitVerify(List<String> ids, Integer checkState);
	
	public Result<?> getApplyById(String id);

	public Result<?> checkApplyName(String applyName);

	public Result<?> checkApplyKey(String applyKey);

    Result batchDeleteOpenApply();

	Result batchInsertOpenApply();

	Result getOpenApplyList(String owner, String name, int currentPage, int pageSize);

//	public CloudResult<?> migraOpenApply();
}
