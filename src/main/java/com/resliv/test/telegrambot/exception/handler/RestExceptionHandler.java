package com.resliv.test.telegrambot.exception.handler;

import com.resliv.test.telegrambot.exception.DeleteException;
import com.resliv.test.telegrambot.exception.ExceptionResponse;
import com.resliv.test.telegrambot.exception.SaveException;
import com.resliv.test.telegrambot.exception.UpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Map<Class<?>, String> EXCEPTION_TITLE = new HashMap<>();
    private static final int ERROR_CODE = 400;

    static {
        EXCEPTION_TITLE.put(DeleteException.class, "DeleteException");
        EXCEPTION_TITLE.put(SaveException.class, "SaveException");
        EXCEPTION_TITLE.put(UpdateException.class, "UpdateException");
    }

    private static String getTitleException(RuntimeException ex) {
        return EXCEPTION_TITLE.get(ex.getClass());
    }

    @ExceptionHandler({RuntimeException.class})
    private ResponseEntity<ExceptionResponse> runtimeExceptionHandler(RuntimeException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setTitle(getTitleException(ex));
        exceptionResponse.setMessage(ex.getMessage());
        exceptionResponse.setErrorCode(ERROR_CODE);
        exceptionResponse.setHttpStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
