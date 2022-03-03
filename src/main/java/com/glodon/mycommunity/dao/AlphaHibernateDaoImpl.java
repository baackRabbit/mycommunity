package com.glodon.mycommunity.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
@Primary
public class AlphaHibernateDaoImpl implements AlphaDao{

    @Override
    public void select() {
        System.out.println("hibernate");
    }
}
