/**
 * Company: Alo7
 * FileName: ExpectException
 * Author: wanghuanhuan
 * CreateTime: 2019-05-01  22:03
 */
package com.course.testng;

import org.testng.annotations.Test;

public class ExpectException {
    /**
     * 什么时候会用到异常测试？
     * 在我们期望结果为一个异常的时候，比如：传入不合法的参数，程序抛出异常
     * 就是运行结果就是这个异常
     **/

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFailed(){
        System.out.println("这是一个失败的异常测试");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccessed(){
        System.out.println("这是一个成功的异常测试");
        throw new RuntimeException();
    }
}
