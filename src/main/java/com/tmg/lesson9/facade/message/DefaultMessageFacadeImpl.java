package com.tmg.lesson9.facade.message;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.facade.converter.message.MessageConverter;
import com.tmg.lesson9.facade.exception.CustomFacadeException;
import com.tmg.lesson9.facade.util.DateTimeGetter;
import com.tmg.lesson9.web.form.MessageSendForm;
import com.tmg.lesson9.web.form.MessageShowForm;
import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.service.exception.CustomServiceException;
import com.tmg.lesson9.service.message.MessageService;
import com.tmg.lesson9.facade.validator.message.MessageFacadeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("messageFacade")
public class DefaultMessageFacadeImpl implements MessageFacade {

    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageFacadeValidator messageFacadeValidator;
    @Autowired
    private MessageConverter messageConverter;

    @Override
    public List<MessageShowForm> getAllMessagesByCreatorName(String creatorName) throws CustomFacadeException, CustomServiceException, CustomDaoException {
        messageFacadeValidator.isMessageCreatorValid(creatorName);
        List<MessageModel> userMessagesList = messageService.getMessageByCreator(creatorName);
        messageFacadeValidator.isMessageModelListValid(userMessagesList);
        List<MessageShowForm> showFormList = messageConverter.convertListModelToListShowForm(userMessagesList);
        return showFormList;
    }

    @Override
    public boolean addMessage(MessageSendForm messageSendForm) throws CustomFacadeException, CustomServiceException, CustomDaoException {
        messageFacadeValidator.isMessageSendFormValid(messageSendForm);
        MessageModel messageModel = messageConverter.convertSendFormToModel(messageSendForm);
        messageModel.setDateTimeCreation(DateTimeGetter.getCurrentDateTime());
        return messageService.addMessage(messageModel);
    }

    @Override
    public List<MessageShowForm> getAllMessages() throws CustomFacadeException, CustomServiceException, CustomDaoException {
        List<MessageModel> messageModelList = messageService.getAllMessages();
        messageFacadeValidator.isMessageModelListValid(messageModelList);
        return messageConverter.convertListModelToListShowForm(messageModelList);
    }
}
