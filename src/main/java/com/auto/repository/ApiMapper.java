package com.auto.repository;

import com.auto.common.ApiListVO;
import com.auto.common.ApiVO;
import com.auto.domain.Api;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface ApiMapper extends BaseMapper<Api> {

    @Select("select * from api where api_classification_id=#{apiClassificationId}")
    public List<Api> getApisByApiClassificationId(Integer apiClassificationId);


    @Select("SELECT t1.id,t1.name,t1.method,t1.url,t2.name classificationName FROM api t1,api_classification t2 WHERE t2.project_id=#{projectId} and t1.api_classification_id=t2.id")
    public List<ApiListVO> showApiUnderProject(Integer projectId);

    @Select("SELECT t1.id,t1.name,t1.method,t1.url,t2.name classificationName FROM api t1,api_classification t2 WHERE t2.id=#{apiClassificationId} and t1.api_classification_id=t2.id")
    public List<ApiListVO> showApiUnderApiClassification(Integer apiClassificationId);


    @Select("select t1.*,t2.username createUserName from api t1,user t2 where t1.id=#{apiId} and t1.create_user=t2.id")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "requestParams",column = "id",
            many = @Many(select = "com.auto.repository.ApiRequestParamMapper.findApiRequestParams"))
    })
    public ApiVO findApiViewVO(Integer apiId);
}

