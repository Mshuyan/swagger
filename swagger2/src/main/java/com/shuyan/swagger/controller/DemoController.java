package com.shuyan.swagger.controller;

import com.shuyan.swagger.controller.api.DemoApi;
import org.springframework.web.bind.annotation.*;

/**
 * @author will
 */
@RestController
public class DemoController implements DemoApi {

    @Override
    @PostMapping("/postDto")
    public String postDto(@RequestBody TestForm form) {
        return null;
    }

    @Override
    @PostMapping("/post")
    public String post(String name) {
        return null;
    }
}
