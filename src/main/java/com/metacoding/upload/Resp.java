package com.metacoding.upload;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Resp<T> {
    // 간단하게 급조
    private Boolean success;
    private String msg;
    private T data;
}
