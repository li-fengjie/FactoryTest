package com.life.dao;

import com.life.bean.User;

public interface UserDao {
    public void addUser(User user);
    public User findUserByUsername(String username);
}
