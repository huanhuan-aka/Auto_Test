package com.auto.service.impl;

import com.auto.domain.Suite;
import com.auto.repository.SuiteMapper;
import com.auto.service.SuiteService;
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
public class SuiteServiceImpl extends ServiceImpl<SuiteMapper, Suite> implements SuiteService {

    @Autowired
    SuiteMapper suiteMapper;

    @Override
    public List<Suite> findSuiteAndReleatedCasesBy(Integer projectId){
        return suiteMapper.findSuiteAndReleatedCasesBy(projectId);
    }
}
