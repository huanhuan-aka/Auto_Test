/**
 * Company: Alo7
 * FileName: Parametertest
 * Author: wanghuanhuan
 * CreateTime: 2019-05-01  22:14
 */
package com.course.testng.parameter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterTest {

    @Test
    @Parameters({"name","age"})
    public void paramTest1(String name,int age){
        System.out.println("name="+name+"; age="+age);
    }


}
