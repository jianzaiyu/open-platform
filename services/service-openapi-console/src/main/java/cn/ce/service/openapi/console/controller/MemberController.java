package cn.ce.service.openapi.console.controller;

import cn.ce.framework.base.exception.BusinessException;
import cn.ce.framework.base.pojo.Page;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author: ggs
 * @date: 2019-04-22 10:44
 **/
@Api(description = "会员信息获取")
@RequestMapping("member")
@RestController
public class MemberController {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${findMemberInfoByMemberCode}")
    private String memberUrl;
    @Value("${findWebListByCustId}")
    private String webListUrl;
    @Value("${findTenatById}")
    private String findTenatById;

    @GetMapping("webList/{memberCode}")
    public Page getWebListByMemberCode(@PathVariable String memberCode, Page page) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(memberUrl + "{memberCode:'" + memberCode + "'}");
        JSONObject memberInfo = restTemplate.getForObject(builder.build().encode().toUri(), JSONObject.class);
        String custId = null;
        try {
            custId = memberInfo.getJSONObject("data").getJSONObject("member").getString("custId");
        } catch (NullPointerException e) {
            throw new BusinessException("查询失败，数据不存在!");
        }
        page.fill(restTemplate.getForObject(webListUrl + custId, JSONObject.class).getJSONArray("data"));
        return page;
    }

    @GetMapping("tenantInfo/{id}")
    public JSONObject getTenantById(@PathVariable String id) {
        return restTemplate.getForObject(findTenatById+id, JSONObject.class).getJSONObject("data");
    }

}
