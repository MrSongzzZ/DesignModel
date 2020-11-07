package com.proxy.proxy;

import com.proxy.dao.UserDaoImpl;

public class UserDaoLogImpl extends UserDaoImpl {

    @Override
    public void query() {
        System.out.println("-----UserDaoLogImpl----");
        super.query();
    }
}
