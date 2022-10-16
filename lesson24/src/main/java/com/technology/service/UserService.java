package com.technology.service;

import com.technology.model.User;
import com.technology.repository.UserRepository;
import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean addUser(String name, String password) {
         return userRepository.addUser(name, password);
    }

    public boolean validate(String name, String password) {
        return userRepository.validate(name, password);
    }

    public List<User> findUsers() {
        return userRepository.findUsers();
    }

    public List<User> filterUsersByQueryParameter(String name, List<User> users) {
        return userRepository.filterUsersByName(name, users);
    }
}
