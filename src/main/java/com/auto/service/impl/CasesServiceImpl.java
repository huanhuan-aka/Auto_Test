package com.auto.service.impl;

import com.auto.common.ApiVO;
import com.auto.common.CaseEditVO;
import com.auto.common.CaseListVO;
import com.auto.domain.ApiRequestParam;
import com.auto.domain.CaseParamValue;
import com.auto.domain.Cases;
import com.auto.domain.TestRule;
import com.auto.repository.CasesMapper;
import com.auto.service.CaseParamValueService;
import com.auto.service.CasesService;
import com.auto.service.TestRuleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class CasesServiceImpl extends ServiceImpl<CasesMapper, Cases> implements CasesService {

    @Autowired
    CaseParamValueService caseParamValueService;
    @Autowired
    CasesMapper casesMapper;
    @Autowired
    TestRuleService testRuleService;

    @Override
    public void addToSuite(Cases caseVO, ApiVO apiRunVO){
        //添加cases表
        this.save(caseVO);
        //添加case_param_value表
        List<ApiRequestParam> requestParams = apiRunVO.getRequestParams();
        List<CaseParamValue> caseParamValues = new ArrayList<>();
        for (ApiRequestParam param: requestParams) {
            CaseParamValue caseParamValue = new CaseParamValue();
            caseParamValue.setCaseId(caseVO.getId());
            caseParamValue.setApiRequestParamId(param.getId());
            caseParamValue.setApiRequestParamValue(param.getValue());

            caseParamValues.add(caseParamValue);
        }
        caseParamValueService.saveBatch(caseParamValues);

    }


    @Override
    public List<CaseListVO> showCaseUnderProject(Integer projectId) {
        return casesMapper.showCaseUnderProject(projectId);
    }

    @Override
    public List<CaseListVO> showCaseUnderSuite(String suiteId) {
        return casesMapper.showCaseUnderSuite(suiteId);
    }

    @Override
    public CaseEditVO findCaseEditVO(String caseId){
        return casesMapper.findCaseEditVO(caseId);
    }

    @Override
    public void updateCase(CaseEditVO caseEditVO){
        updateById(caseEditVO);  //按主键更新case表
        List<ApiRequestParam> requestParams = caseEditVO.getRequestParams();

        List<CaseParamValue> caseParamValues = new ArrayList<>();
        for (ApiRequestParam requestParam:requestParams
             ) {
            CaseParamValue paramValue = new CaseParamValue();
            paramValue.setId(requestParam.getValueId());  //case_param_value表中的id
            paramValue.setApiRequestParamId(requestParam.getId()); //case_param_value表中的api_request_param_id
            paramValue.setApiRequestParamValue(requestParam.getValue()); //case_param_value表中的api_request_param_value
            paramValue.setCaseId(caseEditVO.getId());//case_param_value表中的case_id

            caseParamValues.add(paramValue);
        }
        caseParamValueService.updateBatchById(caseParamValues); //更新case_param_value表

        //先把原来的testRule删除,再插入新的testRule--更新test_rule表
        QueryWrapper queryCondition = new QueryWrapper();
        queryCondition.eq("case_id",caseEditVO.getId());
        testRuleService.remove(queryCondition);

        List<TestRule> testRules = caseEditVO.getTestRules();
        testRuleService.saveBatch(testRules);


    }

}

