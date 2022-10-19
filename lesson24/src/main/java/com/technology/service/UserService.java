package com.technology.service;

import java.util.List;

import com.technology.model.User;
import com.technology.repository.UserRepository;

public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void addUser(String name, String password) {
    if (userRepository.getUser(name).isPresent()) {
      throw new RuntimeException("User already exists");
    }
    userRepository.addUser(name, password);
  }

  public boolean validate(String name, String password) {
    return userRepository.validate(name, password);
  }

  public List<User> findUsers() {
    return userRepository.findUsers();
  }

  public List<User> filterUsersByName(String parameter) {
    return userRepository.filterUsersByName(parameter);
  }
}
