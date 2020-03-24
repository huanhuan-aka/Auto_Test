package com.auto.service;

import com.auto.common.ApiListVO;
import com.auto.common.ApiRunResult;
import com.auto.common.ApiVO;
import com.auto.common.ResponseResult;
import com.auto.domain.Api;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Anna.wang
 * @since 2020-03-05
 */
public interface ApiService extends IService<Api> {

    public List<ApiListVO> showApiUnderProject(Integer projectId);

    public List<ApiListVO> showApiUnderApiClassification(Integer apiClassificationId);

    public ApiVO findApiViewVO(Integer apiId);

    ApiRunResult run(ApiVO apiVO);

    public void editApi(ApiVO apiVO);
}
