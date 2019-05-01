/**
 * Company: Alo7
 * FileName: MultiThreadOnAnnotion
 * Author: wanghuanhuan
 * CreateTime: 2019-05-01  22:37
 */
package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadOnAnnotion {

    @Test(invocationCount = 10,threadPoolSize = 3)   //使用10个线程来执行  ,线程池
    public void test1(){
        System.out.println("1111111111111");
        System.out.printf("Thread Id: %s%n ",Thread.currentThread().getId());
    }
}
