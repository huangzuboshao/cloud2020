package com.liao.springcloud.vo;

import com.liao.springcloud.constant.ResultStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * json封装体
 *
 * @author huangzuboshao
 * @date 2020/5/9 13:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResultVO implements Serializable {

    /**
     * 状态码
     */
    private String code;
    /**
     * 状态信息
     */
    private String message;

    private Object data;

    public static CommonResultVO success() {
        CommonResultVO commonResultVO = new CommonResultVO();
        BeanUtils.copyProperties(ResultStatusEnum.SUCCESS,commonResultVO);
        return commonResultVO;
    }

    public static CommonResultVO success(Object data) {
        CommonResultVO commonResultVO = new CommonResultVO();
        BeanUtils.copyProperties(ResultStatusEnum.SUCCESS,commonResultVO);
        commonResultVO.setData(data);
        return commonResultVO;
    }

    public static CommonResultVO failure(ResultStatusEnum resultStatusEnum) {
        CommonResultVO commonResultVO = new CommonResultVO();
        BeanUtils.copyProperties(resultStatusEnum,commonResultVO);
        return commonResultVO;
    }

    public static CommonResultVO failure(ResultStatusEnum resultStatusEnum, Object data) {
        CommonResultVO commonResultVO = new CommonResultVO();
        BeanUtils.copyProperties(resultStatusEnum,commonResultVO);
        commonResultVO.setData(data);
        return commonResultVO;
    }
}

