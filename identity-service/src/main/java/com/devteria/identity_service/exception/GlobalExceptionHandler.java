package com.devteria.identity_service.exception;

import com.devteria.identity_service.dto.request.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value=BusinessException.class)
    ResponseEntity<ApiResponse> handlingBusinessException(BusinessException exception) {
        ErrorCode errorCode = exception.getErrorCode();

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(errorCode.getMessage());
        apiResponse.setCode(errorCode.getCode());

        return ResponseEntity.badRequest().body(apiResponse);
    }


    @ExceptionHandler(value=Exception.class)
    ResponseEntity<ApiResponse> handleUnknownException(Exception e){
        ApiResponse apiResponse = new ApiResponse();
        ErrorCode unknownError = ErrorCode.UNKNOWN_ERROR;

        apiResponse.setMessage(unknownError.getMessage());
        apiResponse.setCode(unknownError.getCode());

        return ResponseEntity.badRequest().body(apiResponse);
    }


    @ExceptionHandler(value=RuntimeException.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(exception.getMessage());
        apiResponse.setCode(1001);
        return ResponseEntity.badRequest().body(apiResponse);
    }


//    @ExceptionHandler(value=MethodArgumentNotValidException.class)
//    ResponseEntity<String> handlingNotValidException(MethodArgumentNotValidException exception) {
//        return ResponseEntity.badRequest().body(exception.getFieldError().getDefaultMessage());
//    }

    @ExceptionHandler(value=MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingNotValidException(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();

        ErrorCode errorCode = ErrorCode.ENUM_KEY_INVALID;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException e) {

        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(errorCode.getMessage());
        apiResponse.setCode(errorCode.getCode());


        return ResponseEntity.badRequest().body(apiResponse);
    }

}
