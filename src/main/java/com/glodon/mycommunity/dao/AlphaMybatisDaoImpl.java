package com.glodon.mycommunity.dao;

import org.springframework.stereotype.Repository;

@Repository("alphaMybatis")
public class AlphaMybatisDaoImpl implements AlphaDao{

    @Override
    public void select() {
        System.out.println("mybatis");
    }
}
