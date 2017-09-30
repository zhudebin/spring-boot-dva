package com.blue.bird.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by jim on 2017/9/27.
 * code 命名规则
 * 1 正常
 * 2 业务错误
 * 3 系统错误
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor
@Getter
public enum ViewCodeEnum {

    SUCCESS("1001", "成功"),
    OPT_ERROR("2001", "操作异常"),
    VERIFY_CODE_ERROR("2002", "验证码错误"),
    PARAMS_ERROR("2003","参数错误"),
    SYSTEM_ERROR("3001", "系统异常");

    private String code;
    private String info;
}
