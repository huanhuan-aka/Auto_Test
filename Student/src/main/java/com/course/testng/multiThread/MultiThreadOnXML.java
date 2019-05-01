/**
 * Company: Alo7
 * FileName: MultiThreadOnXML
 * Author: wanghuanhuan
 * CreateTime: 2019-05-01  22:45
 */
package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadOnXML {

    @Test
    public void test1(){
        System.out.printf("Thread Id: %s%n ",Thread.currentThread().getId());
    }

    @Test
    public void test2(){
        System.out.printf("Thread Id: %s%n ",Thread.currentThread().getId());
    }

    @Test
    public void test3(){
        System.out.printf("Thread Id: %s%n ",Thread.currentThread().getId());
    }
}
