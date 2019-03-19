package com.tmg.lesson9.web.controller.profile;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.facade.exception.CustomFacadeException;
import com.tmg.lesson9.facade.user.UserFacade;
import com.tmg.lesson9.service.exception.CustomServiceException;
import com.tmg.lesson9.web.controller.error.ErrorModelViewCreator;
import com.tmg.lesson9.web.data.SessionUserData;
import com.tmg.lesson9.web.form.ProfileForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Resource
    private UserFacade userFacade;
    @Resource
    private SessionUserData sessionUserData;

    @RequestMapping(value = "/profile/{profileName:.+}", method = RequestMethod.GET)
    public String showUser(@PathVariable String profileName, Model model) {
        if (sessionUserData != null && sessionUserData.getUserName() != null) {
            model.addAttribute("showEmail", "yes");
        }
        ProfileForm profileForm = userFacade.getProfile(profileName);
        model.addAttribute(profileForm);
        return "userProfile";

    }

    @ExceptionHandler({CustomFacadeException.class, CustomServiceException.class, CustomDaoException.class, Exception.class})
    public ModelAndView handleFacadeException(HttpServletRequest request, Exception exception) {
        return ErrorModelViewCreator.createErrorModelView(sessionUserData, exception, "Profile error");
    }
}
