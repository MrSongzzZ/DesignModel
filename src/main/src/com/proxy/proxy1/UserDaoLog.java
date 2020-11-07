package com.proxy.proxy1;

import com.proxy.dao.UserDao;

public class UserDaoLog implements UserDao {

    UserDao dao;

    public UserDaoLog(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public void query() {
        System.out.println("-----log-----");
        dao.query();
    }

    @Override
    public int sun(int a, int b) {
        return 0;
    }
}
