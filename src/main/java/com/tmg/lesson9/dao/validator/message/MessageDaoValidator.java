package com.tmg.lesson9.dao.validator.message;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.dao.message.MessageDao;
import com.tmg.lesson9.model.message.MessageModel;

import java.util.List;

public interface MessageDaoValidator {
    boolean isMessageCreatorValid(String creator) throws CustomDaoException;

    boolean isMessageModelValid(MessageModel messageModel) throws CustomDaoException;
    boolean isMessageModelListValid(List<MessageModel> messageModelList) throws CustomDaoException;
}
