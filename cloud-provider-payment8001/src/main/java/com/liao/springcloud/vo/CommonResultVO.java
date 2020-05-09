package com.liao.springcloud.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * json封装体
 *
 * @author huangzuboshao
 * @date 2020/5/9 13:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResultVO<T> {
    private String code;
    private String message;
    private T data;

    public CommonResultVO(String code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }
}

