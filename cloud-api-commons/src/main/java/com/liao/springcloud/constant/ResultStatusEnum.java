package com.liao.springcloud.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回状态码
 *
 * @author huangzuboshao
 * @date 2020/5/9 13:38
 */
@AllArgsConstructor
@Getter
public enum ResultStatusEnum {

    //错误产生来源分为 A/B/C，
    // A 表示错误来源于用户，比如参数错误，用户安装版本过低，用户支付超时等问题；
    // B 表示错误来源于当前系统，往往是业务逻辑出错，或程序健壮性差等问题；
    // C 表示错误来源于第三方服务，比如 CDN 服务出错，消息投递超时等问题

    /**
     * 成功状态码
     */
    SUCCESS("00000", "成功"),
    PARAM_IS_INVALID("A01xx", "参数错误");

    /**
     * 状态码
     */
    private String code;
    /**
     * 状态信息
     */
    private String message;
}
