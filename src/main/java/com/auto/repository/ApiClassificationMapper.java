package com.auto.repository;

import com.auto.common.ApiClassificationVO;
import com.auto.domain.ApiClassification;
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
public interface ApiClassificationMapper extends BaseMapper<ApiClassification> {


    @Select("select * from api_classification where project_id=#{projectId}") //查分类表
    @Results({
            @Result(property = "id", column = "id"),  //没有这一行代码,接口返回的id是null
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "apiList", column = "id",
                    many = @Many(select = "com.auto.repository.ApiMapper.getApisByApiClassificationId"))
    })
    public List<ApiClassificationVO> getApiClassificationByProjectId(Integer projectId);



}
