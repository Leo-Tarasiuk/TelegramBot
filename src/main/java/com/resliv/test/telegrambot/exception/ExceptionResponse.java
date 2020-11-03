package com.resliv.test.telegrambot.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ExceptionResponse {

    private int errorCode;

    private String title;

    private String message;

    private int httpStatus;
}
