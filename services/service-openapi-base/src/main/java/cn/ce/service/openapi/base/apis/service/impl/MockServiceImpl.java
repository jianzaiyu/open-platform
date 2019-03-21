package cn.ce.service.openapi.base.apis.service.impl;

import cn.ce.service.openapi.base.apis.dao.IMysqlApiDao;
import cn.ce.service.openapi.base.apis.dao.IMysqlApiMockDao;
import cn.ce.service.openapi.base.apis.entity.ApiMock;
import cn.ce.service.openapi.base.apis.entity.NewApiEntity;
import cn.ce.service.openapi.base.apis.service.IConsoleApiService;
import cn.ce.service.openapi.base.apis.service.IManageApiService;
import cn.ce.service.openapi.base.apis.service.IMockService;
import cn.ce.service.openapi.base.common.AuditConstants;
import cn.ce.service.openapi.base.common.ErrorCodeNo;
import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.common.Status;
import cn.ce.service.openapi.base.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName: MockServiceImpl
 * @Description: TODO
 * @create 2018/8/23 14:49/MKW
 * @update 2018/8/23 14:49/MKW/(说明。)....多次修改添加多个update
 */
@Service
public class MockServiceImpl implements IMockService {

    @Resource
    private IMysqlApiDao mysqlApiDao;
    @Resource
    private IMysqlApiMockDao apiMockDao;
    @Resource
    private IManageApiService manageApiService;

    @Override
    public Result selectByVersionId(String versionId) {
        ApiMock mock = apiMockDao.selectByVersionId(versionId);
        if(null != mock && StringUtils.isNotBlank(mock.getMockStr())){
            String str = mock.getMockStr();
            Object obj = JSONObject.parse(str);
            mock.setMock(obj);
        }
        if(null != mock && StringUtils.isNotBlank(mock.getHeaderStr())){
            Map map = JSONObject.parseObject(
                    StringEscapeUtils.unescapeJava(mock.getHeaderStr())
            ).toJavaObject(Map.class);
            mock.setHeaders(map);
        }

        return Result.successResult("",mock);
    }


    @Override
    public Result inserOrUpdate(ApiMock apiMock) {

        List<NewApiEntity> newApiEntitys = mysqlApiDao.findByVersionId(apiMock.getVersionId());
        if(newApiEntitys.isEmpty()){
            return new Result<String>("当前api不存在", ErrorCodeNo.SYS006, null, Status.FAILED);
        }

        boolean hasSuccess=false;
        NewApiEntity entity = null;
        for (NewApiEntity newApiEntity : newApiEntitys) {
            if(AuditConstants.API_CHECK_STATE_SUCCESS == newApiEntity.getCheckState()){
                hasSuccess = true;
                entity = newApiEntity;
                break;
            }
        }

        if(!hasSuccess){
            return new Result<String>("当前不同版本api均未审核通过", ErrorCodeNo.SYS017, null, Status.FAILED);
        }


        net.sf.json.JSONObject job = null;
        try {
            job = (null == apiMock.getMock() ?
                    null : net.sf.json.JSONObject.fromBean(apiMock.getMock()));
        } catch (Exception e) {
            return new Result<String>("mock格式异常，支持持json", ErrorCodeNo.SYS012, apiMock.getMockStr(), Status.FAILED);
        }

        apiMock.setMockStr(null == job ? null : job.toString());
        if(null != apiMock.getHeaders() && !apiMock.getHeaders().isEmpty()){
            apiMock.setHeaderStr(new org.json.JSONObject(apiMock.getHeaders()).toString());
        }
        apiMock.setCreateTime(new Date());
        apiMock.setMockId(RandomUtil.random32UUID());
        apiMockDao.deleteByVersionId(apiMock.getVersionId());
        apiMockDao.insert(apiMock);
        //先修改api推送到网关。如果网关推送成功添加到数据库中
        List<String> apiIds = new ArrayList<>();
        apiIds.add(entity.getId());
        return manageApiService.auditApi(apiIds,AuditConstants.API_CHECK_STATE_SUCCESS,null,true);
    }
}
