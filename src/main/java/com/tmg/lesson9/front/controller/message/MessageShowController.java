package com.tmg.lesson9.front.controller.message;

import com.tmg.lesson9.facade.message.MessageFacade;
import com.tmg.lesson9.front.form.MessageShowForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class MessageShowController {

    @Resource
    private MessageFacade defaultMessageFacadeImpl;

    @GetMapping(value = "/profile/{profileName:.+}/messages")
    public String showUserMessage(@PathVariable String profileName, Model model){
        List<MessageShowForm> messageShowFormList = defaultMessageFacadeImpl.getAllMessagesByCreatorName(profileName);
        model.addAttribute("showUser", profileName);
        if(messageShowFormList == null || messageShowFormList.isEmpty()){
            model.addAttribute("noMessages", "No messages");
        } else {
            model.addAttribute(messageShowFormList);
        }
        return "showMessages";
    }
}
