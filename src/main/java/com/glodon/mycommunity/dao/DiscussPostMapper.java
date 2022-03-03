package com.glodon.mycommunity.dao;

import com.glodon.mycommunity.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    //@Param用于给参数取别名
    //如果只有一个参数，且要在<if>内使用，则必需加别名
    public int selectDiscussPostRows(@Param("userId") int userId);

    public List<DiscussPost> selectDiscussPosts(int userId,int offset,int limit);

}
