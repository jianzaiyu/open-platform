package cn.ce.service.openapi.base.gateway.service.impl;

import java.util.List;

import javax.annotation.Resource;

import cn.ce.service.openapi.base.common.Constants;
import cn.ce.service.openapi.base.common.ErrorCodeNo;
import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.common.gateway.ApiCallUtils;
import cn.ce.service.openapi.base.common.page.Page;
import cn.ce.service.openapi.base.gateway.dao.IMysqlGwColonyDao;
import cn.ce.service.openapi.base.gateway.dao.IMysqlGwNodeDao;
import cn.ce.service.openapi.base.gateway.entity.GatewayColonyEntity;
import cn.ce.service.openapi.base.gateway.entity.GatewayNodeEntity;
import cn.ce.service.openapi.base.gateway.entity.QueryGwColonyEntity;
import cn.ce.service.openapi.base.gateway.entity.QueryGwNodeEntity;
import cn.ce.service.openapi.base.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.ce.service.openapi.base.gateway.service.IGatewayManageService;

/**
 *
 * @author makangwei 2017-8-4
 */
@Service(value = "gatewayManageService")
@Transactional(propagation=Propagation.REQUIRED)
public class GatewayManageService implements IGatewayManageService {

//	 @Resource
//	 private IGatewayManageDao gatewayManageDao;
//	 @Resource
//	 private IGatewayNodeManageDao gatewayNodeManageDao;
//	 @Resource
//	 private IGatewayColonyManageDao gatewayColonyManageDao;
	@Resource
	private IMysqlGwColonyDao gwColonyDao;
	@Resource
	private IMysqlGwNodeDao gwNodeDao;


	/**
	 * 添加网关集群
	 * 
	 * @param colEntity
	 * @return
	 */
	public Result<GatewayColonyEntity> addGatewayCol(GatewayColonyEntity colEntity) {

		Result<GatewayColonyEntity> result = new Result<GatewayColonyEntity>();

		// 判断参数是否可用
		if (StringUtils.isBlank(colEntity.getColUrl())) {
			result.setErrorMessage("代理域名不能为空！", ErrorCodeNo.SYS005);
			return result;
		}
		if (StringUtils.isBlank(colEntity.getColName())) {
			result.setMessage("集群名称不能为空");
			return result;
		}
		if (colEntity.getColStatus() == null) {
			colEntity.setColStatus(0); // 默认集群是关闭的
		}

		// List<GatewayColonyEntity> urlList =
		// gatewayManageDao.findByField("colUrl", colEntity.getColUrl(),
		// GatewayColonyEntity.class);
		// if(urlList.size()>0){
		// result.setMessage("当前集群url已经存在，无需重复添加");
		// return result;
		// }
		int cNum = gwColonyDao.checkByUrl(colEntity.getColUrl());
		if (cNum > 0) {
			result.setErrorMessage("当前集群url已经存在，无需重复添加", ErrorCodeNo.SYS009);
			return result;
		}

		// 对集群的测试
		boolean reloadBool = ApiCallUtils.getGwReload(colEntity.getColUrl() + Constants.NEWWORK_RELOAD_GROUP);

		if (!reloadBool) {
			result.setMessage("该集群无法在网络中查找到，请填写正确的ip和端口号码");
			return result;
		}

		colEntity.setColId(RandomUtil.random32UUID());
		int iNum = gwColonyDao.addGatewayCol(colEntity);

		if (iNum > 0) {
			result.setSuccessData(colEntity);
		}

		return result;
	}

	/**
	 * 
	 * @Description : 根据id修改集群信息
	 * @Author : makangwei
	 * @Date : 2017-8-7
	 * @param colEntity
	 * @return
	 */
	public Result<GatewayColonyEntity> modifyGatewayCol(GatewayColonyEntity colEntity) {

		Result<GatewayColonyEntity> result = new Result<GatewayColonyEntity>();

		// 查询当前网关id是否存在
		GatewayColonyEntity isExits = gwColonyDao.findById(colEntity.getColId());
		if (null == isExits) {
			result.setErrorMessage("当前集群不存在，请传入正确的集群id", ErrorCodeNo.SYS006);
			return result;
		}
		// 判断参数是否可用
		if (StringUtils.isBlank(colEntity.getColId())) {
			result.setErrorMessage("当前集群id不能为空", ErrorCodeNo.SYS005);
			return result;
		}
		if (StringUtils.isBlank(colEntity.getColUrl())) {
			result.setErrorMessage("代理域名不能为空", ErrorCodeNo.SYS005);
			return result;
		}
		if (StringUtils.isBlank(colEntity.getColName())) {
			result.setErrorMessage("集群名称不能为空", ErrorCodeNo.SYS005);
			return result;
		}
		if (null == colEntity.getColStatus()) {
			colEntity.setColStatus(0); // 默认集群是关闭的
		}
		int cNums = gwColonyDao.checkByUrlExp(colEntity.getColUrl(), colEntity.getColId());
		if (cNums > 0) {
			result.setMessage("当前集群url已经存在，无需重复添加");
			return result;
		}

		// 对集群的测试
		boolean reloadBool = ApiCallUtils.getGwReload(colEntity.getColUrl() + Constants.NEWWORK_RELOAD_GROUP);

		if (!reloadBool) {
			result.setErrorMessage("该集群无法在网络中查找到，请填写正确的ip和端口号码", ErrorCodeNo.SYS027);
			return result;
		}

		// gatewayManageDao.updateById(colEntity.getColId(), colEntity );
		gwColonyDao.update(colEntity);
		result.setSuccessData(colEntity);
		return result;
	}

	/**
	 * 查询网关集群
	 * 
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public Result<Page<GatewayColonyEntity>> getAllGatewayCol(QueryGwColonyEntity queryEntity) {

		Result<Page<GatewayColonyEntity>> result = new Result<Page<GatewayColonyEntity>>();

		// Page<GatewayColonyEntity> page =
		// gwColonyDao.getAllGatewayCol(currentPage,pageSize);
		int cNum = gwColonyDao.findListSize(queryEntity);
		List<GatewayColonyEntity> list = gwColonyDao.getPagedList(queryEntity);

		if (list != null) {
			Page<GatewayColonyEntity> page = new Page<GatewayColonyEntity>(queryEntity.getCurrentPage(), cNum,
					queryEntity.getPageSize(), list);
			result.setSuccessData(page);
		} else {
			result.setErrorMessage("查不到数据");
		}

		return result;
	}

	/**
	 * 删除集群
	 * 
	 * @param colId
	 * @return
	 */
	public Result<String> deleteGatewayColonyById(String colId) {

		Result<String> result = new Result<String>();

		// boolean bool= gatewayManageDao.deleteGatewayColonyById(colId);
		int dNum = gwColonyDao.deleteById(colId);
		if (dNum > 0) {
			result.setSuccessMessage("删除成功");
			return result;
		} else {
			result.setErrorMessage("删除失败，查看当前集群是否存在");
			return result;
		}

	}

	/**
	 * 添加集群节点
	 * 
	 * @param nodeEntity
	 * @return
	 */
	public Result<String> addGatewayNode(GatewayNodeEntity nodeEntity) {

		Result<String> result = new Result<String>();

		if (StringUtils.isBlank(nodeEntity.getNodeUrl())) {
			result.setErrorMessage("请求url不能为空", ErrorCodeNo.SYS005);
			return result;
		}
		if (StringUtils.isBlank(nodeEntity.getNodeName())) {
			result.setErrorMessage("请求节点名称不能为空", ErrorCodeNo.SYS005);
			return result;
		}
		if (StringUtils.isBlank(nodeEntity.getColId())) {
			result.setErrorMessage("所属集群id不能为空", ErrorCodeNo.SYS008);
			return result;
		}

		// GatewayColonyEntity gatewayColonyEntity =
		// gatewayColonyManageDao.findById(nodeEntity.getColId());
		GatewayColonyEntity gatewayColonyEntity = gwColonyDao.findById(nodeEntity.getColId());

		if (gatewayColonyEntity == null || gatewayColonyEntity.getColDesc() == null) {
			result.setErrorMessage("当前集群不存在", ErrorCodeNo.SYS006);
			return result;
		}

		// List<GatewayNodeEntity> urlList =
		// gatewayNodeManageDao.findByField("nodeUrl", nodeEntity.getNodeUrl(),
		// GatewayNodeEntity.class);
		int uNum = gwNodeDao.checkByUrl(nodeEntity.getNodeUrl());
		if (uNum > 0) {
			result.setErrorMessage("当前节点url已经存在，无需重复添加", ErrorCodeNo.SYS009);
			return result;
		}

		boolean reloadBool = ApiCallUtils.getGwReload(nodeEntity.getNodeUrl() + Constants.NEWWORK_RELOAD_GROUP);

		if (!reloadBool) {
			result.setErrorMessage("该节点无法在网络中查找到，请填写正确的ip和端口号码", ErrorCodeNo.SYS027);
			return result;
		}

		// boolean bool = gatewayNodeManageDao.addGatewayNode(nodeEntity);
		nodeEntity.setNodeId(RandomUtil.random32UUID());
		gwNodeDao.addGatewayNode(nodeEntity);
		result.setSuccessMessage("添加成功");
		return result;
	}

	public Result<Page<GatewayNodeEntity>> getAllGatewayNode(QueryGwNodeEntity queryEntity) {

		Result<Page<GatewayNodeEntity>> result = new Result<Page<GatewayNodeEntity>>();

		// Page<GatewayNodeEntity> page =
		// gatewayNodeManageDao.getAllGatewayNode(currentPage,pageSize,colId);
		int totalNum = gwNodeDao.findListSize(queryEntity);
		List<GatewayNodeEntity> list = gwNodeDao.getPagedList(queryEntity);

		if (null != list) {
			Page<GatewayNodeEntity> page = new Page<GatewayNodeEntity>(queryEntity.getCurrentPage(), totalNum,
					queryEntity.getPageSize(), list);
			result.setSuccessData(page);
		} else {
			result.setErrorMessage("查不到数据");
		}
		return result;
	}

	public Result<String> deleteGatewayNodeById(String nodeId) {
		Result<String> result = new Result<String>();

		// boolean bool= gatewayNodeManageDao.deleteGatewayNodeById(nodeId);
		int dNum = gwNodeDao.deleteById(nodeId);

		if (dNum > 0) {
			result.setSuccessMessage("删除成功");
		} else {
			result.setErrorMessage("删除失败，查看当前节点是否存在");
		}
		return result;
	}

	public Result<String> modifyGatewayNodeById(GatewayNodeEntity nodeEntity) {

		Result<String> result = new Result<String>();

		// List<GatewayNodeEntity> nodeEntitys =
		// gatewayNodeManageDao.findByField("_id", nodeEntity.getNodeId(),
		// GatewayNodeEntity.class);
		GatewayNodeEntity getEntity = gwNodeDao.findById(nodeEntity.getNodeId());

		if (null == getEntity) {
			result.setErrorMessage("当前节点Id不存在", ErrorCodeNo.SYS006);
			return result;
		}

		// GatewayColonyEntity gatewayColonyEntity =
		// gatewayManageDao.findById(nodeEntity.getColId());
		GatewayColonyEntity gatewayColonyEntity = gwColonyDao.findById(nodeEntity.getColId());

		if (null == gatewayColonyEntity) {
			result.setErrorMessage("当前集群不存在", ErrorCodeNo.SYS006);
			return result;
		}

		// List<GatewayNodeEntity> urlList =
		// gatewayNodeManageDao.findByField("nodeUrl", nodeEntity.getNodeUrl(),
		// GatewayNodeEntity.class);
		// List<GatewayNodeEntity> urlList =
		// gatewayNodeManageDao.checkNodeUrl(nodeEntity.getNodeUrl(),
		// nodeEntity.getNodeId());
		int nNums = gwNodeDao.checkByUrlExp(nodeEntity.getNodeUrl(), nodeEntity.getNodeId());
		if (nNums > 0) {
			result.setMessage("当前节点url已经存在，无需重复添加");
			return result;
		}

		boolean reloadBool = ApiCallUtils.getGwReload(nodeEntity.getNodeUrl() + Constants.NETWORK_RELOAD_PATH);

		if (!reloadBool) {
			result.setMessage("该节点无法在网络中查找到，请填写正确的ip和端口号");
			return result;
		}

		// gatewayNodeManageDao.updateById(nodeEntity.getNodeId(), nodeEntity);
		gwNodeDao.update(nodeEntity);
		result.setSuccessMessage("修该成功");
		return result;
	}

//	@Override
//	public CloudResult<?> migraGateway() {
//		
//		List<GatewayColonyEntity> colList = gatewayColonyManageDao.findAll();
//		gwColonyDao.deleteAll();
//		gwNodeDao.deleteAll();
//		for (GatewayColonyEntity colEntity : colList) {
//			
//			List<GatewayNodeEntity> nodeList = gatewayNodeManageDao.getAll(colEntity.getColId());
//			
//			colEntity.setColId(RandomUtil.random32UUID());
//			colEntity.setColStatus(0);
//			gwColonyDao.addGatewayCol(colEntity);
//			for (GatewayNodeEntity nodeEntity : nodeList) {
//				nodeEntity.setColId(colEntity.getColId());
//				nodeEntity.setNodeId(RandomUtil.random32UUID());
//				nodeEntity.setNodeStatus(0);
//				gwNodeDao.addGatewayNode(nodeEntity);
//			}
//		}
//		return new CloudResult<String>("",ErrorCodeNo.SYS000,null,Status.SUCCESS);
//	}

}
