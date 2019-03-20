package com.shuyan.swagger.controller.api;

import com.shuyan.swagger.controller.ReturnDto;
import com.shuyan.swagger.controller.TestDto;
import com.shuyan.swagger.controller.TestForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author will
 */
@Api(tags = "demo相关接口")
public interface DemoApi {

    @ApiOperation(value = "修改dto")
    String postDto(TestForm form);

    @ApiOperation("增加资源")
    @ApiImplicitParam(name = "name",value = "名称",paramType = "form")
    String post(String name);
}
