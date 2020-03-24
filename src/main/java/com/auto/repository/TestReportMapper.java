package com.auto.repository;

import com.auto.common.CaseEditVO;
import com.auto.common.CaseListVO;
import com.auto.common.ReportVO;
import com.auto.domain.TestReport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Anna.wang
 * @since 2020-03-05
 */
public interface TestReportMapper extends BaseMapper<TestReport> {

    @Select("SELECT DISTINCT t1.*,t6.method,t6.id apiId,t6.url,t3.host FROM cases t1 LEFT JOIN suite t2 ON t1.suite_id=t2.id LEFT JOIN project t3 ON t2.project_id=t3.id LEFT JOIN case_param_value t4 ON t1.id=t4.case_id LEFT JOIN api_request_param t5 ON t4.api_request_param_id=t5.id LEFT JOIN api t6 ON t5.api_id=t6.id WHERE t1.suite_id=#{suiteId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "requestParams",column = "id",
                    many = @Many(select = "com.auto.repository.ApiRequestParamMapper.findApiRequestParamBy")),
            @Result(property = "testRules",column = "id",
                    many = @Many(select = "com.auto.repository.TestRuleMapper.findTestRulesBy"))
    })
    List<CaseEditVO> findCaseListBySuite(Integer suiteId);


    @Delete("delete from test_report where case_id in (select id from cases where suite_id=#{suiteId})")
    void delTestReport(Integer suiteId);

    @Select("select * from test_report where case_id=#{caseId}")
    TestReport findByCase(Integer caseId);

    @Select("select t1.id,t1.name suiteName,t2.username testerName from suite t1 left join user t2 on t1.create_user=t2.id where t1.id=#{suiteId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "caseList",column = "id",
                    many = @Many(select = "com.auto.repository.CasesMapper.showCaseUnderSuite")),
    })
    ReportVO getReport(Integer suiteId);

}
