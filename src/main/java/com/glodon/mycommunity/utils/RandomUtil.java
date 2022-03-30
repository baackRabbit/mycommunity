package com.glodon.mycommunity.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@Component
public class RandomUtil {

    //生成随机字符串
    public String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    //MD5加密
    public String md5(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }
}
