package com.liushiyao.java8.methodRefer;

/**
 * @Author: liushiyao
 * @Date: 2019/3/8 13:10
 */
public class Man {

  private String name;

  public Man(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Man name=" + name;
  }
}
