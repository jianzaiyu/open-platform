package cn.ce.service.openapi.base.gateway.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import cn.ce.service.openapi.base.common.SaasConstants;
import cn.ce.service.openapi.base.common.gateway.GatewayRouteUtils;
import cn.ce.service.openapi.base.gateway.dao.IMysqlSaasDao;
import cn.ce.service.openapi.base.gateway.entity.SaasEntity;
import cn.ce.service.openapi.base.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.ce.service.openapi.base.gateway.service.ISaasService;
import io.netty.handler.codec.http.HttpMethod;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2018年3月22日
*/
@Service(value="saasService")
public class SaasServiceImpl implements ISaasService{

	@Resource
	private IMysqlSaasDao saasDao;
	
	/**
	 * 
	 * @Title: save
	 * @Description: 只能添加不能修改,添加不带沙箱的路由
	 * @author: makangwei 
	 * @date:   2018年3月28日 下午7:45:32 
	 */
	private int save(SaasEntity saas) {
		if(null ==saas){
			return 0;
		}else if(StringUtils.isBlank(saas.getResourceType()) ||
				StringUtils.isBlank(saas.getSaasId()) ||
				StringUtils.isBlank(saas.getTargetUrl())){
			return 0;
		}
		saas.setRouteId(RandomUtil.random32UUID());
		saas.setCreateDate(new Date());
		saas.setUpdateDate(saas.getCreateDate());
		return saasDao.save(saas);
	}

	@Override
	public void clearAll() {
		saasDao.clearAll();
	}

	/**
	 * 
	 * @Title: getSaas
	 * @Description: 获取不带沙箱的路由
	 * @author: makangwei 
	 * @date:   2018年3月31日 上午10:05:34 
	 */
	@Override
	public String getSaas(String saasId, String resourceType,String method) {
		SaasEntity saas = saasDao.getSaas(saasId,resourceType);
		if(null == saas){
			String routeBySaasId = GatewayRouteUtils.getRouteBySaasId(saasId,resourceType,HttpMethod.GET.toString());
			SaasEntity saas1 = JSON.parseObject(routeBySaasId, SaasEntity.class);
			if(null != saas1 && StringUtils.isNotBlank(saas1.getSaasId())
					&&StringUtils.isNotBlank(saas1.getResourceType())
					&&StringUtils.isNotBlank(saas1.getTargetUrl())){
				int num = this.save(saas1);
			}
			return routeBySaasId;
		}else{
			JSONObject job = new JSONObject();
			job.put("saas_id", saas.getSaasId());
			job.put("resource_type", saas.getResourceType());
			job.put("target_url",saas.getTargetUrl());
			return job.toString(); 
		}
	}

	/**
	 * 
	 * @Title: saveSaas
	 * @Description: 保存不带沙箱的路由
	 * @author: makangwei 
	 * @date:   2018年3月31日 上午10:06:09 
	 */
	@Override
	public String saveSaas(String saasId, String resourceType, String targetUrl,String method) {
		
		String routeBySaasId = GatewayRouteUtils.saveRoute(saasId, targetUrl, resourceType,method);
		
		JSONObject job = new JSONObject(routeBySaasId);
		if(SaasConstants.STATUS_OK.equals(job.getString(SaasConstants.STATUS))){
				SaasEntity saas1 = saasDao.getSaas(saasId, resourceType);
				if(null != saas1 && StringUtils.isNotBlank(saas1.getResourceType()) && StringUtils.isNotBlank(saas1.getSaasId())){
					saasDao.updateSaas(saasId, resourceType, targetUrl);
				}else{
					this.save(new SaasEntity(saasId, resourceType, targetUrl));
				}
		}
		return routeBySaasId;
	}

	/**
	 * 
	 * @Title: deleteSaas
	 * @Description: 删除不带沙箱的路由
	 * @author: makangwei 
	 * @date:   2018年3月31日 上午10:06:40 
	 */
	@Override
	public String deleteSaas(String saasId, String resourceType, String method) {
		
		String routeBySaasId = GatewayRouteUtils.deleteRoute(saasId,resourceType,method);
		JSONObject job = new JSONObject(routeBySaasId);
		if(SaasConstants.STATUS_OK.equals(job.getString(SaasConstants.STATUS)) && 
				SaasConstants.ACTION_DELETED.equals(job.getString(SaasConstants.ACTION))){
			saasDao.deleteSaas(saasId, resourceType);
		}
		return routeBySaasId;
	}
}

