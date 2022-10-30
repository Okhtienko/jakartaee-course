package com.technology.model;

import java.util.Date;

import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@NonFinal
public class User {
  Long id;
  String name;
  String password;
  Date date;
}
