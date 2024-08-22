package com.dbs.sg.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CommonException extends RuntimeException{
    private final String errorCode;

    public CommonException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
