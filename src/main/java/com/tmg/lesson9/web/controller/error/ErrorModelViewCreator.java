package com.tmg.lesson9.web.controller.error;

import com.tmg.lesson9.web.data.SessionUserData;
import org.springframework.web.servlet.ModelAndView;

public class ErrorModelViewCreator {

    public static ModelAndView createErrorModelView(SessionUserData sessionUserData, Exception exception, String controllerInfo) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sessionUserData", sessionUserData);
        modelAndView.addObject("exceptionIs", exception);
        modelAndView.addObject("fromController", controllerInfo);
        modelAndView.setViewName("error/generalErrorPage");
        return modelAndView;
    }
}
