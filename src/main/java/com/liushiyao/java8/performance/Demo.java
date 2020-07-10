package com.liushiyao.java8.performance;

import org.junit.Test;

import java.util.*;

public class Demo {


    @Test
    public void Test(){

        int total = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            total += i;
        }
        System.out.println("use time(ms):"+(System.currentTimeMillis() - start));

    }

    @Test
    public void Test2(){

        int total = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            total += i;
        }
        System.out.println("use time(ms):"+(System.currentTimeMillis() - start));

    }

    @Test
    public void Test3(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("list:"+list.subList(1,1));
    }

    @Test
    public void Test4(){

        //排序
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(21);
        list.add(97);
        list.add(32);
        list.add(40);
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println("list:"+list);


    }

}
