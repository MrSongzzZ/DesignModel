package com.proxy.dao;

public class UserDaoImpl implements UserDao{

    public void query() {
        System.out.println("假装查询数据库");
    }

    @Override
    public int sun(int a, int b) {
        return a + b;
    }
}
