/**
 * Company: Alo7
 * FileName: GroupsOnMethod
 * Author: wanghuanhuan
 * CreateTime: 2019-05-01  21:43
 */
package com.course.testng.group;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethod {

    @Test(groups = "server")
    public void test1(){
        System.out.println("这是server组的测试方法1111");
    }

    @Test(groups = "server")
    public void test2(){
        System.out.println("这是server组的测试方法2222");
    }

    @Test(groups = "client")
    public void test3(){
        System.out.println("这是client组的测试方法3333");
    }

    @Test(groups = "client")
    public void test4(){
        System.out.println("这是client组的测试方法4444");
    }

    @BeforeGroups("server")
    public void beforeGroupsOnServer(){
        System.out.println("这是server组运行前执行的方法");
    }
    @AfterGroups("server")
    public void afterGroupsOnServer(){
        System.out.println("这是server组运行后执行的方法");
    }

    @BeforeGroups("client")
    public void beforeGroupsOnClient(){
        System.out.println("这是client组运行前执行的方法");
    }

    @AfterGroups("client")
    public void afterGroupsOnClient(){
        System.out.println("这是client组运行后执行的方法");
    }
}
