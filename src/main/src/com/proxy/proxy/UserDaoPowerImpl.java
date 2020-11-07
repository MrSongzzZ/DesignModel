package com.proxy.proxy;

import com.proxy.dao.UserDaoImpl;

public class UserDaoPowerImpl extends UserDaoImpl {

    @Override
    public void query() {
        System.out.println("-----powerUserDaoPowerImpl----");
        super.query();
    }
}
