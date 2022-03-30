package com.glodon.mycommunity.controller;

import com.glodon.mycommunity.entity.User;
import com.glodon.mycommunity.service.UserService;
import com.glodon.mycommunity.utils.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class LoginController implements CommunityConstant {

    @Autowired
    UserService userService;

    @RequestMapping(path = "/register",method = RequestMethod.GET)
    public String registerPage(){
        return "/site/register";
    }

    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public String login(){return "/site/login";}

    @RequestMapping(path = "/signup",method = RequestMethod.POST)
    public String signup(Model model, User user){
        Map<String,Object> result = userService.register(user);
        if(result == null || result.isEmpty()){
            model.addAttribute("msg","您已注册成功，牛客已向您的邮箱发送一封激活邮件，请尽快激活！");
            model.addAttribute("target","/index");
            return "/site/operate-result";
        }
        model.addAttribute("usernameMsg",result.get("usernameMsg"));
        model.addAttribute("passwordMsg",result.get("passwordMsg"));
        model.addAttribute("emailMsg",result.get("emailMsg"));
        return "/site/register";
    }
    //http://localhost:8080/community/userId/activationCode
    @RequestMapping(path = "/{userId}/{activationCode}",method = RequestMethod.GET)
    public String activate(Model model, @PathVariable("userId") int userId,
                           @PathVariable("activationCode") String activationCode){

        int result = userService.activate(userId,activationCode);
        if(result == ACTIVATE_SUCCESS){
            model.addAttribute("msg","您已激活成功！");
            model.addAttribute("target","/login");
            return "/site/operate-result";
        }
        else if(result == ACTIVATE_REPEAT){
            model.addAttribute("msg","操作无效，重复激活！");
            model.addAttribute("target","/index");
            return "/site/operate-result";
        }else{
            model.addAttribute("msg","激活失败，您提供的激活码不正确！");
            model.addAttribute("target","/index");
            return "/site/operate-result";
        }

    }
}
