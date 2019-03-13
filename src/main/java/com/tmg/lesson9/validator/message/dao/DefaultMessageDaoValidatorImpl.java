package com.tmg.lesson9.validator.message.dao;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.validator.base.message.BaseMessageValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DefaultMessageDaoValidatorImpl implements MessageDaoValidator {

    @Resource
    BaseMessageValidator defaultBaseMessageValidatorImpl;

    @Override
    public boolean isMessageCreatorValid(String creator) throws CustomDaoException {
        try {
            return defaultBaseMessageValidatorImpl.isMessageCreatorValid(creator);
        } catch (IllegalArgumentException e){
            throw new CustomDaoException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isMessageModelValid(MessageModel messageModel) throws CustomDaoException {
        try{
            return defaultBaseMessageValidatorImpl.isMessageModelValid(messageModel);
        } catch (IllegalArgumentException e) {
            throw new CustomDaoException(e.getMessage(), e);
        }
    }
}
