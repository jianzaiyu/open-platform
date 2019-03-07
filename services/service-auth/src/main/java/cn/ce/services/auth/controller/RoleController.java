package cn.ce.services.auth.controller;

import cn.ce.services.auth.entity.Role;
import cn.ce.services.auth.service.RoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * @author: ggs
 * @date: 2019-03-06 19:01
 **/
@Api(description = "角色信息管理")
@Validated
@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping
    public void insertSelective(@RequestBody @Valid Role role) {
        roleService.insertSelective(role);
    }

    @DeleteMapping
    public void deleteByPrimaryKey(@RequestParam Integer roleId) {
        roleService.deleteByPrimaryKey(roleId);
    }

    @PutMapping
    public void updateByPrimaryKeySelective(@RequestBody Role role) {
        roleService.updateByPrimaryKeySelective(role);
    }

    @GetMapping
    public Role selectByPrimaryKey(@RequestParam Integer roleId) {
        return roleService.selectByPrimaryKey(roleId);
    }
}
