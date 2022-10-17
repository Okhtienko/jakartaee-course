package com.technology.repository;

import com.technology.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void addUser(String name, String password);
    boolean validate(String name, String password);
    List<User> findUsers();

    Optional<User> getUser(String name);

    List<User> filterUsersByQueryParameter(String parameter);
}
