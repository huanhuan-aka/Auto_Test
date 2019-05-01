/**
 * Company: Alo7
 * FileName: IgnoreTest
 * Author: wanghuanhuan
 * CreateTime: 2019-05-01  21:38
 */
package com.course.testng;

import org.testng.annotations.Test;

public class IgnoreTest {

    //@Test后加上enable属性，默认为true，执行； enabled=false 则不执行

    @Test
    public void Ignore1(){
        System.out.println("ignore 1 执行");
    }

    @Test(enabled=false)
    public void Ignore2(){
        System.out.println("ignore 2 执行");
    }

    @Test(enabled=true)
    public void Ignore3(){
        System.out.println("ignore 3 执行");
    }
}
