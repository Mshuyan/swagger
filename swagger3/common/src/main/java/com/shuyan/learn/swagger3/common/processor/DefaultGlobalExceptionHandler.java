package com.shuyan.learn.swagger3.common.processor;

import com.shuyan.learn.swagger3.common.exception.CustomizeException;
import com.shuyan.learn.swagger3.server1.util.ApiResult;
import com.shuyan.learn.swagger3.server1.util.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

import static com.shuyan.learn.swagger3.server1.util.ResponseCode.*;

/**
 * 全局异常处理器
 *
 * @author shuyan
 */
@Slf4j
@Order(0)
@RestControllerAdvice
public class DefaultGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResult<?>> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.warn("无效的请求参数: ", e);
        ResponseCode instance = instance(COMMON_INVALID_PARAM);
        return new ResponseEntity<>(new ApiResult<>(instance),instance.getStatus());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResult<?>> handleMissingParamException() {
        ResponseCode instance = instance(COMMON_MISS_PARAMETER);
        return new ResponseEntity<>(new ApiResult<>(instance),instance.getStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResult<?>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.warn("请求body格式错误: ", e);
        ResponseCode instance = instance(SYS_BODY_FORMAT_ERROR);
        return new ResponseEntity<>(new ApiResult<>(instance),instance.getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResult<?>> constraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        ConstraintViolation<?> constraintViolation = constraintViolations.iterator().next();
        String field = constraintViolation.getPropertyPath().toString();
        field = field.substring(field.lastIndexOf(".") + 1);
        String error = constraintViolation.getMessage();
        ResponseCode instance = instance(error);
        if(instance == null) {
            instance = defaultValidate(error);
        }
        instance.setField(field);
        return new ResponseEntity<>(new ApiResult<>(instance),instance.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResult<?>> methodViolationException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String field = null;
        String error = null;
        if(fieldError != null){
            field = fieldError.getField();
            error = fieldError.getDefaultMessage();
        }
        ResponseCode instance = defaultValidate(error);
        instance.setField(field);
        return new ResponseEntity<>(new ApiResult<>(instance),instance.getStatus());
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiResult<?>> bindException(BindException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String field = null;
        if(fieldError != null){
            field = fieldError.getField();
        }
        ResponseCode instance = instance(COMMON_INVALID_PARAM);
        instance.setField(field);
        return new ResponseEntity<>(new ApiResult<>(instance),instance.getStatus());
    }

    @ExceptionHandler(CustomizeException.class)
    public ResponseEntity<ApiResult<?>> handleCommonException(CustomizeException e) {
        ApiResult<?> result = new ApiResult<>(e.getResponseCode(), e.getChMsg(), e.getEnMsg());
        return new ResponseEntity<>(result,e.getResponseCode().getStatus());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.warn("不支持的请求方法: ", e);
        ResponseCode instance = instance(SYS_UNSUPPORTED_METHOD);
        return new ResponseEntity<>(new ApiResult<>(instance),instance.getStatus());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiResult<?>> httpMediaTypeNotSupportedException() {
        ResponseCode instance = instance(SYS_UNSUPPORTED_CONTENT_TYPE);
        return new ResponseEntity<>(new ApiResult<>(instance),instance.getStatus());
    }
}
