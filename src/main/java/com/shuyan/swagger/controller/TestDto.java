package com.shuyan.swagger.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author will
 */
@Data
public class TestDto implements Serializable {
    private static final long serialVersionUID = 1L;
    //@ApiModelProperty(value = "订单ID",example = "1")
    private Long id;
}
