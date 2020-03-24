package com.auto.common;

import com.auto.domain.TestReport;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReportVO {
    private Integer id;
    private String suiteName;

    private String testerName;
    private Date createReportTime;

    private int totalCaseNum;
    private int successNum; //通过数
    private int failureNum; //失败数

    private List<CaseListVO> caseList;


    public int getTotalCaseNum(){
        return caseList.size();
    }

    public int getSuccessNum(){
        int success = 0, failure = 0;
        for (CaseListVO caseVO:caseList) {
            TestReport testReport = caseVO.getTestReport();
            if(testReport!=null){
                if(testReport.getPassFlag().equals("通过")){
                    success++;
                }else {
                    failure++;
                }
            }
        }
        this.successNum = success;
        this.failureNum = failure;
        return successNum;
    }

    public int getFailureNum(){
        return failureNum;
    }

}
