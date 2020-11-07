package com.proxy.test;

import com.proxy.dao.UserDao;
import com.proxy.dao.UserDaoImpl;
import com.proxy.proxy.ProxyUtil;
import com.proxy.proxy.UserDaoLogAndTimeIImpl;
import com.proxy.proxy.UserDaoLogImpl;
import com.proxy.proxy1.UserDaoLog;
import com.proxy.proxy1.UserDaoTime;

public class Test {
    /**
     * 开闭原则 单一职责
     * @param args
     */
    public static void main(String[] args) {

        //继承
//        UserDaoImpl dao = new UserDaoImpl();
//        UserDaoImpl dao = new UserDaoLogImpl();
//        UserDaoImpl dao = new UserDaoLogAndTimeIImpl();

        //聚合
        UserDao target = new UserDaoImpl();
//        UserDao target = new UserDaoLog(new UserDaoImpl());
//        UserDao dao = new UserDaoTime(target);
        UserDao dao = (UserDao) ProxyUtil.newInstance(target);
        dao.query();
        System.out.println(dao.sun(12, 22));
    }
}
