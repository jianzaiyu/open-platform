package cn.ce.service.openapi.base.dubbapply.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import cn.ce.service.openapi.base.dubbapply.entity.Interfaceapplyentity.DubboApps;
import cn.ce.service.openapi.base.dubbapply.service.IGetAppListSercice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ce.service.openapi.base.common.ErrorCodeNo;
import cn.ce.service.openapi.base.common.HttpClientUtil;
import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.util.PropertiesUtil;

@Slf4j
@Service("getAppListSercice")
public class GetAppListSerciceImpl implements IGetAppListSercice {
	/** 日志对象 */
	@Autowired
	private PropertiesUtil propertiesUtil;
	@Override
	public Result<DubboApps> findAppsByUnit(String unit) {
		Result<DubboApps> result = new Result<>();
		String url = propertiesUtil.getValue("dubbo_app_interfaceurl");
		String unit$ = Pattern.quote("#{unit}");// ${o} 所属企业 CE 为中企动力 不填为所有
		String replacedurl = url.replaceAll(unit$, unit);
		log.info("请求产品中心Url:" + url);
		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
		classMap.put("list", cn.ce.service.openapi.base.diyApply.entity.appsEntity.AppList.class);
		classMap.put("appTypes", cn.ce.service.openapi.base.diyApply.entity.appsEntity.AppTypes.class);

		try {
			/* get请求方法 */
			DubboApps apps = (DubboApps) HttpClientUtil.getUrlReturnObject(replacedurl, DubboApps.class, classMap);
			log.info("调用产品Http请求发送成功");
			if (apps.getStatus().equals("200") || apps.getStatus().equals("110")) {
				result.setSuccessData(apps);
				return result;
			} else {
				log.error("dubbo_app_interfaceurl data http getfaile return code :" + apps.getMsg() + " ");
				result.setErrorCode(ErrorCodeNo.SYS006);
				return result;
			}
		} catch (Exception e) {

			log.error("dubbo_app_interfaceurl http error " + e + "");
			result.setErrorCode(ErrorCodeNo.SYS018);
			result.setErrorMessage("请求失败");
			return result;
		}
	}

}
