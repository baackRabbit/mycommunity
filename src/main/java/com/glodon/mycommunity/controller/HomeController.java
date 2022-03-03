package com.glodon.mycommunity.controller;

import com.glodon.mycommunity.dao.DiscussPostMapper;
import com.glodon.mycommunity.entity.DiscussPost;
import com.glodon.mycommunity.entity.Page;
import com.glodon.mycommunity.entity.User;
import com.glodon.mycommunity.service.DiscussPostService;
import com.glodon.mycommunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    DiscussPostService discussPostService;

    @Autowired
    UserService userService;

    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String showDiscussPostByPage(Model model, Page page){
        //方法调用前,springmvc会自动实例化Model和Page，并将page注入model
        //所以，thymeleaf模板可直接使用page对象
        int rows = discussPostService.selectDiscussPostRows(0);
        page.setRows(rows);
        page.setPath("/index");
        List<DiscussPost> discussPosts = discussPostService.selectDiscussPosts(0,page.getOffset(),page.getLimit());
        List<Map<String,Object>> discussPostMaps = new ArrayList<>();
        if(discussPosts != null){
            for(DiscussPost discussPost:discussPosts){
                Map<String,Object> postMap = new HashMap<>();
                User user = userService.selectUserById(discussPost.getUserId());
                postMap.put("post",discussPost);
                postMap.put("user",user);
                discussPostMaps.add(postMap);
            }
        }
        model.addAttribute("discussPosts",discussPostMaps);
        return "index";
    }
}
