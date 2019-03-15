package com.tmg.lesson9.web.controller.home;

import com.tmg.lesson9.web.data.SessionUserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class HomeController {

    @Resource
    private SessionUserData sessionUserData;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String goHome(Model model) {
        if (sessionUserData != null && sessionUserData.getUserName() != null)
            model.addAttribute("userName", sessionUserData.getUserName());
        model.addAttribute("userIp", sessionUserData.getUserIP());
        return "home";
    }


}
