package cn.ce.services.account.controller;

import cn.ce.framework.base.pojo.Result;
import cn.ce.framework.base.pojo.ResultCode;
import cn.ce.framework.base.support.IdentifierGenerateSupport;
import cn.ce.framework.redis.support.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author: ggs
 * @date: 2019-03-22 10:41
 **/
@Api(description = "验证码相关_无保护")
@Validated
@RestController
@RequestMapping("code")
public class IdentifyCodeController {
    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation("根据参数ID,生成CODE,时效5分钟")
    @PostMapping
    public Result generateCode(@RequestParam @Length(min = 36, max = 36) String componentId) {
        String code = IdentifierGenerateSupport.genRandomUUID32();
        redisUtil.setForTimeMIN(componentId, code, 5);
        return new Result<>(HttpStatus.OK, ResultCode.SYS0000, code);
    }

    @ApiOperation(value = "验证ID与CODE")
    @GetMapping
    public boolean validCode(@RequestParam @Length(min = 36, max = 36) String componentId,
                             @RequestParam @Length(min = 32, max = 32) String code) {
        String value = redisUtil.get(componentId);
        if (value != null && value.equals(code)) {
            return true;
        }
        return false;
    }
}
