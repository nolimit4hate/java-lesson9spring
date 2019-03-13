package com.tmg.lesson9.web.controller.message;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.facade.exception.CustomFacadeException;
import com.tmg.lesson9.facade.message.MessageFacade;
import com.tmg.lesson9.service.exception.CustomServiceException;
import com.tmg.lesson9.web.controller.error.ErrorModelViewCreator;
import com.tmg.lesson9.web.form.MessageShowForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MessageShowController {

    @Resource
    private MessageFacade messageFacade;

    @GetMapping(value = "/profile/{profileName:.+}/messages")
    public String showUserMessage(@PathVariable String profileName, Model model){
        List<MessageShowForm> messageShowFormList = messageFacade.getAllMessagesByCreatorName(profileName);
        model.addAttribute("showUser", profileName);
        if(messageShowFormList == null || messageShowFormList.isEmpty()){
            model.addAttribute("noMessages", "No messages");
        } else {
            model.addAttribute(messageShowFormList);
        }
        return "showMessages";
    }

    @ExceptionHandler({CustomFacadeException.class, CustomServiceException.class, CustomDaoException.class})
    public ModelAndView handleFacadeException(HttpServletRequest request, Exception exception){
        return ErrorModelViewCreator.createErrorModelView(exception, "Show message error");
    }
}
