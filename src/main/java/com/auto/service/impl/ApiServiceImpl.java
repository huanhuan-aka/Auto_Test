package com.auto.service.impl;

import com.alibaba.fastjson.JSON;
import com.auto.common.ApiListVO;
import com.auto.common.ApiRunResult;
import com.auto.common.ApiVO;
import com.auto.common.ResponseResult;
import com.auto.domain.Api;
import com.auto.domain.ApiRequestParam;
import com.auto.repository.ApiMapper;
import com.auto.service.ApiRequestParamService;
import com.auto.service.ApiService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

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
public class ApiServiceImpl extends ServiceImpl<ApiMapper, Api> implements ApiService {

    @Autowired
    ApiMapper apiMapper;
    @Autowired
    ApiRequestParamService apiRequestParamService;

    @Override
    public List<ApiListVO> showApiUnderProject(Integer projectId){

        return apiMapper.showApiUnderProject(projectId);

    }

    @Override
    public List<ApiListVO> showApiUnderApiClassification(Integer apiClassificationId){
        return apiMapper.showApiUnderApiClassification(apiClassificationId);
    }

    @Override
    public ApiVO findApiViewVO(Integer apiId){
        return apiMapper.findApiViewVO(apiId);
    }

    @Override
    public ApiRunResult run(ApiVO apiVO){
        //远程调用 resttemplate
        RestTemplate restTemplate = new RestTemplate();
        String url = apiVO.getHost()+apiVO.getUrl();
        String method = apiVO.getMethod();
        LinkedMultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        LinkedMultiValueMap<String,String> bodyParams = new LinkedMultiValueMap<>();
        List<ApiRequestParam> requestParams = apiVO.getRequestParams();
        String queryStr = "?";
        for (ApiRequestParam param: requestParams) {
            if(param.getType()==3){ //headers
                headers.add(param.getName(),param.getValue());
            }else if(param.getType()==1){
                queryStr += param.getName()+"="+param.getValue()+"&";

            }else{
                //body  没有处理type=1时的情况
                bodyParams.add(param.getName(),param.getValue());
            }
        }
        if(queryStr.equals("?")&&queryStr.lastIndexOf("&")>0){
            queryStr = queryStr.substring(0,queryStr.lastIndexOf("&"));
        }

        HttpEntity httpEntity = null;
        ResponseEntity response = null;
        ApiRunResult apiRunResult = new ApiRunResult();
        try { //发送请求(此时有调用异常,往往没有body,需要try catch一下)
            if("get".equalsIgnoreCase(method)){
                httpEntity = new HttpEntity(headers);
                response = restTemplate.exchange(url+queryStr, HttpMethod.GET,httpEntity,String.class);
            }else if("post".equalsIgnoreCase(method)){
                httpEntity = new HttpEntity(bodyParams,headers);
                response = restTemplate.exchange(url, HttpMethod.POST,httpEntity,String.class);
            }
            //将接口响应返回的结果赋值给ApiRunResult对象---statusCode/headers/body

            apiRunResult.setStatusCode(response.getStatusCodeValue()+"");
            apiRunResult.setHeaders(JSON.toJSONString(response.getHeaders()));
            apiRunResult.setBody(response.getBody().toString());
        } catch (HttpStatusCodeException e) {
            //如果有异常,就将异常的返回数据赋值给apiRunResult对象
            apiRunResult.setStatusCode(e.getRawStatusCode()+"");
            apiRunResult.setHeaders(JSON.toJSONString(e.getResponseHeaders()));
            apiRunResult.setBody(e.getResponseBodyAsString());

        }
        return apiRunResult;
    }

    @Override
    public void editApi(ApiVO apiVO){
        //更新api表
        this.updateById(apiVO);

        //根据apiId进行参数更新(api_request_param)-->如果已存在,先删除再新增
        QueryWrapper queryCondition = new QueryWrapper();
        queryCondition.eq("api_id",apiVO.getId());
        apiRequestParamService.remove(queryCondition);

        //--判断一下,删除list中为null的对象

        //将apiVO中不同位置的参数统一加到requestParam中
        apiVO.getRequestParams().addAll(apiVO.getHeaderParams());
        apiVO.getRequestParams().addAll(apiVO.getQueryParams());
        apiVO.getRequestParams().addAll(apiVO.getBodyParams());
        apiVO.getRequestParams().addAll(apiVO.getBodyRawParams());

        //更新
        apiRequestParamService.saveBatch(apiVO.getRequestParams());

    }

}
