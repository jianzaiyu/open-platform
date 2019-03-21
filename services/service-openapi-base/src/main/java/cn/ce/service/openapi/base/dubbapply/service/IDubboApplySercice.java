package cn.ce.service.openapi.base.dubbapply.service;

import java.util.List;

import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.dubbapply.entity.DubboApplyEntity;

public interface IDubboApplySercice {
	
    /**
     * 解析jar
     * @param path
     * @param dependcyFileName
     * @param mainJarName
     * @return
     */
	public boolean saveDubboApplySercice(String path, String[] dependcyFileName, String mainJarName);
}
