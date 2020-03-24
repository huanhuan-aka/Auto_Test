package com.auto.common;

import com.auto.domain.Api;
import com.auto.domain.ApiClassification;
import lombok.Data;

import java.util.List;

@Data
public class ApiClassificationVO extends ApiClassification {
    //关联对象
    List<Api> apiList;
}
