package com.technology.model;

import java.util.Date;

import lombok.Value;

@Value
public class User {
  Long id;
  String name;
  String password;
  Date date;
}
