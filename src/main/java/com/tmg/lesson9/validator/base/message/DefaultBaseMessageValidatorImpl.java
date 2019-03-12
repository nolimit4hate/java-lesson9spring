package com.tmg.lesson9.validator.base.message;

import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.validator.base.BaseDateTimeValidator;
import com.tmg.lesson9.validator.base.BaseNameValidator;
import com.tmg.lesson9.validator.base.BaseStringFieldValidator;
import com.tmg.lesson9.validator.base.impl.DefaultBaseDateTimeValidatorImpl;
import com.tmg.lesson9.validator.base.impl.DefaultBaseNameValidatorImpl;
import com.tmg.lesson9.validator.base.impl.DefaultBaseStringFieldValidatorImpl;
import org.springframework.stereotype.Component;

@Component
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
        if(isIdValid() && isDateTimeValid() && isTopicValid() && isBodyValid() && isCreatorValid()){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isMessageCreatorValid(String creator) throws IllegalArgumentException {
        return baseNameValidator.isUserNameValid(creator);
    }

    private boolean isIdValid(){
        if(message.getId() >= 0){
            return true;
        } else {
            return false;
        }
    }

    private boolean isTopicValid() throws IllegalArgumentException {
        if(baseStringFieldValidator.isStringFieldsValidByLength(5, 63, message.getMessageTopic())){
            return true;
        } else {
            return false;
        }
    }

    private boolean isBodyValid() throws IllegalArgumentException {
        if(baseStringFieldValidator.isStringFieldsValidByLength(10, 256, message.getMessageBody())){
            return true;
        } else {
            return false;
        }
    }

    private boolean isCreatorValid() throws IllegalArgumentException {
        return isMessageCreatorValid(message.getMessageCreator());
    }

    private boolean isDateTimeValid() throws IllegalArgumentException {
        return baseDateTimeValidator.isDateTimeStringValid(message.getDateTimeCreation());
    }

    public BaseNameValidator getBaseNameValidator() {
        return baseNameValidator;
    }

    public void setBaseNameValidator(BaseNameValidator baseNameValidator) {
        this.baseNameValidator = baseNameValidator;
    }

    public MessageModel getMessage() {
        return message;
    }

    public void setMessage(MessageModel message) {
        this.message = message;
    }
}
