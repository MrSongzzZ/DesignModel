package com.proxy.proxy;

import com.proxy.dao.UserDaoImpl;

public class UserDaoTimerImpl extends UserDaoImpl {

    @Override
    public void query() {
        System.out.println("-----UserDaoTimerImpl----");
        super.query();
    }
}
