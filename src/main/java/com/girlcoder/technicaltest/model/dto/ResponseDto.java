package com.girlcoder.technicaltest.model.dto;

import lombok.Data;

@Data
public class ResponseDto<T> {
    private int responseCode;

    private String responseMessage;

    private T responseData;
}
