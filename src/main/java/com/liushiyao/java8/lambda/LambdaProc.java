package com.liushiyao.java8.lambda;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

//无参无返回值
public class LambdaProc {

  @Test
  public void testLambda(){
//    int x = 2;
    function(() -> System.out.println("123"));
  }
  public void function(LambdaTest lambdaTest){
    lambdaTest.run();
  }


  @Test
  public void runnableTest(){

    Runnable runnable = () -> System.out.println("hello world！");
    new Thread(runnable).run();

  }
  @Test
  public void runnableTest2(){
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        System.out.println("hello world");
      }
    };
    new Thread(runnable).run();
  }

  @Test
  public void foreachTest(){
    Arrays.asList( "a", "b", "d").forEach( a -> System.out.println( a ) );
  }

  @Test
  public void foreachSortTest(){
    List<String> list = new ArrayList();
    list.add("a");
    list.add("d");
    list.add("b");
    Collections.sort(list,(str1,str2) -> str1.compareTo(str2));
    System.out.println(list);
  }
  @Test
  public void foreachSortTest2(){
    List arrayList = Arrays.asList("aasdg","fbg","s","dcar");

    arrayList.sort(new Comparator() {
      @Override
      public int compare(Object o1, Object o2) {
        String a = (String)o1;
        String b = (String) o2;
        return a.compareTo(b);
      }
    });
    System.out.println(arrayList);
  }
  @Test
  public void foreachSortTest3(){
    String waibu = "lambda :";
    List<String> proStrs = Arrays.asList(new String[]{"Ni","Hao","Lambda"});
    List<String>execStrs = proStrs.stream().map(chuandi -> {
      Long zidingyi = System.currentTimeMillis();
      return waibu + chuandi + " -----:" + zidingyi;
    }).collect(Collectors.toList());
    execStrs.forEach(System.out::println);
  }

  @Test
  public void lambdaTest(){
    String[] atp = {"Rafael Nadal", "Novak Djokovic",
        "Stanislas Wawrinka",
        "David Ferrer","Roger Federer",
        "Andy Murray","Tomas Berdych",
        "Juan Martin Del Potro"};
    List<String> players =  Arrays.asList(atp);

// 以前的循环方式
    for (String player : players) {
      System.out.print(player + "; ");
    }

// 使用 lambda 表达式以及函数操作(functional operation)
    players.forEach((player) -> System.out.print(player + "; "));

// 在 Java 8 中使用双冒号操作符(double colon operator)
    players.forEach(System.out::println);
  }
}


