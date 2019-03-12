package com.tmg.lesson9.front.controller.profile;

import com.tmg.lesson9.facade.user.UserFacade;
import com.tmg.lesson9.front.data.SessionUserData;
import com.tmg.lesson9.front.form.ProfileForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class ProfileController {

    @Resource
    private UserFacade defaultUserFacadeImpl;
    @Resource
    private SessionUserData sessionUserData;

    @RequestMapping(value = "/profile/{profileName:.+}", method = RequestMethod.GET)
    public String showUser (@PathVariable String profileName, Model model){
        ProfileForm profileForm = defaultUserFacadeImpl.getProfile(profileName);
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
}
