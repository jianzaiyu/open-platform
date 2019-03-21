package cn.ce.service.openapi.console.controller;

import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.dubbapply.entity.Interfaceapplyentity.DubboApps;
import cn.ce.service.openapi.base.dubbapply.service.IGetAppListSercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("findAppsByUnit")
public class FindAppsByUnit {

	@Autowired
	private IGetAppListSercice getAppListSercice;

	@RequestMapping(value = "findAppsByUnit", method = RequestMethod.GET)
	public Result<DubboApps> findAppsByUnit(@RequestParam(required = true) String unit) {

		return getAppListSercice.findAppsByUnit(unit);
	}
}
