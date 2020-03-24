package com.auto.service;

import com.auto.common.ApiVO;
import com.auto.common.CaseEditVO;
import com.auto.common.CaseListVO;
import com.auto.domain.Cases;
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
public interface CasesService extends IService<Cases> {

     void addToSuite(Cases caseVO, ApiVO apiRunVO);

    List<CaseListVO> showCaseUnderProject(Integer projectId);

    List<CaseListVO> showCaseUnderSuite(String suiteId);

    CaseEditVO findCaseEditVO(String caseId);

    public void updateCase(CaseEditVO caseEditVO);
}
