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
    /**
     * 基本测试
     * @param str
     * @return
     */
    @ApiOperation(value = "测试", notes = "note")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "str", value = "要说的话", paramType = "query",required = true, dataType = "String")
    })
    // 当只有1个参数时，上面注解可替换为：
    // @ApiImplicitParam(name = "keywords", value = "关键字，以空格分割", required = true, dataType = "String")
    @GetMapping("/test")
    public String test(String str){
        return "you said " + str;
    }


    /**
     * ApiImplicitParam 的 dataType 指定为Long时，必须指定 example 属性
     */
    @ApiOperation(value = "测试Long类型必须指定example问题")
    @ApiImplicitParam(name = "id", value = "Long 类型", required = true, dataType = "Long",example = "1")
    @GetMapping("/test/{id}")
    public String testLong(Long id){
        return "you said " + id;
    }

    /**
     * 在Model中指定参数字段说明
     * @param dto
     * @return
     */
    @ApiOperation(value = "测试ApiModelProperty用法")
    @GetMapping("/test1/{id}")
    public String testApiModelProperty(TestDto dto ){
        return "you said " + dto.getId();
    }

    /**
     * 测试返回值字段说明
     * @return
     */
    @ApiOperation(value = "测试返回数据说明")
    @GetMapping("/test2")
    public ReturnDto testReturn(){
        return new ReturnDto();
    }
}
