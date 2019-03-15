package com.tmg.lesson9.facade.validator.message;

import com.tmg.lesson9.commons.validator.base.BaseStringFieldValidator;
import com.tmg.lesson9.commons.validator.base.message.BaseMessageValidator;
import com.tmg.lesson9.facade.exception.CustomFacadeException;
import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.web.form.MessageSendForm;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Class implements MessageDaoValidator - specific validator for facade layer
 */

@Component("messageFacadeValidator")
public class DefaultMessageFacadeValidatorImpl implements MessageFacadeValidator {

    @Resource
    BaseMessageValidator baseMessageValidator;
    @Resource
    BaseStringFieldValidator baseStringFieldValidator;

    /**
     * Use implementation of BaseMessageValidator and do specific message creator validation for facade layer
     *
     * @param creator input string value with name of message creator
     * @return true if input string is valid
     * @throws CustomFacadeException if IllegalArgumentException will be thrown
     */

    @Override
    public boolean isMessageCreatorValid(String creator) throws CustomFacadeException {
        try {
            return baseMessageValidator.isMessageCreatorValid(creator);
        } catch (IllegalArgumentException e) {
            throw new CustomFacadeException(e.getMessage(), e);
        }
    }

    /**
     * Use implementation of BaseMessageValidator and do specific MessageModel validation for dao layer
     *
     * @param messageModel MessageModel type object with information about message
     * @return true if input value is valid
     * @throws CustomFacadeException if IllegalArgumentException will be thrown
     */

    @Override
    public boolean isMessageModelValid(MessageModel messageModel) throws CustomFacadeException {
        try {
            return baseMessageValidator.isMessageModelValid(messageModel);
        } catch (IllegalArgumentException e) {
            throw new CustomFacadeException(e.getMessage(), e);
        }
    }

    /**
     * Method do validate for MessageSendForm object. Call isMessageSendFormAllParamsValid() for validate all fiels
     * of MessageSendForm object
     *
     * @param messageSendForm MessageSendForm object that contains information about message
     * @return true if MessageSendForm object valid
     * @throws CustomFacadeException if MessageSendForm is null or invalid
     */

    @Override
    public boolean isMessageSendFormValid(MessageSendForm messageSendForm) throws CustomFacadeException {
        if (messageSendForm == null) {
            throw new CustomFacadeException("messageSendForm cant be null");
        }
        return isMessageSendFormAllParamsValid(messageSendForm);
    }

    /**
     * Method valid all MessageSendForm object fields. messageTopic field length must be from 5 to 63,
     * messageBody field length must be from 10 to 256. Call isMessageCreatorValid() to valid messageCreator
     *
     * @param messageSendForm MessageSendForm object that contains information about message
     * @return true if all MessageSendForm object fields valid
     * @throws CustomFacadeException if any field of MessageSendForm object invalid or if IllegalArgumentException was threw
     */

    private boolean isMessageSendFormAllParamsValid(MessageSendForm messageSendForm) throws CustomFacadeException {
        try {
            isMessageCreatorValid(messageSendForm.getMessageCreator());
            baseStringFieldValidator.isStringFieldsValidByLength(5, 63, messageSendForm.getMessageTopic());
            baseStringFieldValidator.isStringFieldsValidByLength(10, 256, messageSendForm.getMessageBody());
            return true;
        } catch (IllegalArgumentException e) {
            throw new CustomFacadeException(e.getMessage(), e);
        }
    }

    /**
     * Method valid list of MessageModel objects
     *
     * @param messageModelList list of MessageModel objects that contain message data
     * @return true if all MessageModel objects in list are valid
     * @throws CustomFacadeException if any MessageModel object in list is invalid
     */

    @Override
    public boolean isMessageModelListValid(List<MessageModel> messageModelList) throws CustomFacadeException {
        try {
            return baseMessageValidator.isMessageModelListValid(messageModelList);
        } catch (IllegalArgumentException e) {
            throw new CustomFacadeException(e.getMessage(), e);
        }
    }
}
