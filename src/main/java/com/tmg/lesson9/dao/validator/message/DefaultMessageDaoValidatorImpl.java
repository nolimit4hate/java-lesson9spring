package com.tmg.lesson9.dao.validator.message;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.commons.validator.base.message.BaseMessageValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("messageDaoValidator")
public class DefaultMessageDaoValidatorImpl implements MessageDaoValidator {

    @Resource
    BaseMessageValidator baseMessageValidator;

    @Override
    public boolean isMessageCreatorValid(String creator) throws CustomDaoException {
        try {
            return baseMessageValidator.isMessageCreatorValid(creator);
        } catch (IllegalArgumentException e){
            throw new CustomDaoException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isMessageModelValid(MessageModel messageModel) throws CustomDaoException {
        try{
            return baseMessageValidator.isMessageModelValid(messageModel);
        } catch (IllegalArgumentException e) {
            throw new CustomDaoException(e.getMessage(), e);
        }
    }
}
