/**
 * Company: Alo7
 * FileName: SuiteConfig
 * Author: wanghuanhuan
 * CreateTime: 2019-05-01  21:25
 */
package com.course.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class SuiteConfig {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("before suite执行啦");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("after suite执行啦");
    }

}
