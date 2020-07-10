package com.liushiyao.java8.performance;

import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import com.github.houbb.junitperf.core.report.impl.ConsoleReporter;
import com.github.houbb.junitperf.core.report.impl.HtmlReporter;
import fai.comm.util.Sys;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class SumDemo {

    private static final int NUMBER = 4_000_000;

    public static List<User> getUserList(int number){

        List<User> userList = new ArrayList<>();
        for (int i = 0; i < number; i++) {

            User user = new User();
            user.setId(2020+(i % 10));
            user.setName("我是"+user.getId());
            user.setScore(70+new Random().nextInt(30));
            userList.add(user);
        }

        return userList;

    }


    //初始化测试数据,排除获取数据的时间干扰
    static List<User> userList = getUserList(NUMBER);
    //需求：分别统计10个用户对应的分数总和，并输出
    //测试参数：1s预热时间，运行2s，计算平均运行时间

    //foreach
    @Test
    @JunitPerfConfig(threads = 1, warmUp = 1000, duration = 2000,reporter = HtmlReporter.class)
    public void forEachDemo(){


        Map<String,Integer> sumMap = new HashMap<>();
        for (User user:userList){
            Integer sum = sumMap.get(user.getName());
            if(sum == null){
                sumMap.put(user.getName(),user.getScore());
                continue;
            }
            sum += user.getScore();
            sumMap.put(user.getName(),sum);
        }
        System.out.println("sum:"+sumMap);


    }

    //stream
    @Test
    @JunitPerfConfig(threads = 1, warmUp = 1000, duration = 2000,reporter = HtmlReporter.class)
    public void streamDemo(){

        //按分数从大到小排序
        Map<String, Integer> sumMap = userList
                .stream()
                .collect(Collectors.groupingBy(User::getName,Collectors.summingInt(User::getScore)));
        System.out.println("sumMap:"+sumMap);
    }

    //parallelStream
    @Test
    @JunitPerfConfig(threads = 1, warmUp = 1000, duration = 2000,reporter = HtmlReporter.class)
    public void parallelStreamDemo(){

        //按分数从大到小排序
        Map<String, Integer> sumMap = userList
                .parallelStream()
                .collect(Collectors.groupingByConcurrent(User::getName,Collectors.summingInt(User::getScore)));
        System.out.println("sumMap:"+sumMap);
    }









}
