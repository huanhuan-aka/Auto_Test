package com.auto.common;

import com.auto.domain.ApiRequestParam;
import com.auto.domain.Cases;
import com.auto.domain.TestRule;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CaseEditVO extends Cases {
//    private Integer id;  集成Cases类之后,id和name就不需要定义了(Cases里有)
//    private String name;
    private String method;
    private String url;
    private Integer apiId;
    private String host;
    private List<ApiRequestParam> requestParams = new ArrayList<>();

    //增加testrule属性
    private List<TestRule> testRules = new ArrayList<>();

}
