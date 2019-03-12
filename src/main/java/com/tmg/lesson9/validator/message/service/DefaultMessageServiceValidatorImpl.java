package com.tmg.lesson9.validator.message.service;

import com.tmg.lesson9.facade.exception.ServiceCustomException;
import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.validator.base.BaseStringFieldValidator;
import com.tmg.lesson9.validator.base.message.BaseMessageValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DefaultMessageServiceValidatorImpl implements MessageServiceValidator {

    @Resource
    BaseMessageValidator defaultBaseMessageValidatorImpl;
    @Resource
    BaseStringFieldValidator defaultBaseStringFieldValidatorImpl;

    @Override
    public boolean isMessageCreatorValid(String creator) throws ServiceCustomException {
        try{
            return defaultBaseMessageValidatorImpl.isMessageCreatorValid(creator);
        } catch (IllegalArgumentException e){
            throw new ServiceCustomException(e.getMessage());
        }
    }

    @Override
    public boolean isMessageModelValid(MessageModel messageModel) throws ServiceCustomException {
        boolean result;
        try{
            result = defaultBaseMessageValidatorImpl.isMessageModelValid(messageModel);
            // valid business logic
            if(result == true && defaultBaseStringFieldValidatorImpl.isStringFieldsValidByLength(5, 50, messageModel.getMessageTopic()) &&
            defaultBaseStringFieldValidatorImpl.isStringFieldsValidByLength(10, 200, messageModel.getMessageBody())){
                return true;
            } else {
                throw new ServiceCustomException("message topic must have length from 5 to 50;" +
                        " message body must have length from 10 to 200");
            }
        } catch (IllegalArgumentException e){
            throw new ServiceCustomException(e.getMessage());
        }
    }
}
