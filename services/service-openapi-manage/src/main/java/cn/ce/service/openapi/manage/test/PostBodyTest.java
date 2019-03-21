package cn.ce.service.openapi.manage.test;

import cn.ce.service.openapi.base.apis.entity.ApiVersion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
* @Description : 说明
* @Author : makangwei
* @Date : 2017年9月22日
*/
@Slf4j
@RestController
public class PostBodyTest {


	@RequestMapping(value="/testPostBody",method=RequestMethod.POST)
	public String testPostBody(@RequestBody String str){
		
		log.info(str);
		
		return "aaa";
	}
	
	@RequestMapping(value="/testPostBody1",method=RequestMethod.POST)
	public String testPostBody1(@RequestBody ApiVersion apiVersion){
		
		log.info("get in");
		log.info(apiVersion.toString());
		
		return "aaa";
	}
	
	@RequestMapping(value="/getGetBody1",method=RequestMethod.GET)
	public String testGetBody(String userName, String password){
		
		log.info("get in");
		
		return "aaa";
	}
	
}
