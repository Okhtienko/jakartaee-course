package com.technology.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
  private Long id;
  private String name;
  private String password;
  private Date date;

  public User(Long id, String name, String password, Date date) {
    this.id = id;
    this.name = name;
    this.password = password;
    this.date = date;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
