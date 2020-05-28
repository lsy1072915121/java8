package com.liushiyao.java8;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Demo {







    /*
          快速修改变量
          需求：将常量值修改为apple
          步骤：
            1.e：移动光标
            2：vawd:删除单词

     */
    @Test
    public void Test2(){

        final String APPLE = "banana";
        System.out.println("我是谁？"+ APPLE);



    }

    /*
        自动排序
        需求：将字符串按照字母顺序排序
        步骤：
        1.V，视图块模式选中多行
        2.:sort(命令行输入sort)
     */
    @Test
    public void Test3(){

        List<String> list = new ArrayList<>();
        list.add("B");
        list.add("D");
        list.add("C");
        list.add("A");

    }

    /*
        统一替换
        需求：将对象abc重命名为list
        步骤：
          :n1,n2s/abc/list
     */
    @Test
    public void Test4(){


        List<Integer> abc = new ArrayList<>();
        abc.add(1);
        abc.add(2);
        abc.add(3);
        abc.add(4);
        abc.add(5);
        abc.add(6);
        abc.add(7);
        abc.add(8);
        abc.add(9);
        abc.add(10);


    }

    /*
        多行操作
        要点：视图块模式
        a.需求：将C~H元素进行单行注释
        步骤：
            1.光标移动到C
            2.ctrl + v,进入视图块模式
            3.移动光标，选择需要修改的行
            3.I（shift + i），批量插入模式
            4.输入 //
            5.double esc,退出视图块模式
         b.需求：将C~H元素取消注释
            1.光标移动到C
            2.ctrl + v,进入视图块模式
            3.移动光标，选择需要修改的行
            4.d,删除字符
     */
    @Test
    public void Test(){

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");
        list.add("G");
        list.add("H");
        list.add("I");
        list.add("J");
        list.add("K");

    }

    /*
        宏录制
        需求：将下列String数组中的所有元素后面添加A字符
                如abc.add("1A");
        步骤：
            1.qa：开始录制宏，并存储在a寄存器中
            2.操作
            3.q：结束录制宏
            4.@a：播放宏（n@a，n次播放宏）
     */
    @Test
    public void Test5(){

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("22");
        list.add("333");
        list.add("4444");
        list.add("55555");
        list.add("666666");
        list.add("7777777");
        list.add("88888888");
        list.add("999999999");
        list.add("1010101010");


    }

    @Test
    public void Test6(){

        System.out.println(Math.ceil(21.333 / 100 * 1000 )/ 10.0);

    }





}
