/**
 * Company: Alo7
 * FileName: DataProvidertest
 * Author: wanghuanhuan
 * CreateTime: 2019-05-01  22:22
 */
package com.course.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
public class DataProviderTest {

    @Test(dataProvider = "dataSource")
    public void DataProviderTest(String name,int age){
        System.out.println("name="+name+"; age="+age);
    }

    @DataProvider(name = "dataSource")
    public Object[][] DataProvider(){
        Object[][] datas=new Object[][]{
                {"zhangsan",10},
                {"lisi",20},
                {"wangwu",30}
        };
        return datas;
    }

    @Test(dataProvider = "methodData")
    public void test1(String name,int age){
        System.out.println("test1111方法 name="+name+", age="+age);
    }
    @Test(dataProvider = "methodData")
    public void test2(String name,int age){
        System.out.println("test2222方法 name="+name+", age="+age);
    }

    //传入方法名，通过不同方法名设置不同的参数
    @DataProvider(name = "methodData")
    public Object[][] methodDataTest(Method method){
        Object[][] results=null;
        if(method.getName().equalsIgnoreCase("test1")){
            results=new Object[][]{
                    {"zhangsan",20},
                    {"lisi",25}
            };
        }else if(method.getName().equalsIgnoreCase("test2")){
            results=new Object[][]{
                    {"wangwu",50},
                    {"zhaoliu",60}
            };
        }
        return results;
    }


}
