/**
 * Company: Alo7
 * FileName: GroupsOnClass3
 * Author: wanghuanhuan
 * CreateTime: 2019-05-01  21:53
 */
package com.course.testng.group;

import org.testng.annotations.Test;

@Test(groups = "teacher")
public class GroupsOnClass3 {

    public void teacher1(){
        System.out.println("GroupsOnClass3中的teacher1执行");
    }

    public void teacher2(){
        System.out.println("GroupsOnClass3中的teacher2执行");
    }
}


