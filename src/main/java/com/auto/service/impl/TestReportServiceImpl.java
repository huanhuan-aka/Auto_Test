package com.auto.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.auto.common.ApiRunResult;
import com.auto.common.CaseEditVO;
import com.auto.common.ReportVO;
import com.auto.domain.ApiRequestParam;
import com.auto.domain.TestReport;
import com.auto.domain.TestRule;
import com.auto.repository.TestReportMapper;
import com.auto.service.TestReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Anna.wang
 * @since 2020-03-05
 */
@Service
public class TestReportServiceImpl extends ServiceImpl<TestReportMapper, TestReport> implements TestReportService {
    @Autowired
    TestReportMapper testReportMapper;

    @Override
    public List<TestReport> runBySuite(Integer suiteId){
        List<CaseEditVO> caseEditVOS = testReportMapper.findCaseListBySuite(suiteId);
        List<TestReport> testReports = new ArrayList<>();
        for (CaseEditVO caseEdit:caseEditVOS
             ) {
            //循环测试套件中的用例,依次执行,远程调用
            TestReport testReport =runAndGetReport(caseEdit);
            testReports.add(testReport);
        }
        //先删除历史报告,再插入最新的执行报告
        testReportMapper.delTestReport(suiteId);
        this.saveBatch(testReports);
        return testReports;
    }

    /**
     * 单个api执行并获取测试报告
     * @param caseEditVO
     * @return
     */
    public TestReport runAndGetReport(CaseEditVO caseEditVO){
        TestReport testReport = new TestReport();
        RestTemplate restTemplate = new RestTemplate();
        String url = caseEditVO.getHost()+caseEditVO.getUrl();
        String method = caseEditVO.getMethod();
        LinkedMultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        LinkedMultiValueMap<String,String> bodyParams = new LinkedMultiValueMap<>();
        List<ApiRequestParam> requestParams =caseEditVO.getRequestParams();
        String queryStr = "?";
        for (ApiRequestParam param: requestParams) {
            if(param.getType()==3){ //headers
                headers.add(param.getName(),param.getValue());
            }else if(param.getType()==1){
                queryStr += param.getName()+"="+param.getValue()+"&";

            }else{
                //body  没有处理type=1时的情况
                bodyParams.add(param.getName(),param.getValue());
            }
        }
        if(queryStr.equals("?")&&queryStr.lastIndexOf("&")>0){
            queryStr = queryStr.substring(0,queryStr.lastIndexOf("&"));
        }

        HttpEntity httpEntity = null;
        ResponseEntity response = null;
//        System.out.println(method);
//        System.out.println(JSON.toJSONString(bodyParams));
        try { //发送请求(此时有调用异常,往往没有body,需要try catch一下)
            if("get".equalsIgnoreCase(method)){
                httpEntity = new HttpEntity(headers);
                response = restTemplate.exchange(url+queryStr, HttpMethod.GET,httpEntity,String.class);
            }else if("post".equalsIgnoreCase(method)){
                httpEntity = new HttpEntity(bodyParams,headers);
                response = restTemplate.exchange(url, HttpMethod.POST,httpEntity,String.class);
                //post请求时才会有请求体
                testReport.setRequestBody(JSON.toJSONString(bodyParams));
            }
            testReport.setCaseId(caseEditVO.getId());
            testReport.setRequestHeaders(JSON.toJSONString(headers));
            testReport.setRequestUrl(url);
            testReport.setResponseHeaders(JSON.toJSONString(response.getHeaders()));
            testReport.setResponseBody(response.getBody().toString());
            //根据验证结果设置test_flag(执行结果)
            testReport.setPassFlag(assertByTestRule(response.getBody().toString(),caseEditVO.getTestRules()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return testReport;
    }

    /**
     * 判断api执行结果
     * @param responseBody
     * @param testRules
     * @return
     */
    public String assertByTestRule(String responseBody, List<TestRule> testRules) {
        boolean flag = true;

            for (TestRule rule : testRules) {
                Object value = JSONPath.read(responseBody, rule.getExpression());
                String actual = (String) value;
                String op = rule.getOperator();
                if (op.equals("=")) {
                    if (!actual.equals(rule.getExpected())) {
                        flag = false;
                    }
                } else if (op.equals("contains")) {
                    if (!actual.contains(rule.getExpected())) {
                        flag = false;
                    }
                }
            }
            if (!flag) {
                return "不通过";
            }
            return "通过";
           }

    @Override
    public TestReport findByCase(Integer caseId){

        return testReportMapper.findByCase(caseId);
    }

    @Override
    public ReportVO getReport(Integer suiteId){

        return testReportMapper.getReport(suiteId);
    }
}
