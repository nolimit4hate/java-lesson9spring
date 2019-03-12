package com.tmg.lesson9.validator.message.facade;

import com.tmg.lesson9.front.form.MessageSendForm;
import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.validator.base.BaseStringFieldValidator;
import com.tmg.lesson9.validator.base.message.BaseMessageValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DefaultMessageFacadeValidatorImpl implements MessageFacadeValidator {

    @Resource
    BaseMessageValidator defaultBaseMessageValidatorImpl;
    @Resource
    BaseStringFieldValidator defaultBaseStringFieldValidatorImpl;

    @Override
    public boolean isMessageCreatorValid(String creator) throws IllegalArgumentException {
        return defaultBaseMessageValidatorImpl.isMessageCreatorValid(creator);
    }

    @Override
    public boolean isMessageModelValid(MessageModel messageModel) throws IllegalArgumentException {
        return defaultBaseMessageValidatorImpl.isMessageModelValid(messageModel);
    }

    @Override
    public boolean isMessageSendFormValid(MessageSendForm messageSendForm) throws IllegalArgumentException {
        if(messageSendForm != null && isMessageCreatorValid(messageSendForm.getMessageCreator()) &&
        defaultBaseStringFieldValidatorImpl.isStringFieldsValidByLength(5, 63, messageSendForm.getMessageTopic()) &&
        defaultBaseStringFieldValidatorImpl.isStringFieldsValidByLength(10, 256, messageSendForm.getMessageBody())){
            return true;
        } else {
            return false;
        }
    }
}
