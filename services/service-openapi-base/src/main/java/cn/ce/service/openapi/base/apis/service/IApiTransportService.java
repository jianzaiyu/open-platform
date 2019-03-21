package cn.ce.service.openapi.base.apis.service;

import javax.servlet.http.HttpServletResponse;
import cn.ce.service.openapi.base.apis.entity.ApiExportParamEntity;
import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.users.entity.User;

/**
 * @Description : 文件导入导出service
 * @Author : makangwei
 * @Date : 2017年12月14日
 */
public interface IApiTransportService {

	String exportApis(String recordId, HttpServletResponse response);

	Result<?> generalExportList(ApiExportParamEntity exportParam, User user);

	Result<?> importApis(String upStr, User user);

	Result<?> getExportRecords(Integer checkCurrentPage, Integer checkPageSize);

	Result<?> getExportRecordById(String recordId);

	Result<?> getImportRecords(Integer checkCurrentPage, Integer checkPageSize);

	Result<?> getImportRecordById(String recordId);
}
