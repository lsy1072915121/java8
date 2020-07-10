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
            new Dish("猪肉", false, 800, Dish.Type.MEAT),
            new Dish("牛肉", false, 700, Dish.Type.MEAT),
            new Dish("鸡肉", false, 400, Dish.Type.MEAT),
            new Dish("薯条", true, 530, Dish.Type.OTHER),
            new Dish("白饭", true, 350, Dish.Type.OTHER),
            new Dish("水果", true, 120, Dish.Type.OTHER),
            new Dish("披萨", true, 550, Dish.Type.OTHER),
            new Dish("虾", false, 300, Dish.Type.FISH));
    }

    //返回热量低于300卡路里的菜肴名称,并按卡路里升序排序
    //java8之前
    @Test
    public void jdk7(){

        //过滤
        List<Dish> below400List = new ArrayList<>();
        for (Dish dish:menu){
            if(dish.getCalories() < 300){
                below400List.add(dish);
            }
        }
        //排序
        below400List.sort(new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return o1.getCalories() - o2.getCalories();
            }
        });

        //拿菜肴的名字
        List<String> nameList = new ArrayList<>();
        for (Dish dish:below400List){
            nameList.add(dish.getName());
        }
        System.out.println("nameList:"+nameList);


    }


    //返回热量低于300卡路里的菜肴名称,并按卡路里升序排序
    //java8之后
    @Test
    public void jdk8() {

        List<String> nameList = menu.stream()
            .filter(d -> d.getCalories() < 300)
            .sorted(Comparator.comparing(Dish::getCalories))
            .map(Dish::getName)
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

    //findFirst
    @Test
    public void Test7(){

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
            someNumbers.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0) .findFirst(); // 9
        System.out.println(firstSquareDivisibleByThree);


    }

    //流中的元素是否都能匹配给定的谓词
    @Test
    public void Test8(){

        boolean isHealthy = menu.stream()
            .allMatch(d -> d.getCalories() < 1000);

        System.out.println("isHealthy:"+isHealthy);
    }
    //流中的元素是否存在匹配给定的谓词
    @Test
    public void Test9(){

        boolean hasHealthy = menu.stream()
            .anyMatch(d -> d.getCalories() < 1000);

        System.out.println("hasHealthy:"+hasHealthy);
    }

    //reduce归于
    @Test
    public void Test10(){


        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
        Optional<Integer> sum = list.stream().reduce(Integer::sum);
        System.out.println("sum:"+sum.get());


    }

    @Test
    public void Test11(){
        String name ="sfs.xvxdfdf.xls";
        System.out.println(name.substring(name.lastIndexOf(".")+1));


    }

}
