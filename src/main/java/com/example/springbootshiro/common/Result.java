package com.example.springbootshiro.common;

import java.util.HashMap;

/**
 * @author carzy.
 * @date 14:26 2018/12/25
 */
public class Result extends HashMap<String, Object> {

    /**
     * 操作名称
     */
    public static final String KEY_OPER = "oper";
    /**
     * 操作是否成功
     */
    public static final String KEY_SUCC = "succ";
    /**
     * 操作状态码
     */
    public static final String KEY_CODE = "code";
    /**
     * 结果的信息
     */
    public static final String KEY_MSG = "msg";

    /**
     * 结果数据
     */
    public static final String KEY_DATA = "data";

    /**
     * 默认值
     */
    public static final String DEFAULT_OPER_VAL = "default";
    public static final int DEFAULT_SUCC_CODE = 1;
    public static final int DEFAULT_FAIL_CODE = -1;
    public static final String DEFAULT_SUCC_MSG = "ok";
    public static final String DEFAULT_FAIL_MSG = "fail";

    /**
     * 无参构造：操作成功返回的响应信息
     */
    public Result() {
        this.put(KEY_OPER, DEFAULT_OPER_VAL);
        this.put(KEY_SUCC, true);
        this.put(KEY_CODE, DEFAULT_SUCC_CODE);
        this.put(KEY_MSG, DEFAULT_SUCC_MSG);
    }

    /**
     * 操作成功返回的响应信息
     */
    public Result(String oper) {
        this.put(KEY_OPER, oper);
        this.put(KEY_SUCC, true);
        this.put(KEY_CODE, DEFAULT_SUCC_CODE);
        this.put(KEY_MSG, DEFAULT_SUCC_MSG);
    }

    /**
     * 全参构造
     *
     * @param operate 操作名称
     * @param success 是否成功
     * @param code    状态码
     * @param msg     消息
     * @param data    有效数据
     */
    public Result(String operate, boolean success, int code, String msg, Object data) {
        this.put(KEY_OPER, operate);
        this.put(KEY_SUCC, success);
        this.put(KEY_CODE, code);
        this.put(KEY_MSG, msg);
        if (data != null) {
            this.put(KEY_DATA, data);
        }
    }

    /////////////////////// 各种简便的静态工厂方法 ///////////////////////

    ////// 操作成功的：

    public static Result succ() {
        return new Result();
    }

    public static Result succ(String operate) {
        return new Result(operate, true, DEFAULT_SUCC_CODE, DEFAULT_SUCC_MSG, null);
    }

    public static Result succ(String operate, String message) {
        return new Result(operate, true, DEFAULT_SUCC_CODE, message, null);
    }

    public static Result succ(String operate, Object data) {
        return new Result(operate, true, DEFAULT_SUCC_CODE, DEFAULT_SUCC_MSG, data);
    }

    public static Result succ(String operate, String dataKey, Object dataVal) {
        return new Result(operate, true, DEFAULT_SUCC_CODE, DEFAULT_SUCC_MSG, null)
                .data(dataKey, dataVal);
    }

    ////// 操作失败的：

    public static Result fail() {
        return new Result(DEFAULT_OPER_VAL, false, DEFAULT_FAIL_CODE, DEFAULT_FAIL_MSG, null);
    }

    public static Result fail(String operate) {
        return new Result(operate, false, DEFAULT_FAIL_CODE, DEFAULT_FAIL_MSG, null);
    }

    public static Result fail(String operate, String message) {
        return new Result(operate, false, DEFAULT_FAIL_CODE, message, null);
    }

    public static Result fail(String operate, Object data) {
        return new Result(operate, false, DEFAULT_FAIL_CODE, DEFAULT_FAIL_MSG, data);
    }

    public static Result fail(String operate, String dataKey, Object dataVal) {
        return new Result(operate, false, DEFAULT_FAIL_CODE, DEFAULT_FAIL_MSG, null)
                .data(dataKey, dataVal);
    }

    ////// 操作动态判定成功或失败的：

    public static Result result(String operate, boolean success) {
        return new Result(
                operate,
                success,
                (success ? DEFAULT_SUCC_CODE : DEFAULT_FAIL_CODE),
                (success ? DEFAULT_SUCC_MSG : DEFAULT_FAIL_MSG),
                null);
    }

    public static Result result(String operate, boolean success, Object data) {
        return new Result(
                operate,
                success,
                (success ? DEFAULT_SUCC_CODE : DEFAULT_FAIL_CODE),
                (success ? DEFAULT_SUCC_MSG : DEFAULT_FAIL_MSG),
                data);
    }

    public static Result result(String operate, boolean success, String dataKey, Object dataVal) {
        return new Result(
                operate,
                success,
                (success ? DEFAULT_SUCC_CODE : DEFAULT_FAIL_CODE),
                (success ? DEFAULT_SUCC_MSG : DEFAULT_FAIL_MSG),
                null)
                .data(dataKey, dataVal);
    }

    /////////////////////// 各种链式调用方法 ///////////////////////

    /**
     * 设置操作名称
     */
    public Result oper(String operate) {
        this.put(KEY_OPER, operate);
        return this;
    }

    /**
     * 设置操作结果是否成功的标记
     */
    public Result succ(boolean success) {
        this.put(KEY_SUCC, success);
        return this;
    }

    /**
     * 设置操作结果的代码
     */
    public Result code(int code) {
        this.put(KEY_CODE, code);
        return this;
    }

    /**
     * 设置操作结果的信息
     */
    public Result msg(String message) {
        this.put(KEY_MSG, message);
        return this;
    }

    /**
     * 设置操作返回的数据
     */
    public Result data(Object dataVal) {
        this.put(KEY_DATA, dataVal);
        return this;
    }

    /**
     * 设置操作返回的数据，数据使用自定义的key存储
     */
    public Result data(String dataKey, Object dataVal) {
        this.put(dataKey, dataVal);
        return this;
    }
}
