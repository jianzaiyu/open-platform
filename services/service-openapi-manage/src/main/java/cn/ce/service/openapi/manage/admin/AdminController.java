package cn.ce.service.openapi.manage.admin;

import cn.ce.service.openapi.base.admin.service.IAdminService;
import cn.ce.service.openapi.base.common.ErrorCodeNo;
import cn.ce.service.openapi.base.common.Result;
import cn.ce.service.openapi.base.common.Status;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private IAdminService adminService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<?> login(HttpSession session,
                           @RequestParam(required = true) String userName,
                           @RequestParam(required = true) String password) {

        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            return new Result<String>("用户名或密码不能为空", ErrorCodeNo.SYS005, null, Status.FAILED);
        }

        return adminService.login(session, userName, password);
    }


    @RequestMapping(value = "/logOut", method = RequestMethod.POST)
    public Result<String> logOut(HttpSession session) {

        session.invalidate();
        return new Result<String>("退出登录成功", ErrorCodeNo.SYS000, null, Status.SUCCESS);
    }
}
