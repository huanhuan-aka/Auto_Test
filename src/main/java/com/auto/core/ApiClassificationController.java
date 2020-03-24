package com.auto.core;


import com.alibaba.fastjson.JSON;
import com.auto.common.ApiClassificationVO;
import com.auto.common.ResponseResult;
import com.auto.domain.ApiClassification;
import com.auto.domain.Cases;
import com.auto.domain.Suite;
import com.auto.service.ApiClassificationService;
import com.auto.service.SuiteService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/apiClassification")
public class ApiClassificationController {
    @Autowired
    ApiClassificationService apiClassificationService;
    @Autowired
    SuiteService suiteService;


    @GetMapping("/toIndex")
    @ApiOperation(value = "通过projectId获取api分类信息",httpMethod = "Get")
    public ResponseResult getApiClassificationByProjectId(Integer projectId,Integer tabId){

        if(tabId==1){
            //接口列表
            List<ApiClassificationVO> apiClassificationVOList = apiClassificationService.getApiClassificationByProjectId(projectId);
            return new ResponseResult("0",apiClassificationVOList,"查询api分类同时延迟加载api");
        }else{
            List<Suite> suiteList = suiteService.findSuiteAndReleatedCasesBy(projectId);
            return new ResponseResult("0",suiteList,"查询集合列表同时延迟加载用例");
        }
    }

    @PostMapping("/addApiClassification")
    @ApiOperation(value = "新增接口分类",httpMethod = "POST")
    public ResponseResult addApiClassification(@RequestBody @Validated ApiClassification apiClassification, BindingResult error) {
        if(error.hasErrors()){
            String defaultMessage = error.getFieldError().getDefaultMessage();
            return new ResponseResult("1",defaultMessage);
        }else{
            apiClassificationService.save(apiClassification);
            return new ResponseResult("0","新增接口分类成功!");
        }
    }

    //TODO:前端联调--删除接口分类/编辑接口分类/添加接口分类-->待完成
    @DeleteMapping("/{projectId}")
    @ApiOperation(value = "删除接口分类",httpMethod = "Delete")
    public ResponseResult delProjectSetById(@PathVariable("projectId") Integer projectId) {

        apiClassificationService.removeById(projectId);
        return new ResponseResult("0","删除接口分类成功");
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "根据projectId查询接口分类",httpMethod = "Get")
    public ResponseResult findAll(Integer projectId){

        QueryWrapper queryCondition = new QueryWrapper();
        queryCondition.eq("project_id",projectId);
        List<ApiClassification> apiClassificationList = apiClassificationService.list(queryCondition);

        return new ResponseResult("0",apiClassificationList);
    }

    @PostMapping("/addVersion2")
    public ResponseResult addVersion2(@RequestBody String jsonStr) {
        //将json字符串转成java对象
        //TODO:这里截串还是有问题....等下看
        System.out.println(jsonStr);
        String targetStr = jsonStr.substring(jsonStr.indexOf("[")+2,jsonStr.lastIndexOf("]")-1);
        System.out.println(targetStr);
        ApiClassification apiClassification = JSON.parseObject(targetStr,ApiClassification.class);
        apiClassificationService.save(apiClassification);
        return new ResponseResult("0","新增分类成功");
    }

}
