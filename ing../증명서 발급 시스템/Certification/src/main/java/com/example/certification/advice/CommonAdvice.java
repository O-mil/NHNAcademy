package com.example.certification.advice;

import lombok.extern.slf4j.Slf4j;
import com.example.certification.exception.NotExistCertificateException;
import com.example.certification.exception.NotFoundResidentException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@ControllerAdvice
public class CommonAdvice {

    @InitBinder
    void initBinder (WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }

    @ExceptionHandler(NotExistCertificateException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundResidentException(NotFoundResidentException notFoundResidentException) {
        log.info("error:{}", notFoundResidentException.getMessage(), notFoundResidentException);
        return "error/error";
    }
}
