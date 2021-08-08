package com.lzc.cn.leetcode;

import java.util.ArrayList;
import java.util.List;

public class IntegerInversion {
    /**
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * <p>
     * 如果反转后整数超过 32 位的有符号整数的范[−231,  231 − 1] ，就返回 0。
     */

    public static void main(String[] args) {
        //Integer key = new Integer("");
        System.out.println(intInversion(123));
    }


    static int intInversion(int x) {
        if (x <= -2147483648 || x >= 2147483647) {
            return 0;
        }
        boolean flag = false;
        int num = Math.abs(x);
        if (x < 0) {
            flag = true;
        }
        // todo 数据转换成 字符串
        char [] strArray = Integer.toString(num).toCharArray();
        List list = new ArrayList<>();
        //todo 数组数据的反转
        for (int i = strArray.length - 1; i >= 0; i--) {
            list.add(strArray[i]);
        }
        StringBuilder sb = new StringBuilder();
        for (Object strs : list) {
            sb.append(strs);
        }
        int key = Integer.valueOf(sb.toString());
        if (flag == true) {
            key = -key;
        }
        return key;
    }
    /***
     * 可见性是因为缓存导致的，有序性是编译优化导致的
     *
     * happens-Before 规则 是针对volatile的加强去操作的；
     * 1.程序的顺序性规则：、
     * class VolatileExample {
     *     int x  = 0;
     *     volatile boolean v = false;
     *     public void writer(){
     *         x = 42;
     *         v = true;
     *     }
     *     public void reader(){
     *          if(v){
     *              System.out.println(x);
     *          }
     *      }
     *
     * }
     * 2. volatile 变量规则
     *
     *  class VolatileExample {
     *      volatile int x  = 4;
     *
     *      void writer() {
     *          x = 5;
     *      }
     *  }
     *  当线程a 执行之后并对x进行了写入的操作 线程b读取的x = 5；证明了x的值在线程中是可见的
     *
     *  3.传递性
     *      A happends-Before B ,且B happends -Before C 那么 A happends -Before C
     *      看规则1的代码，我们能理解到这点，线程B 中的v= true的时候X = 42在线程B中是可见的
     *  4.管程中锁的规则
     *
         synchronized (this) { //此处自动加锁
             // x是共享变量,初始值=10
             if (this.x < 12) {
             this.x = 12;
         }
         } //此处自动解锁
     * 5. 线程start() 规则
     * 6.线程 join() 规则
     *
     *  有一个共享变量 abc，在一个线程里设置了 abc 的值 abc=3，你思考一下，有哪些办法可以让其他线程能够看到abc==3？
     *  1.volatile 2.管程锁；主线程中去启动子线程start()， 主线程中等待子线程join()； final关键字修饰！
     */

}
