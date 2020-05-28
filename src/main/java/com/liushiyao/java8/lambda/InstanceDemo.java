package com.liushiyao.java8.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InstanceDemo {


    @Test
    public void Test1(){

        List<Apple> appleList = new ArrayList<>();
        appleList.add(new Apple("apple1",100));
        appleList.add(new Apple("apple2",90));
        appleList.add(new Apple("apple3",120));


        //1.传递Comparator
        appleList.sort(new AppleComparator());
        //2.匿名内部类
        appleList.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });

        //3.lambda
        /*
        default void sort(Comparator<? super E> c) {
            Object[] a = this.toArray();
            Arrays.sort(a, (Comparator) c);
            ListIterator<E> i = this.listIterator();
            for (Object e : a) {
                i.next();
                i.set((E) e);
            }
        }
         */

        appleList.sort((a1,a2) -> a1.getWeight() - a2.getWeight());
        //4.方法引用(“对库存进行排序，比较苹果的重量。”)
        appleList.sort(Comparator.comparingInt(Apple::getWeight));


        appleList.forEach(System.out::println);

    }


}

class AppleComparator implements Comparator<Apple> {
    public int compare(Apple a1, Apple a2){
        return a1.getWeight() - a2.getWeight();
    }
}



class Apple{

    private String name;
    private int weight;

    Apple(String name,int weight){
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
            "name='" + name + '\'' +
            ", weight=" + weight +
            '}';
    }
}
