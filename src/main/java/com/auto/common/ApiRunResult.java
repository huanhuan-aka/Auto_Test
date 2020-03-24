package com.auto.common;

import lombok.Data;

@Data
public class ApiRunResult {
    private String statusCode;
    private String headers; //HttpHeaders是MultiValueMap,需要转string
    private String body;
}
