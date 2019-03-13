package com.tmg.lesson9.validator.message.dao;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.model.message.MessageModel;

public interface MessageDaoValidator {
    boolean isMessageCreatorValid(String creator) throws CustomDaoException;
    boolean isMessageModelValid(MessageModel messageModel) throws CustomDaoException;
}
