package com.tmg.lesson9.dao.message;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.model.message.MessageModel;

import java.util.List;

public interface MessageDao {

    List<MessageModel> getAllMessages() throws CustomDaoException;
    List<MessageModel> getMessagesByCreator(String creatorName) throws CustomDaoException;
    boolean insertIntoMessages(MessageModel messageModel) throws CustomDaoException;
}
