package com.tmg.lesson9.commons.validator.base.message;

import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.commons.validator.base.BaseDateTimeValidator;
import com.tmg.lesson9.commons.validator.base.BaseNameValidator;
import com.tmg.lesson9.commons.validator.base.BaseStringFieldValidator;
import com.tmg.lesson9.commons.validator.base.impl.DefaultBaseDateTimeValidatorImpl;
import com.tmg.lesson9.commons.validator.base.impl.DefaultBaseNameValidatorImpl;
import com.tmg.lesson9.commons.validator.base.impl.DefaultBaseStringFieldValidatorImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("baseMessageValidator")
public class DefaultBaseMessageValidatorImpl implements BaseMessageValidator {

    BaseNameValidator baseNameValidator = new DefaultBaseNameValidatorImpl();
    BaseStringFieldValidator baseStringFieldValidator = new DefaultBaseStringFieldValidatorImpl();
    BaseDateTimeValidator baseDateTimeValidator = new DefaultBaseDateTimeValidatorImpl();

    MessageModel message = new MessageModel();


    @Override
    public boolean isMessageModelValid(MessageModel messageModel) throws IllegalArgumentException {
        if(messageModel == null){
            throw new IllegalArgumentException("message model cant be null");
        }
        setMessage(messageModel);
        isAllParamsValid();
        return true;
    }

    private boolean isAllParamsValid() throws IllegalArgumentException {
        isIdValid();
        isTopicValid();
        isBodyValid();
        isCreatorValid();
        isDateTimeValid();
        return true;
    }

    @Override
    public boolean isMessageCreatorValid(String creator) throws IllegalArgumentException {
        return baseNameValidator.isUserNameValid(creator);
    }

    @Override
    public boolean isMessageModelListValid(List<MessageModel> messageModelList) throws IllegalArgumentException {
        if(messageModelList == null){
            throw new IllegalArgumentException("message model list cant be null");
        }
        for(MessageModel messageModel : messageModelList){
            isMessageModelValid(messageModel);
        }
        return true;
    }

    private boolean isIdValid() throws IllegalArgumentException{
        if(message.getId() >= 0){
            return true;
        } else {
            throw new IllegalArgumentException("message id must be >= 0");
        }
    }

    private boolean isTopicValid() throws IllegalArgumentException {
        return baseStringFieldValidator.isStringFieldsValidByLength(5, 63, message.getMessageTopic());
    }

    private boolean isBodyValid() throws IllegalArgumentException {
        return baseStringFieldValidator.isStringFieldsValidByLength(10, 256, message.getMessageBody());
    }

    private boolean isCreatorValid() throws IllegalArgumentException {
        return isMessageCreatorValid(message.getMessageCreator());
    }

    private boolean isDateTimeValid() throws IllegalArgumentException {
        return baseDateTimeValidator.isDateTimeStringValid(message.getDateTimeCreation());
    }

    public MessageModel getMessage() {
        return message;
    }

    public void setMessage(MessageModel message) {
        this.message = message;
    }
}
