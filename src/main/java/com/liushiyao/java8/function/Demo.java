package com.liushiyao.java8.function;

//内置函数式接口

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Demo {


    //Consumer T -> void
    @Test
    public void Test(){


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
    public void Test2(){

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
    public void Test3(){

        Function<Integer,String> function = integer -> "value:" + integer;
        System.out.println(function.apply(100));

    }



}
