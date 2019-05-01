/**
 * Company: Alo7
 * FileName: BasicAnnotation
 * Author: wanghuanhuan
 * CreateTime: 2019-05-01  21:04
 */
package com.course.testng;

import org.testng.annotations.*;

public class BasicAnnotation {

    /**
     * @Test 是最基本的注解，用来把方法标记为测试的一部分
     * @BeforeMethod  每次测试方法执行前都要执行的内容
     * @AfterMethod   每次测试方法执行后都要执行的内容
     * @BeforeClass   类执行前执行，在所有测试方法执行前执行
     * @AfterClass    类执行后执行，在所有测试方法执行完成后执行
     * @BeforeSuite
     * @AfterSuite
     *
     **/

    @Test
    public void TestCase1(){
        System.out.println("这是测试用例1");
    }
    @Test
    public void TestCase2(){
        System.out.println("这是测试用例2");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("这是测试方法之前运行");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("这是测试方法之后运行");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("这是在测试类执行前执行的");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("这是在测试类执行后执行的");
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("这是在测试套件执行前执行的，早于测试类的执行");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("这是在测试套件执行后执行的，最后执行的");
    }
}
