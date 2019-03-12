package com.tmg.lesson9.front.controller.registration;

import com.tmg.lesson9.facade.user.UserFacade;
import com.tmg.lesson9.front.form.RegistrationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *  {@code RegisterUserController} class is controller that process all requests with url that ends
 * with context path + "/registration" with methods get and post.
 *  If request method is get add to user attribute object {@code RegistrationForm} and do
 * follow to registration page(registration.jsp).
 *  If request method is post we pass our filled object {@code RegistrationForm} to facade layer with
 * method addUser. If method addUser return true do redirect to login page else do forward to registration
 * page with error massage about wrong input data.
 */

@Controller
public class RegistrationController {

    /**
     *  @param facadeUser represent facade layer for user
     */

    @Resource
    private UserFacade defaultUserFacadeImpl;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registerUser(Model model) {
        model.addAttribute(new RegistrationForm());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addUser(@ModelAttribute RegistrationForm user, Model model) {
        if(defaultUserFacadeImpl.addUser(user)){
            String successMessage = "user " + user.getName() + " was registered !";
            model.addAttribute("successMessage", successMessage);
            return "successPage";
        } else {
            String errorMessage = "Something goes wrong";
            model.addAttribute(errorMessage);
            return "registration";
        }
    }

    /**
     *  Method generate list of countries which will be showed in dropdown countries menu
     *
     * @return list of countries
     */
    @ModelAttribute("countryList")
    public List<String> getCountryList(){
        List<String> exerciseList = new ArrayList<>();
        exerciseList.add("Ukraine");
        exerciseList.add("USA");
        exerciseList.add("Other");
        return exerciseList;
    }
}