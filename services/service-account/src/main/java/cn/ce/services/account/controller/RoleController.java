package cn.ce.services.account.controller;

import cn.ce.services.account.entity.Role;
import cn.ce.services.account.service.RoleService;
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

    @DeleteMapping("{id}")
    public void deleteByPrimaryKey(@PathVariable Integer id) {
        roleService.deleteByPrimaryKey(id);
    }

    @PutMapping
    public void updateByPrimaryKeySelective(@RequestBody Role role) {
        roleService.updateByPrimaryKeySelective(role);
    }

    @GetMapping("{id}")
    public Role selectByPrimaryKey(@RequestParam Integer id) {
        return roleService.selectByPrimaryKey(id);
    }
}
