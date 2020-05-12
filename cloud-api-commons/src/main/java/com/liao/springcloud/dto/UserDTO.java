package com.liao.springcloud.dto;

import lombok.Data;

/**
 *
 * @author huangzuboshao
 * @date 2020/5/11 15:22
 */
@Data
public class UserDTO {

    private Long id;

    private String account;

    private String name;

    private Integer age;


    private String serverPort;
}
