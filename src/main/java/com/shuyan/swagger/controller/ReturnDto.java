package com.shuyan.swagger.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "订单查询请求数据")
public class ReturnDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "订单ID",example = "1")
    private Long id;
}
