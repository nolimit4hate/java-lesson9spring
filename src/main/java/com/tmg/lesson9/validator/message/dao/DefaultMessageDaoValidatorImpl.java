package com.tmg.lesson9.validator.message.dao;

import com.tmg.lesson9.dao.exception.DaoCustomException;
import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.validator.base.message.BaseMessageValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DefaultMessageDaoValidatorImpl implements MessageDaoValidator {

    @Resource
    BaseMessageValidator defaultBaseMessageValidatorImpl;

    @Override
    public boolean isMessageCreatorValid(String creator) throws DaoCustomException {
        try {
            return defaultBaseMessageValidatorImpl.isMessageCreatorValid(creator);
        } catch (IllegalArgumentException e){
            throw new DaoCustomException(e.getMessage());
        }
    }

    @Override
    public boolean isMessageModelValid(MessageModel messageModel) throws DaoCustomException {
        try{
            return defaultBaseMessageValidatorImpl.isMessageModelValid(messageModel);
        } catch (IllegalArgumentException e) {
            throw new DaoCustomException(e.getMessage());
        }
    }
}
