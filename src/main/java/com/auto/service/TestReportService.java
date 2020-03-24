package com.auto.service;

import com.auto.common.ReportVO;
import com.auto.domain.TestReport;
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
public interface TestReportService extends IService<TestReport> {

    List<TestReport> runBySuite(Integer suiteId);
    TestReport findByCase(Integer caseId);

    ReportVO getReport(Integer suiteId);
}
