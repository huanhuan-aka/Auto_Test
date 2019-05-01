/**
 * Company: Alo7
 * FileName: GroupsonClass1
 * Author: wanghuanhuan
 * CreateTime: 2019-05-01  21:52
 */
package com.course.testng.group;

import org.testng.annotations.Test;

@Test(groups = "stu")  //类上面也是可以写test标签的
public class GroupsOnClass1 {

    public void stu1(){
        System.out.println("GroupsOnClass1中的stu1执行");
    }

    public void stu2(){
        System.out.println("GroupsOnClass1中的stu2执行");
    }

}
