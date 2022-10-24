package com.technology.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
  private Long id;
  private String name;
  private String password;
  private Date date;
}
