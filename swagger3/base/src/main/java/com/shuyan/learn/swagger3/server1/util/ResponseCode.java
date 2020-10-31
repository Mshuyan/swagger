package com.shuyan.learn.swagger3.server1.util;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shuyan
 */
@Data
public class ResponseCode implements Serializable {
    private static final long serialVersionUID = -2877670960023701453L;
    private static final Map<String, ResponseCode> MAP = new HashMap<>();

    /* *****************************  200 请求成功  **************************** */

    /* -------------  00 系统状态  ------------- */
    /** 请求成功 */
    public static final String SYS_SUCCESSFUL_REQUEST = "SYS_SUCCESSFUL_REQUEST";

    /* *****************************  400 请求错误  **************************** */

    /* -------------  00 系统状态  ------------- */
    /** 请求body格式错误 */
    public static final String SYS_BODY_FORMAT_ERROR = "SYS_BODY_FORMAT_ERROR";
    /** 不支持的Content */
    public static final String SYS_UNSUPPORTED_CONTENT_TYPE = "SYS_UNSUPPORTED_CONTENT_TYPE";
    /** 丢失请求参数 */
    public static final String COMMON_MISS_PARAMETER = "COMMON_MISS_PARAMETER";
    /** 无效的参数 */
    public static final String COMMON_INVALID_PARAM = "COMMON_INVALID_PARAM";

    /* -------------  01 通用状态  ------------- */
    /** 参数校验错误 */
    public static final String COMMON_VALIDATE_ERROR = "COMMON_VALIDATE_ERROR";
    /** 不在允许值范围内 */
    public static final String COMMON_NOT_IN_ALLOWED_SCOPE = "COMMON_NOT_IN_ALLOWED_SCOPE";

    /* *****************************  404 路由错误  **************************** */
    /* -------------  00 系统状态  ------------- */
    /** 路由不存在 */
    public static final String SYS_NOT_FOUND = "SYS_NOT_FOUND";

    /* *****************************  405 不支持的请求方法  **************************** */
    /* -------------  00 系统状态  ------------- */
    /** 不支持的请求方法 */
    public static final String SYS_UNSUPPORTED_METHOD = "SYS_UNSUPPORTED_METHOD";

    /* *****************************  500 系统内部错误  **************************** */
    /* -------------  00 系统状态  ------------- */
    /** 系统繁忙 */
    public static final String SYS_INTERNAL_SERVER_ERROR = "SYS_INTERNAL_SERVER_ERROR";


    static {
        /* *****************************  200 请求成功  **************************** */
        MAP.put(SYS_SUCCESSFUL_REQUEST,new ResponseCode(HttpStatus.OK,2000000,"请求成功","Request successful"));

        /* *****************************  400 请求错误  **************************** */
        MAP.put(SYS_BODY_FORMAT_ERROR,new ResponseCode(HttpStatus.BAD_REQUEST,4000000,"请求body格式错误","Request body format error"));
        MAP.put(SYS_UNSUPPORTED_CONTENT_TYPE,new ResponseCode(HttpStatus.BAD_REQUEST,4000002,"不支持的Content-Type","Unsupported Content-Type"));
        MAP.put(COMMON_MISS_PARAMETER,new ResponseCode(HttpStatus.BAD_REQUEST,4000003,"丢失请求参数","Missing request parameters"));
        MAP.put(COMMON_INVALID_PARAM,new ResponseCode(HttpStatus.BAD_REQUEST,4000004,"无效的参数","Invalid parameter"));
        MAP.put(COMMON_VALIDATE_ERROR,new ResponseCode(HttpStatus.BAD_REQUEST,4000100,"未通过参数校验","Failed parameter verification"));
        MAP.put(COMMON_NOT_IN_ALLOWED_SCOPE,new ResponseCode(HttpStatus.BAD_REQUEST,4000101,"不在允许值范围内","Not in allowed scope"));

        /* *****************************  404 路由错误  **************************** */
        MAP.put(SYS_NOT_FOUND,new ResponseCode(HttpStatus.NOT_FOUND,4040000,"路由不存在","Route does not exist"));
        /* *****************************  405 不支持的请求方法  **************************** */
        MAP.put(SYS_UNSUPPORTED_METHOD,new ResponseCode(HttpStatus.METHOD_NOT_ALLOWED,4050000,"不支持的请求方法","Unsupported request method"));
        /* *****************************  500 系统内部错误  **************************** */
        MAP.put(SYS_INTERNAL_SERVER_ERROR,new ResponseCode(HttpStatus.INTERNAL_SERVER_ERROR,5000000,"系统繁忙","System error"));
    }

    /**
     * http状态码
     */
    private HttpStatus status;
    /**
     * 自定义状态码
     * 自定义状态码一共5位，前3位取http状态码，中间2位表示状态类型，后两位表示具体状态码
     * 状态类型（每个人根据自己需求进行追加）
     *  + 00：系统状态
     *  + 01：通用状态
     *  + 02：授权状态
     *  + 03：角色状态
     */
    private Integer code;
    /**
     * 中文message
     */
    private String chMsg;
    /**
     * 英文message
     */
    private String enMsg;
    /**
     * 字段名
     */
    private String field;

    private ResponseCode(HttpStatus status, Integer code, String chMsg, String enMsg) {
        this.status = status;
        this.code = code;
        this.chMsg = chMsg;
        this.enMsg = enMsg;
    }

    @SuppressWarnings("unused")
    public ResponseCode() {
    }

    public static ResponseCode defaultValidate(String msg){
        ResponseCode instance = instance(msg);
        if(instance == null) {
            instance = instance(COMMON_VALIDATE_ERROR);
            if(msg != null && !msg.isEmpty()) {
                instance.chMsg = msg;
                instance.enMsg = msg;
            }
        }
        return instance;
    }

    public static ResponseCode instance(String key){
        if (key == null){
            return null;
        }
        ResponseCode responseCode = MAP.get(key);
        if(responseCode == null){
            return null;
        }
        return JSONObject.parseObject(JSONObject.toJSONString(responseCode),ResponseCode.class);
    }

    public String getChMsg() {
        return ((field == null)? "" : field  + " : ")+ chMsg;
    }

    public String getEnMsg() {
        return ((field == null)? "" : field + " : ") + enMsg;
    }
}
