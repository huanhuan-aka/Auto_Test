package com.auto.core;


import com.auto.common.ResponseResult;
import com.auto.domain.Suite;
import com.auto.service.SuiteService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Anna.wang
 * @since 2020-03-05
 */
@RestController
@RequestMapping("/suite")
public class SuiteController {
    @Autowired
    SuiteService suiteService;

    @GetMapping("/listAll")
    @ApiOperation(value = "获取该项目下所有的测试集",httpMethod = "Get")
    public ResponseResult getSuiteList(Integer projectId){
        QueryWrapper queryCondition = new QueryWrapper();
        queryCondition.eq("project_id",projectId);
        List<Suite> suiteList = suiteService.list(queryCondition);
        return new ResponseResult("0",suiteList);
    }




}
