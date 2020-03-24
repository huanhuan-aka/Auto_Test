package com.auto.service.impl;

import com.auto.common.ApiClassificationVO;
import com.auto.domain.ApiClassification;
import com.auto.repository.ApiClassificationMapper;
import com.auto.service.ApiClassificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Anna.wang
 * @since 2020-03-05
 */
@Service
public class ApiClassificationServiceImpl extends ServiceImpl<ApiClassificationMapper, ApiClassification> implements ApiClassificationService {

    @Autowired
    ApiClassificationMapper apiClassificationMapper;

    @Override
    public List<ApiClassificationVO> getApiClassificationByProjectId(Integer projectId) {


        return apiClassificationMapper.getApiClassificationByProjectId(projectId);
    }
}
