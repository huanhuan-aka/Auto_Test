/**
 * Company: Alo7
 * FileName: PayTest
 * Author: wanghuanhuan
 * CreateTime: 2019-05-01  21:28
 */
package com.course.testng.suite;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PayTest {

    @BeforeClass
    public void beforeClass(){
        System.out.println("before pay执行啦");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("after pay执行啦");
    }

    @Test
    public void paySuccess(){
        System.out.println("支付成功");
    }
}
