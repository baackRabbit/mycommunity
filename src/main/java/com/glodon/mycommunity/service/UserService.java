package com.glodon.mycommunity.service;

import com.glodon.mycommunity.dao.UserMapper;
import com.glodon.mycommunity.entity.User;
import com.glodon.mycommunity.utils.CommunityConstant;
import com.glodon.mycommunity.utils.MailUtil;
import com.glodon.mycommunity.utils.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UserService implements CommunityConstant {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RandomUtil randomUtil;

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${community.path.domain}")
    private String domain;

    public User selectUserById(int userId){
        return userMapper.selectUserById(userId);
    }

    public Map<String,Object> register(User user){
        Map<String,Object> map = new HashMap<>();

        //验证参数不为空
        if(user == null){
            throw new IllegalArgumentException("参数不能为空!");
        }

        if(StringUtils.isBlank(user.getUsername())){
            map.put("usernameMsg","账号不能为空!");
            return map;
        }

        if(StringUtils.isBlank(user.getPassword())){
            map.put("passwordMsg","密码不能为空!");
            return map;
        }

        if(StringUtils.isBlank(user.getEmail())){
            map.put("emailMsg","邮箱不能为空!");
            return map;
        }

        //验证参数不重复
        if(userMapper.selectUserByName(user.getUsername()) != null){
            map.put("usernameMsg","该账号已被注册！");
            return map;
        }

        if(userMapper.selectUserByEmail(user.getEmail()) != null){
            map.put("emailMsg","该邮箱已被注册！");
            return map;
        }

        user.setSalt(randomUtil.generateUUID().substring(0,5));
        user.setPassword(user.getPassword()+user.getSalt());
        user.setType(0);
        user.setStatus(0);
        user.setActivationCode(randomUtil.generateUUID());
        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png",new Random().nextInt(1000)));
        user.setCreateTime(new Date());
        //注册用户
        userMapper.insertUser(user);

        //给用户发送激活邮件
        Context context = new Context();
        context.setVariable("email",user.getEmail());
        //http://localhost:8080/community/userId/activationCode
        context.setVariable("url",domain+contextPath+"/"+user.getId()+"/"+user.getActivationCode());
        String content = templateEngine.process("/mail/activation",context);
        mailUtil.sendEmail(user.getEmail(),"牛客网-激活账号",content);

        return map;
    }

    //用户状态激活
    public int activate(int userId,String activationCode){
        User user = userMapper.selectUserById(userId);
        if(user == null){
            throw new IllegalArgumentException("用户ID不存在！");
        }
        if(user.getStatus() == 1){
            return ACTIVATE_REPEAT;
        }else if(user.getActivationCode().equals(activationCode)){
            userMapper.updateStatusById(userId,1);
            return ACTIVATE_SUCCESS;
        }else{
            return ACTIVATE_FAIL;
        }
    }
}
