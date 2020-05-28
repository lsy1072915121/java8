package com.liushiyao.java8.lambda;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 * @Author: liushiyao
 * @Date: 2019/6/27 15:10
 */
//双冒号符的使用
public class DoubleColonDemo {

    //1.双冒号符的基本使用
    public static void main(String []a){

        List<String> list = Arrays.asList("1","2","3","4");
        //传统方式
        for (String value:list){
            System.out.print(value+" ");
        }
        System.out.println();
        //lambda
        list.forEach((value) -> System.out.print(value+" "));
        System.out.println();
        //双冒号符的使用
        list.forEach(System.out::println);

    }

    //2.拓展:把方法当做参数传到stream内部，使stream的每个元素都传入到该方法里面执行一下。
    @Test
    public void DoubleColonTest(){

        List<Integer> list = Arrays.asList(1,2,3,4);
        list.forEach(DoubleColonDemo::red);

    }


    public static int add(Integer num){
        System.out.println(num);
        return 1+num;
    }
    public static int red(Integer num){
        System.out.println(num);
        return --num;
    }


}
