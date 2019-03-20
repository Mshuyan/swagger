package com.shuyan.swagger.controller;

import com.sun.deploy.net.protocol.ProtocolType;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sun.security.ssl.ProtocolVersion;

import java.util.List;

/**
 * @author will
 */
@Api(tags = "测试swagger相关的api")
@RestController
public class TestController {
    /**
     * 基本测试
     */
    @ApiOperation(value = "测试", notes = "note",authorizations = @Authorization("AuthKey"))
    @ApiImplicitParams({
            @ApiImplicitParam(name = "str", value = "要说的话",paramType = "form",type = "integer"),
            @ApiImplicitParam(name = "id", value = "订单id",required = true,paramType = "form",dataType = "long",allowableValues = "range[1, 5)")
    })
    @PostMapping(value = "/test")
    public String test(String str, TestDto dto){
        return "you said "+  str + dto;
    }


    /**
     * ApiImplicitParam 的 dataType 指定为Long时，必须指定 example 属性
     */
    @ApiOperation(value = "测试Long类型必须指定example问题",nickname = "123")
    @GetMapping("/test/{id}")
    public String testLong(@ApiParam("idid") Long id){
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
    public TestForm testReturn(){
        return new TestForm();
    }

    @ApiOperation(value = "body")
    @ApiImplicitParam(name = "dto",dataType = "TestDto",examples = @Example({
            @ExampleProperty(mediaType = "id",value = "12")
    }))
    @PostMapping(value = "/body")
    public String body(@RequestBody TestDto dto){
        return dto.toString();
    }


    @ApiOperation(value = "param", response = Integer.class, responseContainer = "list")
    @PostMapping(value = "/param")
    public String param(TestDto dto){
        return "11";
    }
}
