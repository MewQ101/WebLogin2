package com.example.demo.handlers;

import com.example.demo.exceptions.AlreadyUsed;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class AlreadyUsedEmailExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AlreadyUsed.class)
    public ModelAndView handleAlreadyUsedException(AlreadyUsed ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage", ex.getMessage());
        modelAndView.setViewName("error_used");
        return modelAndView;
    }
}
