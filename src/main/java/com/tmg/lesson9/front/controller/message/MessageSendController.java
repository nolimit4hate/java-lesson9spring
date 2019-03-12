package com.tmg.lesson9.front.controller.message;

import com.tmg.lesson9.facade.message.MessageFacade;
import com.tmg.lesson9.front.data.SessionUserData;
import com.tmg.lesson9.front.form.MessageSendForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

@Controller
public class MessageSendController {

    @Resource
    SessionUserData sessionUserData;
    @Resource
    MessageFacade defaultMessageFacadeImpl;

    @GetMapping(value = "/sendMessage")
    public String makeMessage(Model model){
            model.addAttribute(new MessageSendForm(sessionUserData.getUserName()));
            return "sendMassage";
    }

    @PostMapping(value = "/processMessage")
    public String processMessage(@ModelAttribute MessageSendForm messageSendForm){
            messageSendForm.setMessageCreator(sessionUserData.getUserName());
            if(defaultMessageFacadeImpl.addMessage(messageSendForm)){
                return "sendMessageSuccess";
            } else {
                return "redirect:/sendMessage";
            }

    }
}
