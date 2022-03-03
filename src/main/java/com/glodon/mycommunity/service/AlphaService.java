package com.glodon.mycommunity.service;

import com.glodon.mycommunity.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//@Scope("prototype")
public class AlphaService {
    @Autowired
    AlphaDao alphaDao;

    public void find(){
        alphaDao.select();
    }

    public AlphaService(){
        System.out.println("实例化AlphaService");
    }

    //构造器之后执行初始化方法
    @PostConstruct
    public void init(){
        System.out.println("初始化AlphaService");
    }

    //bean销毁之前最后执行的方法
    @PreDestroy
    public void destory(){
        System.out.println("销毁AlphaService");
    }
}
