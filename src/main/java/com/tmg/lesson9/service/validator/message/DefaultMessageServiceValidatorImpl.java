package com.tmg.lesson9.service.validator.message;

import com.tmg.lesson9.commons.validator.base.BaseStringFieldValidator;
import com.tmg.lesson9.commons.validator.base.message.BaseMessageValidator;
import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.service.exception.CustomServiceException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("messageServiceValidator")
public class DefaultMessageServiceValidatorImpl implements MessageServiceValidator {

    @Resource
    BaseMessageValidator baseMessageValidator;
    @Resource
    BaseStringFieldValidator baseStringFieldValidator;

    @Override
    public boolean isMessageCreatorValid(String creator) throws CustomServiceException {
        try {
            return baseMessageValidator.isMessageCreatorValid(creator);
        } catch (IllegalArgumentException e) {
            throw new CustomServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isMessageModelValid(MessageModel messageModel) throws CustomServiceException {
        try {
            baseMessageValidator.isMessageModelValid(messageModel);
            // valid business logic
            baseStringFieldValidator.isStringFieldsValidByLength(5, 50, messageModel.getMessageTopic());
            baseStringFieldValidator.isStringFieldsValidByLength(10, 200, messageModel.getMessageBody());
            return true;
        } catch (IllegalArgumentException e) {
            throw new CustomServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isMessageModelListValid(List<MessageModel> messageModelList) throws CustomServiceException {
        try {
            return baseMessageValidator.isMessageModelListValid(messageModelList);
        } catch (IllegalArgumentException e) {
            throw new CustomServiceException(e.getMessage(), e);
        }
    }
}
