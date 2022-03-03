package com.glodon.mycommunity;

import com.glodon.mycommunity.entity.DiscussPost;
import com.glodon.mycommunity.service.DiscussPostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = MycommunityApplication.class)
public class DiscussPostServiceTests {

    @Autowired
    DiscussPostService discussPostService;

    @Test
    public void selectDiscussPost(){
        List<DiscussPost> posts = discussPostService.selectDiscussPosts(0,0,10);
        for(DiscussPost post:posts){
            System.out.println(post);
        }
        int rows = discussPostService.selectDiscussPostRows(0);
        System.out.println(rows);
    }
}
