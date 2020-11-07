package com.proxy.proxy1;

import com.proxy.dao.UserDao;

public class UserDaoTime implements UserDao {

    UserDao dao;

    public UserDaoTime(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public void query() {
        System.out.println("-----time-----");
        dao.query();
    }

    @Override
    public int sun(int a, int b) {
        return 0;
    }
}
