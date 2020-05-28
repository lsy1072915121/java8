package com.liushiyao.java8.methodRefer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class Demo {

    //静态方法
    @Test
    public void Test(){


//        Function<String,String> function = (String s) -> Option.run(s);
        Function<String,String> function = Option::run;
        System.out.println(function.apply("abc"));

    }

    //指向任意类型实例方法的方法引用
    @Test
    public void Test2(){

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

//        list.forEach((String s) -> System.out.print(s));
        list.forEach(System.out::print);

    }

    //指向现有对象的实例方法的方法引用
    @Test
    public void Test3(){


        Option option = new Option();
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        list.forEach(option::print);


    }

    //构造函数引用
    @Test
    public void Test4(){

        Function<Integer,Option> function = Option::new;

//        Function<Integer,Option> function = (Integer i) -> new Option(i);


        Option option = function.apply(123);

        System.out.println("option:"+option.getNum());


    }
    @Test
    public void Test5(){

        Set<String> set = new HashSet<>();
        set.add("123");
        set.add("456");
        boolean b = set.remove("456");
        System.out.println("b:"+b);
        System.out.println("set:"+set);

        String regIp = "";
        String ip = regIp.substring(0, regIp.lastIndexOf("."));
        System.out.println("ip:"+regIp);

    }


}


class Option{

    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public static String run(String value){

        return value +" run...";

    }

    public void print(String content){
        System.out.println("内容："+content);
    }

    public Option() {
    }

    public Option(int num) {
        this.num = num;
    }
}

