package com.auto.repository;

import com.auto.domain.Suite;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
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
public interface SuiteMapper extends BaseMapper<Suite> {

    @Select("select * from suite where project_id=#{projectId}")
    @Results({
        @Result(property = "id",column = "id"),
        @Result(property = "casesList",column = "id",
        many = @Many(select="com.auto.repository.CasesMapper.findCases"))
    })
    List<Suite> findSuiteAndReleatedCasesBy(Integer projectId);


}
