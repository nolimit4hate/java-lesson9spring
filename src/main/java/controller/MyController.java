package controller;

import model.User;
import model.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MyController {
    @Autowired
    ApplicationContext context;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHome(Model model){
        model.addAttribute("msg", "MVC PAGE");
        return "helloPage";
    }
    @RequestMapping(value = "/123", method = RequestMethod.GET)
    public String showHome2(Model model) {
        model.addAttribute("usersList", (ArrayList<User>) context.getBean("usersList"));
        model.addAttribute("messagesList", (ArrayList<UserMessage>) context.getBean("messagesList"));
        return "helloPage";
    }

}
