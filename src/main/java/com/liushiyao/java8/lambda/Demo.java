package com.liushiyao.java8.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Demo {


    /*

        从比较器中体会lambda的魅力


     */
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(100);
        list.add(112);
        list.add(50);
        list.add(20);

        //1.传统
//        list.sort(new Compare());
        //2.匿名内部类
        /*list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2 < 0?-1 :((o1 == o2)? 0:1);
            }
        })*/;
        //3.lambda
//        list.sort((o1, o2) -> o1 - o2 < 0?-1 :((o1 == o2)? 0:1));
        //4.::
        list.sort(Integer::compareTo);

        System.out.println("list:"+list);


    }


    public static class Compare implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2 < 0?-1 :((o1 == o2)? 0:1);
        }
    }


}
