package com.tmg.lesson9.web.controller.error;

import org.springframework.web.servlet.ModelAndView;

public class ErrorModelViewCreator {

    public static ModelAndView createErrorModelView(Exception exception, String controllerInfo) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exceptionIs", exception);
        modelAndView.addObject("fromController", controllerInfo);
        modelAndView.setViewName("error/generalErrorPage");
        return modelAndView;
    }
}
