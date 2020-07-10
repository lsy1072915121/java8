package com.liushiyao.java8.performance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ParallelStreamParsing {

    static List<Integer> list = new ArrayList<>();
    static{
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
    }


    @Test
    public void streamThreadDemo(){

        list.stream().forEach(i ->{

            //输出当前线程名字
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

    }


    //线程数默认情况等与处理器的核心数
    @Test
    public void parallelStreamThreadDemo(){

        System.out.println("Thread Number:"+Runtime.getRuntime().availableProcessors());
        list.parallelStream().forEach(i ->{

           //输出当前线程名字
           System.out.println(Thread.currentThread().getName());
           try {
               Thread.sleep(200);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }

       });

    }

    //获取默认线程数
    @Test
    public void getThreadNumDemo(){

        System.out.println(Runtime.getRuntime().availableProcessors());

    }

    //自定义线程数
    @Test
    public void setThreadNumDemo(){

        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","10");
        list.parallelStream().forEach(i ->{

            //输出当前线程名字
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

    }


}
