package com.proxy.proxy;

public class UserDaoLogAndTimeIImpl extends UserDaoTimerImpl{

    @Override
    public void query() {
        System.out.println("-----log-----");
        super.query();
    }
}
