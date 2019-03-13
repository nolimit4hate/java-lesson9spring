package com.tmg.lesson9.facade.validator.message;

import com.tmg.lesson9.facade.exception.CustomFacadeException;
import com.tmg.lesson9.web.form.MessageSendForm;
import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.commons.validator.base.BaseStringFieldValidator;
import com.tmg.lesson9.commons.validator.base.message.BaseMessageValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("messageFacadeValidator")
public class DefaultMessageFacadeValidatorImpl implements MessageFacadeValidator {

    @Resource
    BaseMessageValidator baseMessageValidator;
    @Resource
    BaseStringFieldValidator baseStringFieldValidator;

    @Override
    public boolean isMessageCreatorValid(String creator) throws CustomFacadeException {
        try {
            return baseMessageValidator.isMessageCreatorValid(creator);
        } catch (IllegalArgumentException e) {
            throw new CustomFacadeException (e.getMessage(), e);
        }
    }

    @Override
    public boolean isMessageModelValid(MessageModel messageModel) throws CustomFacadeException {
        try {
            return baseMessageValidator.isMessageModelValid(messageModel);
        } catch (IllegalArgumentException e) {
            throw new CustomFacadeException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isMessageSendFormValid(MessageSendForm messageSendForm) throws CustomFacadeException {
        if(messageSendForm == null) {
            throw new CustomFacadeException("messageSendForm cant be null");
        }
        return isMessageSendFormAllParamsValid(messageSendForm);
    }

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

    @Override
    public boolean isMessageModelListValid(List<MessageModel> messageModelList) throws CustomFacadeException {
        try {
            return baseMessageValidator.isMessageModelListValid(messageModelList);
        } catch (IllegalArgumentException e) {
            throw new CustomFacadeException(e.getMessage(), e);
        }
    }
}
