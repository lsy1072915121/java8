package com.liushiyao.java8.demo;


import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;

public class Demo {




    /*
            1.lambda表达式

            2.Stream


     */
    public static void main(String[] args) {

        if (args.length != 1){
            System.out.println("please input method name");
            return;
        }
        try {
            Demo.class.getDeclaredMethod(args[0]).invoke(new Demo());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.out.println("no matcher method");
            e.printStackTrace();
        }


    }

    //lambda演示
    @Test
    public void Test(){


        //jdk1.8以前
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程启动");
            }
        });

        //jdk1.8之后
        Thread thread1 = new Thread(() ->{
            System.out.println("lambda线程启动");
        });

        thread.start();
        thread1.start();

    }


    //在函数式接口(@FunctionalInterface)上使用lambda表达式
    @Test
    public void Test2(){

        List<Integer> list = Arrays.asList(10,20,31,5,3,81,50);
        Comparator<Integer> comparator = (o1, o2) -> o1-o2;             //正序
        System.out.println("排序前："+list);
        list.sort(comparator);
        System.out.println("排序后:"+list);


    }

    //Consumer T -> void
    @Test
    public void Test3(){


        List<Integer> list = Arrays.asList(9,10,11,23,1,3,31);
        list.forEach(System.out::println);

        /*
        List<Integer> list = Arrays.asList(9,10,11,23,1,3,31);
        for (Integer num:list){
            System.out.println(num);
        }
        */

    }

    //Predicate T -> boolean
    @Test
    public void Test4(){

        Predicate<Integer> predicate = integer -> integer > 10;
        List<Integer> list = Arrays.asList(9,10,11,23,1,3,31);
        list.stream()
            .filter(predicate)
            .forEach(System.out::println);

        /*
        List<Integer> list = Arrays.asList(9,10,11,23,1,3,31);
        for (Integer num:list){
            if(num > 10){
                System.out.println(num);
            }
        }
        */


    }

    //Function T -> R
    @Test
    public void Test5(){

        Function<Integer,String> function = integer -> "value:" + integer;
        System.out.println(function.apply(100));

    }






}
