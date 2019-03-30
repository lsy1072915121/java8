package com.liushiyao.java8.methodRefer;

/**
 * @Author: liushiyao
 * @Date: 2019/3/8 12:47
 */
@FunctionalInterface
public interface Person<T> {

  T run(String s);
//  void show();

}
