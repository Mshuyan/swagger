package com.shuyan.learn.swagger3.server1.controller;

import com.shuyan.learn.swagger3.server1.util.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author shuyan
 */
@Validated
@Api(value = "测试 controller", tags = {"测试接口"})
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 校验跳转页面是否还有效
     * @param jumpType 跳转类型
     * @param jumpParam 跳转参数
     * @return 是否有效
     */
    @ApiOperation("校验跳转页面是否还有效")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jumpType",value = "跳转类型"),
            @ApiImplicitParam(name = "jumpParam",value = "跳转参数")
    })
    @GetMapping("/check")
    ApiResult<Boolean> checkJumpValid(@NotBlank @Length(min = 1,max = 10) String jumpType, String jumpParam,@Min(10)Integer age){
        return null;
    }
}
