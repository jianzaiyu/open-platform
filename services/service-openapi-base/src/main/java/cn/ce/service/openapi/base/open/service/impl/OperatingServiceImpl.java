package cn.ce.service.openapi.base.open.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import cn.ce.service.openapi.base.apis.dao.IMysqlApiDao;
import cn.ce.service.openapi.base.apis.entity.NewApiEntity;
import cn.ce.service.openapi.base.diyApply.entity.DiyApplyEntity;
import cn.ce.service.openapi.base.open.entity.BiDiyApply;
import cn.ce.service.openapi.base.open.service.IOperatingService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.ce.service.openapi.base.common.ErrorCodeNo;
import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.common.Status;
import cn.ce.service.openapi.base.diyApply.dao.IMysqlDiyApplyDao;
import cn.ce.service.openapi.base.open.entity.BiBoundEntity2;
import cn.ce.service.openapi.base.open.entity.BiOpenApply;
import cn.ce.service.openapi.base.open.entity.BiPageEntity;
import cn.ce.service.openapi.base.open.entity.BiPageEntity2;
import cn.ce.service.openapi.base.util.PropertiesUtil;
import cn.ce.service.openapi.base.util.http.HttpResult;
import cn.ce.service.openapi.base.util.http.HttpUtil;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2018年5月28日
*/
@Service(value="operatingService")
public class OperatingServiceImpl implements IOperatingService {

	private static final String ORDER = "order";
	private static final String CURRENT_PAGE = "pageNumber";
	private static final String PAGE_SIZE = "itemPerPage";
	private static final String CLIENT_ID = "appKey";
	private static final String OPEN_APPLY_ID = "appCode";
	private static final String API_ID = "apiID";
	private static final String RESULT_LIST = "resultList";
	private static final String COUNT = "count";
	private static final String CURRENT_PAGE1 = "currentPage";
	private static final String PAGE_SIZE1 = "pageSize";
	private static final String TOTAL_PAGE1 = "totalPage";
	private static final String TOTAL_NUMBER1 = "totalNumber";
	private static final int TIME_OUT = 5000;

	@Autowired
	private PropertiesUtil propertiesUtil;
	@Resource
    IMysqlApiDao mysqlApiDao;
	@Resource
	IMysqlDiyApplyDao diyApplyDao;


	private static final Logger LOGGER = LoggerFactory.getLogger(OperatingServiceImpl.class);

	@Override
	public Result<?> api(Long startTimeStamp, Long endTimeStamp, Integer order, String apiId, Integer currentPage,
			Integer pageSize) {
		Map<String,String> params = putParams(order, apiId, null, null, currentPage, pageSize);
		String basicUrl = propertiesUtil.getValue("ois_api");
		String url = new StringBuffer(basicUrl).append("/").append(startTimeStamp)
				.append("/").append(endTimeStamp).toString();
		HttpResult hResult = HttpUtil.get(url, null, params, true, TIME_OUT);
		return parseApi(hResult);
	}

	@Override
	public Result<?> openApply(Long startTimeStamp, Long endTimeStamp, Integer order, String apiId, String openApplyId,
			Integer currentPage, Integer pageSize) {
		Map<String,String> params = putParams(order, apiId, openApplyId, null, currentPage, pageSize);
		String basicUrl = propertiesUtil.getValue("ois_openApply");
		String url = new StringBuffer(basicUrl).append("/").append(startTimeStamp)
				.append("/").append(endTimeStamp).toString();
		HttpResult hResult = HttpUtil.get(url, null, params, true, TIME_OUT);
		return parseOpenApply(hResult,openApplyId,apiId);
	}

	@Override
	public Result<?> diyApply(Long startTimeStamp, Long endTimeStamp, Integer order, String apiId, String openApplyId,
			String diyApplyId, Integer currentPage, Integer pageSize) {
		Map<String,String> params = putParams(order, apiId, openApplyId, diyApplyId, currentPage, pageSize);
		String basicUrl = propertiesUtil.getValue("ois_diyApply");
		String url = new StringBuffer(basicUrl).append("/").append(startTimeStamp)
				.append("/").append(endTimeStamp).toString();
		HttpResult hResult = HttpUtil.get(url, null, params, true, TIME_OUT);
		return parseDiyApply(hResult,diyApplyId,openApplyId,apiId);
	}

	private Result<?> parseApi(HttpResult hResult) {

		BiPageEntity2 biPage = getBounds(hResult);

		if(null == biPage){
			return Result.errorResult("获取bi统计数据异常", ErrorCodeNo.SYS037, null, Status.FAILED);
		}

		List<JSONObject> jobs = new ArrayList<JSONObject>(); //封装最后的list用于返回
		List<BiBoundEntity2> biBound = biPage.getResultList();//原始的运营访问数据数据
		Map<String,Long> cMap = new LinkedHashMap<String,Long>();//封装verionid和number关系
		List<String> versionIds = new ArrayList<String>();//封装versionids

		for (BiBoundEntity2 biBoundEntity2 : biBound) {
			cMap.put(biBoundEntity2.getVersionId(), biBoundEntity2.getNumber());
			versionIds.add(biBoundEntity2.getVersionId());
		}

		if(versionIds.size() > 0){ //判断只有集合不为空才去查询数据库
			List<NewApiEntity> entitys = mysqlApiDao.findByVersionIds(versionIds);
			for (NewApiEntity newApiEntity : entitys) {
				JSONObject job = JSONObject.parseObject(JSONObject.toJSONString(newApiEntity));
				job.put(COUNT, cMap.get(newApiEntity.getVersionId()));
				jobs.add(job);
			}
		}

		JSONObject data = new JSONObject();
		data.put(CURRENT_PAGE1, biPage.getCurrentPage());
		data.put(PAGE_SIZE1, biPage.getPageSize());
		data.put(TOTAL_NUMBER1, biPage.getTotalNumber());
		data.put(TOTAL_PAGE1, biPage.getTotalPage());
		data.put(RESULT_LIST, jobs);

		return Result.successResult("",data);
	}

	private Result<?> parseOpenApply(HttpResult hResult, String openApplyId, String apiId) {

		//如果openApplyId不为空代表查询开放应用下api
		if(StringUtils.isNotBlank(openApplyId)){
			return parseApi(hResult);
		}

		//否则查询开放应用热度
		BiPageEntity2 biPage = getBounds(hResult);
		if(null == biPage){
			return Result.errorResult("获取bi统计数据异常", ErrorCodeNo.SYS037, null, Status.FAILED);
		}
		List<BiBoundEntity2> bounds = biPage.getResultList();
		List<String> openApplyIds = new ArrayList<String>();
		Map<String,Long> cMap = new LinkedHashMap<String,Long>();
		for (BiBoundEntity2 biBoundEntity2 : bounds) {
			openApplyIds.add(biBoundEntity2.getOpenApplyId());
			cMap.put(biBoundEntity2.getOpenApplyId(), biBoundEntity2.getNumber());
		}

		List<BiOpenApply> inOrder = new ArrayList<BiOpenApply>();
		JSONObject data = new JSONObject();
		if(openApplyIds.size() > 0){
			//调用租户接口根据id获取开放应用列表并排序以及绑定结束------------------------------------------
			String url = propertiesUtil.getValue("findAppsById");
			String apiIds$ = Pattern.quote("#{apiIds}");
			try {
				url = url.replaceAll(apiIds$, URLEncoder.encode(openApplyIds.toString(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {}
			HttpResult result = HttpUtil.get(url, null, true, TIME_OUT);


			if(null != result && null != result.getStatus() && 200 == result.getStatus()){
				JSONObject job = JSONObject.parseObject(result.getBody());
				List<BiOpenApply> biOpenApplys = JSONObject.parseArray(job.getJSONArray("data").toJSONString(),
						BiOpenApply.class); //乱序
				//将访问量count绑定到BiOpenApply
				Map<String, BiOpenApply> outOfOrder = new HashMap<String,BiOpenApply>();
				for (BiOpenApply biOpenApply : biOpenApplys) {
					Long count = cMap.get(biOpenApply.getAppId());
					biOpenApply.setCount(count);
					outOfOrder.put(biOpenApply.getAppId(), biOpenApply);
				}
				//排序
				for (String id : openApplyIds) {
					inOrder.add(outOfOrder.get(id));
				}
				//调用租户接口根据id获取开放应用列表并排序以及绑定结束------------------------------------------
			}

			data.put(CURRENT_PAGE1, biPage.getCurrentPage());
			data.put(PAGE_SIZE1, biPage.getPageSize());
			data.put(TOTAL_NUMBER1, biPage.getTotalNumber());
			data.put(TOTAL_PAGE1, biPage.getTotalPage());
			data.put(RESULT_LIST, inOrder);
		}
		return Result.successResult("", data);
	}

	private Result<?> parseDiyApply(HttpResult hResult, String diyApplyId, String openApplyId, String apiId) {
		if(StringUtils.isNotBlank(diyApplyId)){
			return parseOpenApply(hResult, openApplyId, apiId);
		}

		//获取所有定制应用列表
		BiPageEntity2 biPage = getBounds(hResult);
		if(null == biPage){
			return Result.errorResult("获取bi统计数据异常", ErrorCodeNo.SYS037, null, Status.FAILED);
		}
		//获取定制应用列表数据
		List<BiBoundEntity2> bounds = biPage.getResultList();
		List<String> diyClientIds = new ArrayList<String>();
		Map<String,Long> cMap = new LinkedHashMap<String,Long>();

		Long innerAccessCount = null;
		for (BiBoundEntity2 bound : bounds) {
			if(StringUtils.isBlank(bound.getClientId())){
				innerAccessCount = bound.getNumber();
				continue;
			}
			diyClientIds.add(bound.getClientId());
			cMap.put(bound.getClientId(), bound.getNumber());
		}

		List<BiDiyApply> inOrder = new ArrayList<BiDiyApply>();
		JSONObject data = new JSONObject();
		if(diyClientIds.size() > 0){
			inOrder = diyApplyDao.findBiDiyByClientIds(diyClientIds);
			//将定制应用数据和当前数据绑定
			for (BiDiyApply biDiyApply : inOrder) {
				biDiyApply.setCount(cMap.get(biDiyApply.getClientId()));
			}
			inOrder.add(0,BiDiyApply.InnerAccess(innerAccessCount)); //添加内部调用的访问统计
		}

		data.put(CURRENT_PAGE1, biPage.getCurrentPage());
		data.put(PAGE_SIZE1, biPage.getPageSize());
		data.put(TOTAL_NUMBER1, biPage.getTotalNumber());
		data.put(TOTAL_PAGE1, biPage.getTotalPage());
		data.put(RESULT_LIST, inOrder);
		return Result.successResult("", data);
	}

	/**
	 *
	 * @Title: getBounds
	 * @Description: 从httpResult中获取绑定集合
	 * @author: makangwei
	 * @date:   2018年6月5日 上午10:45:44
	 * @param : @param hResult
	 * @param : @return
	 * @return: List<BiBoundEntity2>
	 * @throws
	 */
	private BiPageEntity2 getBounds(HttpResult hResult) {
		if(hResult == null){
			LOGGER.warn("获取的http result 结果为 null");
			return null;
		}
		if(null == hResult || hResult.getStatus() != 200){
			LOGGER.warn("获取的http result 状态码为：{}",hResult.getStatus());
			return null;
		}
		if(StringUtils.isBlank(hResult.getBody())){
			LOGGER.warn("获取的http result body为：{}",hResult.getBody());
			return null;
		}
		BiPageEntity2 biPage2 = null;
		try{
			JSONObject data =
					JSONObject.parseObject(hResult.getBody()).getJSONObject("data");
			BiPageEntity biPage = data.toJavaObject(BiPageEntity.class);
			biPage2 = new BiPageEntity2(biPage);
			LOGGER.debug(biPage2.toString());
		}catch(Exception e){
			LOGGER.warn("返回json解析错误,原数据为：{},堆栈信息为：{}/n",hResult.getBody(),e);
			return null;
		}

		return biPage2;
	}


	/**
	 *
	 * @Title: putParams
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author: makangwei
	 * @date:   2018年6月5日 下午2:45:32
	 * @param : @param order
	 * @param : @param apiId
	 * @param : @param openApplyId
	 * @param : @param clientId
	 * @param : @param currentPage
	 * @param : @param pageSize
	 * @param : @return
	 * @return: Map<String,String>
	 * @throws
	 */
	private Map<String,String> putParams(Integer order,String apiId,String openApplyId,String diyApplyId,
			Integer currentPage,Integer pageSize){
		Map<String, String > params = new HashMap<String, String>();
		if(null != order &&(1== order || 2 == order)){
			String od = order == 1 ? "asc" : "desc";
			params.put(ORDER, od);
		}
		if(StringUtils.isNotBlank(apiId)){
			NewApiEntity entity = mysqlApiDao.findById(apiId);
			if(null != entity){
				params.put(API_ID, entity.getVersionId());
			}
		}
		if(StringUtils.isNotBlank(openApplyId)){
			params.put(OPEN_APPLY_ID, openApplyId);
		}
		if(StringUtils.isNotBlank(diyApplyId)){
			DiyApplyEntity diy = diyApplyDao.findById(diyApplyId);
			if(null != diy){
				params.put(CLIENT_ID, diy.getClientId());
			}
		}
		if(null != currentPage && currentPage > 0){
			params.put(CURRENT_PAGE, currentPage+"");
		}
		if(null != pageSize && pageSize > 0){
			params.put(PAGE_SIZE, pageSize+"");
		}
		return params;
	}
}
