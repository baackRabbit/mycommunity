package com.glodon.mycommunity.dao;

import com.glodon.mycommunity.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public User selectUserById(int id);

    public User selectUserByName(String username);

    public User selectUserByEmail(String email);

    public int insertUser(User user);

    public int updateStatusById(int id,int status);

    public int updatePasswordById(int id,String password);

    public int updateHeaderUrlById(int id,String headerUrl);
}
