/**
 * Company: Alo7
 * FileName: TimeOutTest
 * Author: wanghuanhuan
 * CreateTime: 2019-05-01  22:56
 */
package com.course.testng;

import org.testng.annotations.Test;

public class TimeOutTest {

    @Test(timeOut = 3000) //单位为毫秒，
    public void testSuccess() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Test(timeOut = 2000) //单位为毫秒，
    public void testFailed() throws InterruptedException {
        Thread.sleep(3000);
    }
}
