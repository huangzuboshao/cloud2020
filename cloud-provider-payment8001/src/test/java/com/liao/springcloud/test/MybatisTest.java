package com.liao.springcloud.test;

import com.liao.springcloud.dao.UserMapper;
import com.liao.springcloud.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * TODO..
 *
 * @author huangzuboshao
 * @date 2020/5/9 11:07
 */
@MapperScan
public class MybatisTest {
    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectByLiao();
        if (!CollectionUtils.isEmpty(users)) {
            users.forEach(System.out::println);
        }
        sqlSession.commit();
        sqlSession.flushStatements();
        sqlSession.close();
    }
}
