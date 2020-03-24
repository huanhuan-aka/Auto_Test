package com.auto.repository;

import com.auto.common.CaseEditVO;
import com.auto.common.CaseListVO;
import com.auto.domain.Cases;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface CasesMapper extends BaseMapper<Cases> {

    @Select("select * from cases where suite_id=#{suiteId}")
    List<Cases> findCases(Integer suiteId);

    /**
     * 根据projectId获取当前项目所有的用例
     * @param projectId
     * @return
     */
    @Select("SELECT DISTINCT t1.*,t6.id apiId,t6.url apiUrl FROM cases t1 LEFT JOIN suite t2 ON t1.suite_id=t2.id LEFT JOIN project t3 ON t2.project_id=t3.id LEFT JOIN case_param_value t4 ON t1.id=t4.case_id LEFT JOIN api_request_param t5 ON t4.api_request_param_id=t5.id LEFT JOIN api t6 ON t5.api_id=t6.id WHERE t3.id=#{projectId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "testReport",column = "id",
                    one = @One(select = ("com.auto.repository.TestReportMapper.findByCase")))
    })
    List<CaseListVO> showCaseUnderProject(Integer projectId);

    /**
     * 根据suiteId获取当前测试集所有的用例
     * @param suiteId
     * @return
     */
    @Select("SELECT DISTINCT t1.*,t6.id apiId,t6.url apiUrl FROM cases t1 LEFT JOIN suite t2 ON t1.suite_id=t2.id LEFT JOIN project t3 ON t2.project_id=t3.id LEFT JOIN case_param_value t4 ON t1.id=t4.case_id LEFT JOIN api_request_param t5 ON t4.api_request_param_id=t5.id LEFT JOIN api t6 ON t5.api_id=t6.id WHERE t1.suite_id=#{suiteId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "testReport",column = "id",
            one = @One(select = ("com.auto.repository.TestReportMapper.findByCase")))
    })
    List<CaseListVO> showCaseUnderSuite(String suiteId);

    @Select("SELECT DISTINCT t1.id,t1.NAME,t6.id apiId,t6.method,t5.HOST,t6.url FROM cases t1 JOIN case_param_value t2 ON t2.case_id=t1.id JOIN api_request_param t3 ON t2.api_request_param_id=t3.id JOIN api t6 ON t3.api_id=t6.id JOIN suite t4 ON t4.id=t1.suite_id JOIN project t5 ON t5.id=t4.project_id WHERE t1.id=#{caseId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "requestParams",column = "id",
            many = @Many(select = "com.auto.repository.ApiRequestParamMapper.findApiRequestParamBy")),
            @Result(property = "testRules",column = "id",
            many = @Many(select = "com.auto.repository.TestRuleMapper.findTestRulesBy"))
    })
    CaseEditVO findCaseEditVO(String caseId);


}
