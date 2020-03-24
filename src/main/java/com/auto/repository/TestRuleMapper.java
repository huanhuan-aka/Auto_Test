package com.auto.repository;

import com.auto.domain.TestRule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Anna.wang
 * @since 2020-03-05
 */
public interface TestRuleMapper extends BaseMapper<TestRule> {

    @Select("select * from test_rule where case_id=#{caseId}")
    public List<TestRule> findTestRulesBy(String caseId);

}
