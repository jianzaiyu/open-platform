package cn.ce.service.openapi.base.dubbapply.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import cn.ce.service.openapi.base.annotation.InterfaceDescriptionFullEnty;
import cn.ce.service.openapi.base.dubbapply.dao.DubboApplyEntityMapper;
import cn.ce.service.openapi.base.dubbapply.entity.DubboApplyEntity;
import cn.ce.service.openapi.base.dubbapply.service.IDubboApplySercice;
import cn.ce.service.openapi.base.util.ModuleClassLoader;
/***
 * dubbo jar解析服务
 * @author huangdayang
 * 2018年3月16日
 */
@Slf4j
@Service("dubboApplySercice")
public class DubboApplySerciceImpl implements IDubboApplySercice {
	/** 日志对象 */
	@Resource
	private DubboApplyEntityMapper dubboApplyEntityMapper;

	private void saveDubboApplySercice(List<DubboApplyEntity> dubboApplyEntityList) {
		for (DubboApplyEntity record : dubboApplyEntityList) {
			dubboApplyEntityMapper.insert(record);
		}
	}

	@Override
	public boolean saveDubboApplySercice(String path, String[] dependencyJarName, String mainJarName) {
		ModuleClassLoader moduleClassLoader = ModuleClassLoader.getInstance();
		List<String> list = new ArrayList<String>();
		list.add(path+File.separator+mainJarName);
		if(dependencyJarName != null) {
			for (String string : dependencyJarName) {
				list.add(path+File.separator+string);
			}
		}
		String[] jarpath =list.toArray(new String[] {});
		Map<String, Map<String, InterfaceDescriptionFullEnty>> simpleInfo= null;
		try {
			simpleInfo = moduleClassLoader.parseJars(jarpath);
		} catch (ClassNotFoundException e) {
			log.error("class load ClassNotFoundException", e);
		} catch (URISyntaxException e) {
			log.error("class load URISyntaxException", e);
		} catch (IOException e) {
			log.error("class load IOException", e);
		}
		if(simpleInfo == null) return false;
		int c = this.countMainJar(simpleInfo);
		if(c != 1) {
			log.error("main jar must only one,but now is :"+c);
			return false;
		};
		
		
		
		return false;
	}
	/**
	 * 统计main jar的数量
	 * @param simpleInfo
	 * @return
	 */
	private int countMainJar(Map<String, Map<String, InterfaceDescriptionFullEnty>> simpleInfo) {
		int count = 0;
		for (String jarName : simpleInfo.keySet()) {
			if(simpleInfo.get(jarName).size() >=  1) {
				count++;
			}
		}
		return count;
		
	}
    /**
     * 处理解析信息
     * @param simpleInfo
     * @return
     */
	private List<DubboApplyEntity> dealInterfaceDescriptionFullEnty(Map<String, Map<String, InterfaceDescriptionFullEnty>> simpleInfo) {
		List<DubboApplyEntity> list = new ArrayList<DubboApplyEntity>();
		String rootId = UUID.randomUUID().toString();
		for (String jarName : simpleInfo.keySet()) {
			if(simpleInfo.get(jarName).size() >=  1) {
				 list.addAll(convertMainJar(rootId,jarName,simpleInfo.get(jarName)));
			} else {
				//dependency 处理依赖包 
				DubboApplyEntity dae = new DubboApplyEntity();
				dae.setAppcode("");
				dae.setAppid("");
				dae.setAppname("");
				dae.setFilepath("");
				dae.setFiletag("2"); //dependency
				dae.setFilename(jarName);
				dae.setCreattime(new Date());
				dae.setUpdattime(new Date());
				dae.setCreatuserid("");
				dae.setParentid(rootId);
				list.add(dae);
			}
		}
		return list;
	}

	
	private List<DubboApplyEntity> convertMainJar(String rootId,String jarName,Map<String, InterfaceDescriptionFullEnty> mainJarInfo) {
		List<DubboApplyEntity> list = new ArrayList<DubboApplyEntity>();
		//处理 jar rootId
		DubboApplyEntity root = new DubboApplyEntity();
		root.setAppcode("");
		root.setId(rootId);
		root.setAppid("");
		root.setAppname("");
		root.setFilepath("");
		root.setFiletag("1"); //service
		root.setFilename(jarName);
		root.setCreattime(new Date());
		root.setUpdattime(new Date());
		root.setCreatuserid("");
		root.setParentid(null);
		list.add(root);
	    //处理 class 
		Map<String,String> classMap = new HashMap<String,String>(); 
		for (InterfaceDescriptionFullEnty enty : mainJarInfo.values()) {
			String parentId = classMap.get(enty.getClassname());
			if(parentId == null) {
				parentId = UUID.randomUUID().toString();
				classMap.put(enty.getClassname(),parentId);
			};
			DubboApplyEntity dae = new DubboApplyEntity();
			dae.setAppcode("");
			dae.setAppid("");
			dae.setAppname("");
			dae.setFilepath("");
			dae.setFiletag("1"); //service
			dae.setFilename(jarName);
			dae.setCreattime(new Date());
			dae.setUpdattime(new Date());
			dae.setParentid(null);         
			dae.setCreatuserid("");
		}
		return list;
		
	}

}
