package com.auto.common;

import com.auto.domain.Api;
import com.auto.domain.ApiRequestParam;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ApiVO extends Api {

    private String createUserName;
    private String host;

    private List<ApiRequestParam> requestParams = new ArrayList<>();
    private List<ApiRequestParam> queryParams = new ArrayList<>();
    private List<ApiRequestParam> bodyParams = new ArrayList<>();
    private List<ApiRequestParam> headerParams = new ArrayList<>();
    private List<ApiRequestParam> bodyRawParams = new ArrayList<>();




}
