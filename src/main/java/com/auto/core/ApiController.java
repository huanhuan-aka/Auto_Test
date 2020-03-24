package com.auto.core;


import com.auto.common.*;
import com.auto.service.ApiRequestParamService;
import com.auto.service.ApiService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api")
public class ApiController {

    @Autowired
    ApiService apiService;
    @Autowired
    ApiRequestParamService apiRequestParamService;

    @GetMapping("/showApiUnderProject")
    @ApiOperation(value = "通过projectId获取该项目api信息",httpMethod = "Get")
    public ResponseResult showApiUnderProject(Integer projectId){
            //接口列表
            List<ApiListVO> apiListVOList = apiService.showApiUnderProject(projectId);
            return new ResponseResult("0",apiListVOList);
    }

    @GetMapping("/showApiUnderApiClassification")
    @ApiOperation(value = "通过获取该项目某个分类下的api信息",httpMethod = "Get")
    public ResponseResult showApiUnderApiClassification(Integer apiClassificationId){
        //接口列表
        List<ApiListVO> apiListVOList = apiService.showApiUnderApiClassification(apiClassificationId);
        return new ResponseResult("0",apiListVOList);
    }


    @GetMapping("/toApiView")
    @ApiOperation(value = "获取某个api的信息",httpMethod = "Get")
    public ResponseResult findApiViewVO(Integer apiId){
        //接口列表
        ApiVO apiVO = apiService.findApiViewVO(apiId);
        return new ResponseResult("0",apiVO);
    }

    @PutMapping("/edit")
    @ApiOperation(value = "更新某个api的信息",httpMethod = "Put")
    public ResponseResult editApi(ApiVO apiVO){
        //更新api表
        apiService.editApi(apiVO);
        return new ResponseResult("0","更新成功");

    }

    @PostMapping("/run")
    @ApiOperation(value = "发送接口请求",httpMethod = "Post")
    public ResponseResult run(ApiVO apiVO){
        ApiRunResult apiRunResult =apiService.run(apiVO);
        return new ResponseResult("0",apiRunResult);


    }

}
