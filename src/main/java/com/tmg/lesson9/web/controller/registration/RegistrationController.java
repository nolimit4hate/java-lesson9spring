package com.tmg.lesson9.web.controller.registration;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.facade.exception.CustomFacadeException;
import com.tmg.lesson9.facade.user.UserFacade;
import com.tmg.lesson9.service.exception.CustomServiceException;
import com.tmg.lesson9.web.controller.error.ErrorModelViewCreator;
import com.tmg.lesson9.web.form.RegistrationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code RegisterUserController} class is controller that process all requests with url that ends
 * with context path + "/registration" with methods get and post.
 * If request method is get add to user attribute object {@code RegistrationForm} and do
 * follow to registration page(registration.jsp).
 * If request method is post we pass our filled object {@code RegistrationForm} to facade layer with
 * method addUser. If method addUser return true do redirect to login page else do forward to registration
 * page with error massage about wrong input data.
 */

@Controller
public class RegistrationController {

    /**
     * @param facadeUser represent facade layer for user
     */

    @Resource
    private UserFacade userFacade;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registerUser(Model model) {
        model.addAttribute(new RegistrationForm());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("registrationForm") RegistrationForm registrationForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userFacade.addUser(registrationForm);
        String successMessage = "user " + registrationForm.getName() + " was registered !";
        model.addAttribute("successMessage", successMessage);
        return "successPage";
    }

    @ExceptionHandler({CustomFacadeException.class, CustomServiceException.class, CustomDaoException.class})
    public ModelAndView handleCustomFacadeException(HttpServletRequest request, Exception exception) {
        return ErrorModelViewCreator.createErrorModelView(exception, "Registration error");
    }

    /**
     * Method generate list of countries which will be showed in dropdown countries menu
     *
     * @return list of countries
     */
    @ModelAttribute("countryList")
    public List<String> getCountryList() {
        List<String> exerciseList = new ArrayList<>();
        exerciseList.add("Ukraine");
        exerciseList.add("USA");
        exerciseList.add("Other");
        return exerciseList;
    }
}
