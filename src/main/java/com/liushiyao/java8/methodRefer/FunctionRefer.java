package com.liushiyao.java8.methodRefer;

/**
 * @Author: liushiyao
 * @Date: 2019/3/8 12:37
 */
//方法引用
public class FunctionRefer {

  public static void main(String[] a) {
    String string = "abDFF";
    /*Person<String> person = string::toUpperCase;
    System.out.println(person.run());*/
    /*Person<String> person = (String s) -> s.toUpperCase();
    System.out.println(person.run("123"));*/
    Person<Man> person = Man::new;
    Man man = person.run("123");
    System.out.println(man);

  }
}
