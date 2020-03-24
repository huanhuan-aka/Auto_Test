package com.auto.core;


import com.auto.common.ApiVO;
import com.auto.common.CaseEditVO;
import com.auto.common.CaseListVO;
import com.auto.common.ResponseResult;
import com.auto.domain.ApiRequestParam;
import com.auto.domain.CaseParamValue;
import com.auto.domain.Cases;
import com.auto.domain.Suite;
import com.auto.service.CaseParamValueService;
import com.auto.service.CasesService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Anna.wang
 * @since 2020-03-05
 */
@RestController
@RequestMapping("/cases")
public class CasesController {

    @Autowired
    CasesService casesService;

    @PostMapping("/addToSuite")
    @ApiOperation(value = "添加用例到测试集",httpMethod = "Post")
    public ResponseResult addToSuite(Cases caseVO, ApiVO apiRunVO){

        casesService.addToSuite(caseVO,apiRunVO);
        return new ResponseResult("0","添加到测试集成功");
    }

    @GetMapping("/showCaseUnderProject")
    @ApiOperation(value = "获取当前项目用例列表",httpMethod = "Get")
    public ResponseResult showCaseUnderProject(Integer projectId){
        List<CaseListVO> caseListVOList = casesService.showCaseUnderProject(projectId);
        return new ResponseResult("0",caseListVOList);
    }

    @GetMapping("/showCaseUnderSuite")
    @ApiOperation(value = "获取当前用例集用例列表",httpMethod = "Get")
    public ResponseResult showCaseUnderSuite(String suiteId){
        List<CaseListVO> caseListVOList = casesService.showCaseUnderSuite(suiteId);
        return new ResponseResult("0",caseListVOList);
    }

    @GetMapping("/toCaseEdit")
    @ApiOperation(value = "获取要编辑的用例",httpMethod = "Get")
    public ResponseResult findCaseEditVO(String caseId){
        CaseEditVO caseEditVO = casesService.findCaseEditVO(caseId);
        return new ResponseResult("0",caseEditVO);
    }

    @PutMapping("/updateCaseReq")
    @ApiOperation(value = "更新用例的参数",httpMethod = "Put")
    public ResponseResult updateCaseReq(CaseEditVO caseEditVO){
        casesService.updateCase(caseEditVO);
        return new ResponseResult("0","更新用例成功");
    }


}
