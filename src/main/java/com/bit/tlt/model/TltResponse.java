package com.bit.tlt.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class TltResponse<T> {

    private T body;

    private HttpStatus httpStatus;

    private int httpStatusCode;

    private String message;

}
