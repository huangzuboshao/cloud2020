package com.liao.springcloud.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.liao.springcloud.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 * @author huangzuboshao
 * @date 2020/4/20 16:37
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 自定义mapper
     */
    List<User> selectByLiao();

    @Select("select * from sys_user ${ew.customSqlSegment}")
    List<User> mySelectList(@Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
