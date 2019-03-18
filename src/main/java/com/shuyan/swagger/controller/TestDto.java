package com.shuyan.swagger.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.Extension;
import io.swagger.annotations.ExtensionProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author will
 */
@ApiModel(description = "订单")
@Data
public class TestDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "订单ID",example = "1")
    private Long id;
}
