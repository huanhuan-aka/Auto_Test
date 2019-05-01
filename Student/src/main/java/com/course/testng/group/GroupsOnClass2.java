/**
 * Company: Alo7
 * FileName: GroupsOnClass2
 * Author: wanghuanhuan
 * CreateTime: 2019-05-01  21:53
 */
package com.course.testng.group;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass2 {

    public void stu1(){
        System.out.println("GroupsOnClass2中的stu1执行");
    }

    public void stu2(){
        System.out.println("GroupsOnClass2中的stu2执行");
    }

}
