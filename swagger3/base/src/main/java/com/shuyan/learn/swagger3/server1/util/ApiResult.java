package com.shuyan.learn.swagger3.server1.util;

import lombok.Data;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.Serializable;
import java.util.Locale;

import static com.shuyan.learn.swagger3.server1.util.ResponseCode.SYS_SUCCESSFUL_REQUEST;

/**
 * 统一响应信息主体
 *
 * @author shuyan
 */
@Data
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = 8746053006546246031L;
    private T data;
    private Integer code;
    private String message;

    public ApiResult() {
        this(null, ResponseCode.instance(SYS_SUCCESSFUL_REQUEST));
    }

    public ApiResult(T data) {
        this(data, ResponseCode.instance(SYS_SUCCESSFUL_REQUEST));
    }

    public ApiResult(ResponseCode responseCode) {
        this(null, responseCode);
    }

    public ApiResult(ResponseCode responseCode, String chMsg, String enMsg) {
        this(null, responseCode, chMsg, enMsg);
    }

    public ApiResult(T data, ResponseCode responseCode) {
        this(data, responseCode, null, null);
    }

    public ApiResult(T data, ResponseCode responseCode, String chMsg, String enMsg) {
        Locale locale = LocaleContextHolder.getLocale();
        if (Locale.CHINESE.getLanguage().equals(locale.getLanguage())) {
            this.message = (chMsg == null) ? responseCode.getChMsg() : chMsg;
        } else {
            this.message = (enMsg == null) ? responseCode.getEnMsg() : enMsg;
        }
        this.data = data;
        this.code = responseCode.getCode();
    }

    public static <T> ApiResult<T> ok() {
        return new ApiResult<>();
    }

    public static <T> ApiResult<T> ok(T data) {
        return new ApiResult<>(data);
    }
}
