package com.liao.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/4/20 16:16
 */
@Data
@TableName("sys_user")
public class User {

    @TableId("user_id")
    private Long id;

    private String account;

    private String name;

    private Integer age;
}
