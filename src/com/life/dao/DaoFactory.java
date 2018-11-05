package com.life.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {

    private static Properties props = null;

    static {
        try {
            InputStream inputStream = DaoFactory.class.getClassLoader().getResourceAsStream("dao.properties");
            props = new Properties();
            props.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static UserDao getUserDao() {
        String daoClassName = props.getProperty("com.life.dao.UserDao");
        try {
            Class clazz = Class.forName(daoClassName);
            return  (UserDao)clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
