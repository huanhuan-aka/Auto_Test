package com.auto.repository;

import com.auto.domain.ApiRequestParam;
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
public interface ApiRequestParamMapper extends BaseMapper<ApiRequestParam> {

    @Select("select * from api_request_param where api_id=#{apiId}")
    List<ApiRequestParam> findApiRequestParams(Integer apiId);


    @Select("SELECT DISTINCT t2.*,t1.id valueId,t1.api_request_param_value value FROM case_param_value t1 join api_request_param t2 on t1.api_request_param_id = t2.id where t1.case_id=#{caseId}")
    List<ApiRequestParam> findApiRequestParamBy(String caseId);


}
