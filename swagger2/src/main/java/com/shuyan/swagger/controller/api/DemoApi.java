package com.shuyan.swagger.controller.api;

import com.shuyan.swagger.controller.TestForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author will
 */
@Api(tags = "demo相关接口")
public interface DemoApi {
    /**
     * 12
     * @param form 12
     * @return 12
     */
    @ApiIgnore
    @ApiOperation(value = "修改dto")
    String postDto(TestForm form);

    @ApiOperation("增加资源")
    @ApiImplicitParam(name = "name",value = "名称",paramType = "form")
    String post(String name);
}
