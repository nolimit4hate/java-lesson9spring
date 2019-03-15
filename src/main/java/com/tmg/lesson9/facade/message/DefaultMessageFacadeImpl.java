package com.tmg.lesson9.facade.message;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.facade.converter.message.MessageConverter;
import com.tmg.lesson9.facade.exception.CustomFacadeException;
import com.tmg.lesson9.facade.util.DateTimeGetter;
import com.tmg.lesson9.facade.validator.message.MessageFacadeValidator;
import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.service.exception.CustomServiceException;
import com.tmg.lesson9.service.message.MessageService;
import com.tmg.lesson9.web.form.MessageSendForm;
import com.tmg.lesson9.web.form.MessageShowForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Class have methods that processing input from controller message data then validate and convert it to needed format for service layer
 * and send it to service layer.
 * Then validate gotten data from service layer and send it to controller that call facade method.
 * If any input data is invalidate then throw CustomFacadeException.
 */

@Component("messageFacade")
public class DefaultMessageFacadeImpl implements MessageFacade {

    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageFacadeValidator messageFacadeValidator;
    @Autowired
    private MessageConverter messageConverter;

    /**
     * Method validate @param creatorName and call messageService.getMessageByCreator()
     * service method for getting list of all MessageModel objects with current creator name.
     *
     * @param creatorName string value with message creator name
     * @return list of MessageShowForm objects for MessageModel presentation
     * @throws CustomFacadeException  if input data @param creatorName is invalid or if list of MessageModel getting from
     *                                service layer is invalid
     * @throws CustomServiceException exceptions from service layer
     * @throws CustomDaoException     exceptions from dao layer
     */

    @Override
    public List<MessageShowForm> getAllMessagesByCreatorName(String creatorName) throws CustomFacadeException, CustomServiceException, CustomDaoException {
        messageFacadeValidator.isMessageCreatorValid(creatorName);
        List<MessageModel> userMessagesList = messageService.getMessageByCreator(creatorName);
        messageFacadeValidator.isMessageModelListValid(userMessagesList);
        List<MessageShowForm> showFormList = messageConverter.convertListModelToListShowForm(userMessagesList);
        return showFormList;
    }

    /**
     * Method validate @param messageSendForm then convert MessageSendForm object to MessageModel object. Add
     * Information about current date-time to MessageModel in string type with format 'yyyy-MM-dd hh:mm:ss'.
     * Call service layer method messageConverter.convertSendFormToModel() with converted MessageModel as param.
     * Return result of calling method from service layer.
     *
     * @param messageSendForm
     * @return true if messageSendForm information was added to database
     * @throws CustomFacadeException  is @param messageSendForm is invalidate
     * @throws CustomServiceException exceptions from service layer
     * @throws CustomDaoException     exceptions from dao layer
     */

    @Override
    public boolean addMessage(MessageSendForm messageSendForm) throws CustomFacadeException, CustomServiceException, CustomDaoException {
        messageFacadeValidator.isMessageSendFormValid(messageSendForm);
        MessageModel messageModel = messageConverter.convertSendFormToModel(messageSendForm);
        messageModel.setDateTimeCreation(DateTimeGetter.getCurrentDateTime());
        return messageService.addMessage(messageModel);
    }

    /**
     * This method call service layer method messageService.getAllMessages(). Validate list of MessageModel object then
     * Convert list of UserModel objects to list of MessageShowForm objects for presentation UserModel objects in jsp.
     *
     * @return list of MessageShowForm objects for presentation UserModel objects
     * @throws CustomFacadeException  if any UserModel object in list of UserModel objects getting from messageService.getAllMessages()
     *                                is invalid
     * @throws CustomServiceException exceptions from service layer
     * @throws CustomDaoException     exceptions from dao layer
     */

    @Override
    public List<MessageShowForm> getAllMessages() throws CustomFacadeException, CustomServiceException, CustomDaoException {
        List<MessageModel> messageModelList = messageService.getAllMessages();
        messageFacadeValidator.isMessageModelListValid(messageModelList);
        return messageConverter.convertListModelToListShowForm(messageModelList);
    }
}
