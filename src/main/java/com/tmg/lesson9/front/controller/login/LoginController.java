package com.tmg.lesson9.front.controller.login;

import com.tmg.lesson9.facade.user.UserFacade;
import com.tmg.lesson9.front.data.SessionUserData;
import com.tmg.lesson9.front.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Resource
    private UserFacade defaultUserFacadeImpl;
    @Resource
    private SessionUserData sessionUserData;
    // servlet request
    private HttpServletRequest request;
    // get autowired servlet request
    @Resource
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model){
        model.addAttribute(new LoginForm());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute LoginForm loginForm, Model model){
        request.getRemoteAddr();
        if(defaultUserFacadeImpl.doLogin(loginForm)){
//            add session data - user name and ip
            sessionUserData.setUserName(loginForm.getName());
            sessionUserData.setUserIP(request.getRemoteAddr());
            return "redirect:/home";
        } else {
            String errorMessage = "name or password is wrong";
            model.addAttribute(errorMessage);
            return "/login";
        }
    }
}
