package com.liushiyao.java8.performance;

import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import com.github.houbb.junitperf.core.report.impl.ConsoleReporter;
import com.sun.javafx.tools.packager.Param;
import fai.comm.util.FaiList;
import fai.comm.util.Misc;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class BusinessDemo {


    private static List<Integer> getNumberList(int baseNum,int bound){

        List<Integer> numberList = new ArrayList<>();
        for (int i = 0; i < baseNum; i++) {
            numberList.add(new Random().nextInt(bound));
        }
        return numberList;
    }


    private static final int baseNum = 1000000;
    private static final int bound = 10000;
    /*
        公司内部实现
     */
    @Test
    public void Test(){

        List<Integer> numberList = getNumberList(baseNum,bound);
        long startTimeLong = System.currentTimeMillis();
//        Collections.sort(numberList);
        numberList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println("use time(ms):"+(System.currentTimeMillis() - startTimeLong));

    }

    /*
        java8实现
     */
    @Test
    public void Test2(){

        /*try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        List<Integer> numberList = getNumberList(baseNum,bound);
        long startTimeLong = System.currentTimeMillis();

//        numberList.parallelStream().sorted(Comparator.comparingInt(a -> a));
        numberList.stream().sorted(Comparator.comparingInt(o -> o));
        System.out.println("use time(ms):"+(System.currentTimeMillis() - startTimeLong));
       /* try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
//        System.out.println("numberList:"+list);

    }
    //查找分数前100名的学生的名字  2251   3191 7119    3224
    @Test
    @JunitPerfConfig(threads = 1, warmUp = 1000, duration = 2000,reporter = ConsoleReporter.class)
    public void forEachDemo(){

//        List<User> userList = getUserList(NUMBER);

        long startLong = System.currentTimeMillis();
        //按分数从大到小排序
        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o2.getScore() - o1.getScore();
            }
        });

        //取前100名学生
        List<User> tempList = userList.subList(0,100);

        //拿学生的名字
        List<String> nameList = new ArrayList<>();
        for (User user:tempList){
            nameList.add(user.getName());
        }
        System.out.println("use time(ms):"+ (System.currentTimeMillis() - startLong));

        System.out.println("nameList:"+nameList);


    }

    public static List<User> getUserList(int number){

        List<User> userList = new ArrayList<>();
        for (int i = 0; i < number; i++) {

            User user = new User();
            user.setId(2020+(i % 3));
            user.setName("我是"+user.getId());
            user.setScore(70+new Random().nextInt(30));
            userList.add(user);
        }

        return userList;

    }
    private static final int NUMBER = 4000000;


    static List<User> userList = getUserList(NUMBER);

    //2681   2600
    @Test
    @JunitPerfConfig(threads = 1, warmUp = 1000, duration = 2000,reporter = ConsoleReporter.class)
    public void streamDemo(){

//        List<User> userList = getUserList(NUMBER);

        long startLong = System.currentTimeMillis();
        //按分数从大到小排序
        List<String> nameList = userList
                .parallelStream()
                .sorted((o1, o2) -> o2.getScore() - o1.getScore())
                .limit(100)
                .map(User::getName)
                .collect(Collectors.toList());


        System.out.println("use time(ms):"+ (System.currentTimeMillis() - startLong));

        System.out.println("nameList:"+nameList);

    }


    @Test
    public void Test3(){

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Integer> numberList = getNumberList(baseNum,bound);
        long startTimeLong = System.currentTimeMillis();

//        numberList.parallelStream().sorted(Comparator.comparingInt(a -> a));
        numberList.parallelStream().forEach(i ->
        {
            try {
                Thread.sleep(5);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("use time(ms):"+(System.currentTimeMillis() - startTimeLong));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println("numberList:"+list);

    }
    @Test
    public void Test4(){
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
    @Test
    public void Test5(){

        for (int i = 0; i < 100; i++) {
            System.out.println(70+new Random().nextInt(30));
        }

    }


    public long measureSumPerf(Function<Long, Long> adder, long n) { long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000; System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }


    static class ParallelStreams{

        public static long sequentialSum(long n) {
            return Stream.iterate(1L, i -> i + 1)
                    .limit(n)
                    .reduce(0L, Long::sum);
        }

        public static long iterativeSum(long n) {
            long result = 0;
            for (long i = 1L; i <= n; i++) {
                result += i;
            }
            return result;
        }

        public static long parallelSum(long n) {
            return Stream.iterate(1L, i -> i + 1)
                    .limit(n)
                    .parallel()
                    .reduce(0L, Long::sum);
        }

        public static long rangedSum(long n) {
            return LongStream.rangeClosed(1, n)
                    .reduce(0L, Long::sum);
        }

        public static long parallelRangedSum(long n) {
            return LongStream.rangeClosed(1, n)
                    .parallel()
                    .reduce(0L, Long::sum);
        }

    }

    @Test
    public void Demo(){

        System.out.println("Parallel sequentialSum:" + measureSumPerf(ParallelStreams::sequentialSum, 20_000_000) + " msecs");
        System.out.println("Parallel iterativeSum:" + measureSumPerf(ParallelStreams::iterativeSum, 20_000_000) + " msecs");
        System.out.println("Parallel parallelSum:" + measureSumPerf(ParallelStreams::parallelSum, 20_000_000) + " msecs");
        System.out.println("Parallel rangedSum:" + measureSumPerf(ParallelStreams::rangedSum, 20_000_000) + " msecs");
        System.out.println("Parallel parallelRangedSum:" + measureSumPerf(ParallelStreams::parallelRangedSum, 20_000_000) + " msecs");
    }

}
