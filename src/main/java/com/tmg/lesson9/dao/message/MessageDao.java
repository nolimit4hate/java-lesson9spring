package com.tmg.lesson9.dao.message;

import com.tmg.lesson9.model.message.MessageModel;

import java.util.List;

public interface MessageDao {

    List<MessageModel> getAllMessages();
    List<MessageModel> getMessageByCreator(String creatorName);

    boolean insertIntoMessages(MessageModel messageModel);
}
