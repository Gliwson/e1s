package io.github.e1s.components.discount.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotFoundDiscountAdvice {

    @ResponseBody
    @ExceptionHandler(NotFoundDiscountException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundDiscountAdvice(NotFoundDiscountException ex) {
        return ex.getMessage();
    }
}
