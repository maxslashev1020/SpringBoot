package com.example.springboot.dao;

import com.example.springboot.entity.User;

import java.util.List;

public interface UserDao {

    void updateUser(User user);

    void removeUserById(long id);

    User getUserById(long id);

    List<User> getAllUsers();
    public void addUser(User user);
    public User getUserByName(String username);
}
