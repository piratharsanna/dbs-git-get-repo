package com.dbs.sg;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_INPUT("1001", "Invalid input"),
    UN_HANDLED("1501", "We not able process your request right now, we are working to resolve this, any further queries  please contact our support hot line: +6533434444"),
    NOT_FOUND("1112", "Searching git repository details not found");

    private final String code;
    private final String message;
    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
