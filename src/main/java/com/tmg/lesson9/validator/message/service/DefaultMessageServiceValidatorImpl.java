package com.tmg.lesson9.validator.message.service;

import com.tmg.lesson9.service.exception.CustomServiceException;
import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.validator.base.BaseStringFieldValidator;
import com.tmg.lesson9.validator.base.message.BaseMessageValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class DefaultMessageServiceValidatorImpl implements MessageServiceValidator {

    @Resource
    BaseMessageValidator defaultBaseMessageValidatorImpl;
    @Resource
    BaseStringFieldValidator defaultBaseStringFieldValidatorImpl;

    @Override
    public boolean isMessageCreatorValid(String creator) throws CustomServiceException {
        try{
            return defaultBaseMessageValidatorImpl.isMessageCreatorValid(creator);
        } catch (IllegalArgumentException e){
            throw new CustomServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isMessageModelValid(MessageModel messageModel) throws CustomServiceException {
        try{
            defaultBaseMessageValidatorImpl.isMessageModelValid(messageModel);
            // valid business logic
            defaultBaseStringFieldValidatorImpl.isStringFieldsValidByLength(5, 50, messageModel.getMessageTopic());
            defaultBaseStringFieldValidatorImpl.isStringFieldsValidByLength(10, 200, messageModel.getMessageBody());
            return true;
        } catch (IllegalArgumentException e){
            throw new CustomServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isMessageModelListValid(List<MessageModel> messageModelList) throws CustomServiceException {
        try {
            return defaultBaseMessageValidatorImpl.isMessageModelListValid(messageModelList);
        } catch (IllegalArgumentException e) {
            throw new CustomServiceException(e.getMessage(), e);
        }
    }
}
