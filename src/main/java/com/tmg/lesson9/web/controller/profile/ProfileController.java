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
    public String showUser (@PathVariable String profileName, Model model){
        ProfileForm profileForm = userFacade.getProfile(profileName);
        if(profileForm != null){
            model.addAttribute(profileForm);
            if(sessionUserData != null && sessionUserData.getUserName() != null &&
                    sessionUserData.getUserName().equals(profileName)){
                model.addAttribute("showEmail", "yes");
            }
            return "userProfile";
        } else {
            String errorMessage = "Something goes wrong";
            model.addAttribute(errorMessage);
            return "userProfile";
        }
    }

    @ExceptionHandler({CustomFacadeException.class, CustomServiceException.class, CustomDaoException.class})
    public ModelAndView handleFacadeException(HttpServletRequest request, Exception exception){
        return ErrorModelViewCreator.createErrorModelView(exception, "Profile error");
    }
}
