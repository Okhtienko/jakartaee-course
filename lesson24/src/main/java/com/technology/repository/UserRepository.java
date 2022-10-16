package com.technology.repository;

import com.technology.model.User;

import java.util.List;

public interface UserRepository {
    boolean addUser(String name, String password);
    boolean validate(String name, String password);
    List<User> findUsers();
    List<User> filterUsersByName(String name, List<User> users);
}
