package com.tmg.lesson9.facade.message;

import com.tmg.lesson9.converter.message.MessageConverter;
import com.tmg.lesson9.converter.util.MessageChecker;
import com.tmg.lesson9.facade.util.DateTimeGetter;
import com.tmg.lesson9.front.form.MessageSendForm;
import com.tmg.lesson9.front.form.MessageShowForm;
import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class DefaultMessageFacadeImpl implements MessageFacade {

    @Autowired
    private MessageService defaultMessageServiceImpl;
    @Autowired
    private MessageConverter defaultMessageConverterImpl;

    @Override
    public List<MessageShowForm> getAllMessagesByCreatorName(String creatorName) {
        if(creatorName == null) {
            return null;
        }
        List<MessageModel> userMessagesList = defaultMessageServiceImpl.getMessageByCreator(creatorName);
        List<MessageShowForm> showFormList = defaultMessageConverterImpl.convertListModelToListShowForm(userMessagesList);
        return showFormList;
    }

    @Override
    public boolean addMessage(MessageSendForm messageSendForm) {
        if(messageSendForm == null){
            return false;
        }
        MessageModel messageModel = defaultMessageConverterImpl.convertSendFormToModel(messageSendForm);
        if (messageModel == null) {
            return false;
        }
        messageModel.setDateTimeCreation(DateTimeGetter.getCurrentDateTime());
        return defaultMessageServiceImpl.addMessage(messageModel);
    }

    @Override
    public List<MessageShowForm> getAllMessages() {
        List<MessageShowForm> result;
        List<MessageModel> messageModelList = defaultMessageServiceImpl.getAllMessages();
        result = defaultMessageConverterImpl.convertListModelToListShowForm(messageModelList);
        return result;
    }
}
