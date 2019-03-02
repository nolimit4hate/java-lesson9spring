package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MyController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHome(Model model){
        model.addAttribute("msg", "MVC PAGE");
        return "helloPage";
    }
    @RequestMapping(value = "/123", method = RequestMethod.GET)
    public String showHome2(Model model) {
        model.addAttribute("msg", "RABOTAET");
        return "helloPage";
    }

}
