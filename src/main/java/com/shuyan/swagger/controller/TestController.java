package com.shuyan.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author will
 */
@RestController
@Api(tags = "测试swagger相关的api",description = "TestController")
public class TestController {

    @ApiOperation(value = "根据关键字查询产品列表", notes = "根据关键字查询产品列表 note")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "str", value = "要说的话", paramType = "query",required = true, dataType = "String")
    })
    // 当只有1个参数时，上面注解可替换为：
    // @ApiImplicitParam(name = "keywords", value = "关键字，以空格分割", required = true, dataType = "String")

    @GetMapping("/test")
    public String test(String str){
        return "you said " + str;
    }
}
