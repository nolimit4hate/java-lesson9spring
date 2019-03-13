package com.tmg.lesson9.facade.message;

import com.tmg.lesson9.converter.message.MessageConverter;
import com.tmg.lesson9.facade.exception.CustomFacadeException;
import com.tmg.lesson9.facade.util.DateTimeGetter;
import com.tmg.lesson9.front.form.MessageSendForm;
import com.tmg.lesson9.front.form.MessageShowForm;
import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.service.exception.CustomServiceException;
import com.tmg.lesson9.service.message.MessageService;
import com.tmg.lesson9.validator.message.facade.MessageFacadeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultMessageFacadeImpl implements MessageFacade {

    @Autowired
    private MessageService defaultMessageServiceImpl;
    @Autowired
    private MessageFacadeValidator defaultMessageFacadeValidatorImpl;
    @Autowired
    private MessageConverter defaultMessageConverterImpl;

    @Override
    public List<MessageShowForm> getAllMessagesByCreatorName(String creatorName) throws IllegalArgumentException {
        defaultMessageFacadeValidatorImpl.isMessageCreatorValid(creatorName);
        try {
            List<MessageModel> userMessagesList = defaultMessageServiceImpl.getMessageByCreator(creatorName);
            defaultMessageFacadeValidatorImpl.isMessageModelListValid(userMessagesList);
            List<MessageShowForm> showFormList = defaultMessageConverterImpl.convertListModelToListShowForm(userMessagesList);
            return showFormList;
        } catch (CustomServiceException e) {
            throw new CustomFacadeException(e.getMessage(), e);
        }
    }

    @Override
    public boolean addMessage(MessageSendForm messageSendForm) throws IllegalArgumentException {
        defaultMessageFacadeValidatorImpl.isMessageSendFormValid(messageSendForm);
        MessageModel messageModel = defaultMessageConverterImpl.convertSendFormToModel(messageSendForm);
        messageModel.setDateTimeCreation(DateTimeGetter.getCurrentDateTime());
        try {
            return defaultMessageServiceImpl.addMessage(messageModel);
        } catch (CustomServiceException e) {
            throw new CustomFacadeException(e.getMessage(), e);
        }
    }

    @Override
    public List<MessageShowForm> getAllMessages() throws IllegalArgumentException {
        try {
            List<MessageModel> messageModelList = defaultMessageServiceImpl.getAllMessages();
            defaultMessageFacadeValidatorImpl.isMessageModelListValid(messageModelList);
            return defaultMessageConverterImpl.convertListModelToListShowForm(messageModelList);
        } catch (CustomServiceException e) {
            throw new CustomFacadeException(e.getMessage(), e);
        }
    }
}
