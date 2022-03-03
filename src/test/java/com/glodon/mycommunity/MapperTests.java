package com.glodon.mycommunity;

import com.glodon.mycommunity.dao.UserMapper;
import com.glodon.mycommunity.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = MycommunityApplication.class)
public class MapperTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void selectUserTest(){
        User user = userMapper.selectUserById(149);
        System.out.println(user);

        user = userMapper.selectUserByName("niuke");
        System.out.println(user);

        user = userMapper.selectUserByEmail("nowcoder149@sina.com");
        System.out.println(user);
    }

    @Test
    public void insertUserTest(){
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("abcd");
        user.setEmail("123@qq.com");
        user.setHeaderUrl("http://images.nowcoder.com/head/200t.png");
        user.setCreateTime(new Date());
        int rows = userMapper.insertUser(user);
        System.out.println(rows);
    }
}
