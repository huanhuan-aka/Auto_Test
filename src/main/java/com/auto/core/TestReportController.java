package com.auto.core;


import com.auto.common.ReportVO;
import com.auto.common.ResponseResult;
import com.auto.domain.TestReport;
import com.auto.service.TestReportService;
import com.auto.service.TestRuleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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
@RequestMapping("/testReport")
public class TestReportController {
    @Autowired
    TestReportService testReportService;


    @RequestMapping("/runSuite")
    @ApiOperation(value = "批量执行测试套件内的接口")
    public ResponseResult runBySuite(Integer suiteId){
        List<TestReport> testReports = testReportService.runBySuite(suiteId);
        return new ResponseResult("0",testReports,"接口全部执行");
    }


    @RequestMapping("/findCaseRunResult")
    @ApiOperation(value = "根据caseId查询测试报告")
    public ResponseResult findCaseRunResult(Integer caseId){
        TestReport testReport = testReportService.findByCase(caseId);
        return new ResponseResult("0",testReport);
    }

    @RequestMapping("/get")
    @ApiOperation(value = "根据suiteId获取测试报告前端渲染")
    public ResponseResult getReport(Integer suiteId){
        ReportVO reportVO = testReportService.getReport(suiteId);
        reportVO.setCreateReportTime(new Date());
        return new ResponseResult("0",reportVO);
    }

}
