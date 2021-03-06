package cn.ce.services.account.controller;

import cn.ce.framework.base.exception.BusinessException;
import cn.ce.services.account.entity.Identify;
import cn.ce.services.account.service.IdentifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author ggs
 * @date 2019/3/6 23:00
 */
@Api(description = "用户认证管理")
@Validated
@RestController
@RequestMapping("identify")
public class IdentifyController {
    @Autowired
    private IdentifyService identifyService;

    @PostMapping
    @ApiOperation("增加一条认证")
    public void insertSelective(@RequestBody @Valid Identify identify) {
        if (identifyService.selectByUserId(identify.getUid()) != null) {
            throw new BusinessException("该用户的认证信息已经存在!");
        }
        if (identifyService.selectByCardNumber(identify.getCardNumber()) != null) {
            throw new BusinessException("该身份证号已经存在!");
        }
        identifyService.insertSelective(identify);
    }

    @PutMapping
    @ApiOperation("修改一条认证")
    public void updateByPrimaryKeySelective(@RequestBody Identify identify) {
        identifyService.updateByPrimaryKeySelective(identify);
    }

    @GetMapping("{id}")
    @ApiOperation("查询一条认证")
    public Identify selectByPrimaryKey(@PathVariable Integer id) {
        return identifyService.selectByPrimaryKey(id);
    }

    @GetMapping("user/{uid}")
    @ApiOperation("查询一条认证")
    public Identify selectByUserId(@PathVariable Integer uid) {
        return identifyService.selectByUserId(uid);
    }

    @GetMapping("cardNumber/{cardNumber}")
    @ApiOperation("按照身份证查询是否重复")
    public boolean selectByCardNumber(@PathVariable String cardNumber) {
        return identifyService.selectByCardNumber(cardNumber) != null;
    }
}
