package com.lzc.cn.week_01.test;

import com.lzc.cn.week_01.work.Work_01;

import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) {
        Work_01 work_01 = new Work_01("Hello.xlass");
        work_01.getOwnerObject("Hello","hello");
    }
}
