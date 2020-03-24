package com.auto.service;

import com.auto.domain.Suite;
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
public interface SuiteService extends IService<Suite> {

    List<Suite> findSuiteAndReleatedCasesBy(Integer projectId);

}
