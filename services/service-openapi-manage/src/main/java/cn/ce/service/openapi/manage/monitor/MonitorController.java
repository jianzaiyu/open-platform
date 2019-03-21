package cn.ce.service.openapi.manage.monitor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
* @Description : 监控接口
* @Author : makangwei
* @Date : 2017年12月18日
*/
@RestController
@RequestMapping(value="/monitor")
public class MonitorController {

	@RequestMapping(value="/manage", method=RequestMethod.GET)
	public String manageMonitor(){
		
		return "ok";
	}
}
