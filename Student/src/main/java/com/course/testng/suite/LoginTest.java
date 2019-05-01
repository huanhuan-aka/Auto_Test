/**
 * Company: Alo7
 * FileName: LoginTest
 * Author: wanghuanhuan
 * CreateTime: 2019-05-01  21:25
 */
package com.course.testng.suite;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {

    @BeforeClass
    public void beforeClass(){
        System.out.println("before login执行啦");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("after login执行啦");
    }

    @Test
    public void loginTB(){
        System.out.println("taobao登录成功");
    }

}
