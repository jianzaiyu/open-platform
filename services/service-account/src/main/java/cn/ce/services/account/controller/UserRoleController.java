package cn.ce.services.account.controller;

import cn.ce.services.account.entity.RUserrole;
import cn.ce.services.account.entity.UserRoleDetail;
import cn.ce.services.account.service.RUserroleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ggs
 * @date 2019/3/6 22:34
 */
@Api(description = "用户角色信息管理")
@Validated
@RestController
@RequestMapping("userrole")
public class UserRoleController {
    @Autowired
    private RUserroleService rUserroleService;

    @PostMapping
    public void insertSelective(@RequestBody @Valid RUserrole rUserrole) {
        rUserroleService.insertSelective(rUserrole);
    }

    @DeleteMapping("{id}")
    public void deleteByPrimaryKey(@PathVariable Integer id) {
        rUserroleService.deleteByPrimaryKey(id);
    }

    @PutMapping
    public void updateByPrimaryKeySelective(@RequestBody RUserrole rUserrole) {
        rUserroleService.updateByPrimaryKeySelective(rUserrole);
    }

    @GetMapping("{id}")
    public RUserrole selectByPrimaryKey(@PathVariable Integer id) {
        return rUserroleService.selectByPrimaryKey(id);
    }

    @GetMapping("userid/{id}")
    public List<UserRoleDetail> selectByUId(@PathVariable Integer id) {
        return rUserroleService.selectByUId(id);
    }

}
