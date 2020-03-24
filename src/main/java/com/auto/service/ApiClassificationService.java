package com.auto.service;

import com.auto.common.ApiClassificationVO;
import com.auto.domain.ApiClassification;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Anna.wang
 * @since 2020-03-05
 */
public interface ApiClassificationService extends IService<ApiClassification> {

    public List<ApiClassificationVO> getApiClassificationByProjectId(Integer projectId);
}
