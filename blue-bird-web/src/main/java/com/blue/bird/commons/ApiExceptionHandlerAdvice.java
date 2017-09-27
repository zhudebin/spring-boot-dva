package com.blue.bird.commons;

import com.blue.bird.enums.ViewCodeEnum;
import com.blue.bird.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static com.blue.bird.enums.ViewCodeEnum.SYSTEM_ERROR;

/**
 * Created by jim on 2017/9/27.
 */
@ControllerAdvice(annotations = RestController.class)
@Slf4j
public class ApiExceptionHandlerAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<ViewCodeEnum> exception(Exception exception, HttpServletResponse response) {
        log.error("Api接口异常:", exception);
        ViewCodeEnum viewCodeEnum = SYSTEM_ERROR;
        if (exception instanceof ApiException) {
            //api异常
            ApiException apiException = (ApiException) exception;
            viewCodeEnum = apiException.getViewCodeEnum();
        }
        return new ResponseEntity<>(viewCodeEnum, HttpStatus.valueOf(response.getStatus()));
    }
}
