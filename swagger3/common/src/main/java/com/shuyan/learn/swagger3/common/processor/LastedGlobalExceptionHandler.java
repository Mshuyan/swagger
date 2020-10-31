package com.shuyan.learn.swagger3.common.processor;

import com.shuyan.learn.swagger3.server1.util.ApiResult;
import com.shuyan.learn.swagger3.server1.util.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.shuyan.learn.swagger3.server1.util.ResponseCode.*;

/**
 * 全局异常处理器
 *
 * @author shuyan
 */
@Slf4j
@Order
@RestControllerAdvice
public class LastedGlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResult<?>> handleException(Exception e) {
        log.error("系统错误: ", e);
        ResponseCode instance = instance(SYS_INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(new ApiResult<>(instance),instance.getStatus());
    }
}
