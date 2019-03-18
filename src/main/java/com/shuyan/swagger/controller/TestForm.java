package com.shuyan.swagger.controller;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author will
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "Test Form Model")
@Data
public class TestForm extends TestDto{
    private String name;
}
