package com.liushiyao.java8.stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo {

    /*





     */
    private static List<Dish> menu;

    static {

        menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));
    }


    public static void main(String[] args) {

        List nameList = menu.stream()
            .map(Dish::getName)
            .map(String::length)
            .limit(3)
            .collect(Collectors.toList());
        System.out.println("nameList:" + nameList);


    }


    //流只能用一次
    @Test
    public void Test() {

        List<String> list = Arrays.asList(new String[]{"123", "456"});

        Stream stream = list.stream();
        stream.forEach(s -> System.out.println(s));
        stream.forEach(s -> System.out.println(s));//java.lang.IllegalStateException: stream has already been operated upon or closed


    }

    //筛选-filter
    @Test
    public void Test2() {

        List<Dish> vegetarianMenu = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        vegetarianMenu.forEach(dish -> System.out.println(dish.getName()));

    }

    //筛选各异元素-distinct
    @Test
    public void Test3() {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 1, 2, 3);
        numbers.stream().filter(integer -> integer % 2 == 0)
            .distinct()
            .forEach(System.out::println);

    }

    //截断流-limit
    //如果是顺序流，这返回前面顺序返回，如果是无序，则无序返回
    @Test
    public void Test4() {

        menu.stream().filter(dish -> dish.getCalories() > 300)
            .limit(3)
            .collect(Collectors.toList())
            .forEach(dish -> System.out.println(dish.getName()));

    }

    //跳过元素-skip
    @Test
    public void Test5() {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        numbers.stream().skip(2).forEach(System.out::println);

    }

    //映射-map
    @Test
    public void Test6() {

        //输出所有元素的字符串长度

        Map<Integer,String> map = new HashMap<>();

        map.put(1,"123");
        map.put(2,"456");
        List<Map<Integer,String>> list = new ArrayList<>();
        list.add(map);

        map = new HashMap<>();
        map.put(1,"789");
        map.put(2,"101112");

        list.add(map);

        System.out.println("list:"+list);


    }


}
