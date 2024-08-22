package com.dbs.sg.exception;

import com.dbs.sg.ErrorCode;
import com.dbs.sg.builder.GitRepoDetailsBuilder;
import com.dbs.sg.dto.GenericResponse;
import com.dbs.sg.model.GitRepoDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final GitRepoDetailsBuilder gitRepoDetailsBuilder;
    @ExceptionHandler(value = CommonException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<GenericResponse<CommonErrorResponse>> handleCommonException(CommonException exception){
        CommonErrorResponse response = new CommonErrorResponse(exception.getErrorCode(), exception.getMessage());
        return new ResponseEntity<>(gitRepoDetailsBuilder.buildExceptionGenericResponse(response), HttpStatus.ACCEPTED);
    }
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<CommonErrorResponse> UnhandledException(Exception e){
        CommonErrorResponse response = new CommonErrorResponse(ErrorCode.UN_HANDLED.getCode(),
                ErrorCode.UN_HANDLED.getMessage()+"  -----NOT PROCESSED-----    "+e.getMessage());
        log.error("Error: "+ e);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
