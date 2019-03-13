package com.tmg.lesson9.web.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultErrorController {
    @RequestMapping(path = "/error")
    public String handleException(HttpServletRequest request) {
        return "error/generalErrorPage";
    }

}
