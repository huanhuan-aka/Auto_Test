package com.auto.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component //自动填充
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //给用户表的注册时间做自动填充
        this.setFieldValByName("regtime",new Date(),metaObject);

        //给项目表的创建时间做自动填充
        this.setFieldValByName("createTime",new Date(),metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
