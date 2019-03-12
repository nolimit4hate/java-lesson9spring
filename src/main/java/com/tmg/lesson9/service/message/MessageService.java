package com.tmg.lesson9.service.message;

import com.tmg.lesson9.model.message.MessageModel;

import java.util.List;

public interface MessageService {

    List<MessageModel> getAllMessages();
    List<MessageModel> getMessageByCreator(String creatorName);

    boolean addMessage(MessageModel messageModel);
}
