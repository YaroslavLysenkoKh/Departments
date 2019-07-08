package com.aimprosoft.noormal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class ExceptionHandlingController {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(Exception exception) {
        String errorMessage = exception.getMessage();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage", errorMessage);
        modelAndView.setViewName("/errorPage");
        return modelAndView;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public Map<String, String> handleError(ValidationException exception) {
        Map<String, String> errors = exception.getErrorMap();
        return errors;
    }
}
