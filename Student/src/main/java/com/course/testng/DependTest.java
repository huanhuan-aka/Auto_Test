/**
 * Company: Alo7
 * FileName: DependTest
 * Author: wanghuanhuan
 * CreateTime: 2019-05-01  22:10
 */
package com.course.testng;

import org.testng.annotations.Test;

public class DependTest {
    //依赖测试
    @Test
    public void test1(){
        System.out.println("test1 run");
        throw new RuntimeException();
    }

    //当被依赖的测试类执行抛出异常后，当前测试类会被忽略
    @Test(dependsOnMethods = {"test1"})
    public void test2(){
        System.out.println("test2 run, test2依赖于test1");
    }

}
