package com.tmg.lesson9.web.controller.message;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.facade.exception.CustomFacadeException;
import com.tmg.lesson9.facade.message.MessageFacade;
import com.tmg.lesson9.service.exception.CustomServiceException;
import com.tmg.lesson9.web.controller.error.ErrorModelViewCreator;
import com.tmg.lesson9.web.data.SessionUserData;
import com.tmg.lesson9.web.form.MessageSendForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MessageSendController {

    @Resource
    SessionUserData sessionUserData;
    @Resource
    MessageFacade messageFacade;

    @GetMapping(value = "/sendMessage")
    public String makeMessage(Model model){
            model.addAttribute(new MessageSendForm(sessionUserData.getUserName()));
            return "sendMassage";
    }

    @PostMapping(value = "/processMessage")
    public String processMessage(@ModelAttribute MessageSendForm messageSendForm){
            messageSendForm.setMessageCreator(sessionUserData.getUserName());
            if(messageFacade.addMessage(messageSendForm)){
                return "sendMessageSuccess";
            } else {
                return "redirect:/sendMessage";
            }

    }

    @ExceptionHandler({CustomFacadeException.class, CustomServiceException.class, CustomDaoException.class})
    public ModelAndView handleFacadeException(HttpServletRequest request, Exception exception){
        return ErrorModelViewCreator.createErrorModelView(exception, "Send message error");
    }
}
