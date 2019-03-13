package com.tmg.lesson9.service.message;

import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.service.exception.CustomServiceException;

import java.util.List;

public interface MessageService {

    List<MessageModel> getAllMessages() throws CustomServiceException;
    List<MessageModel> getMessageByCreator(String creatorName) throws CustomServiceException;

    boolean addMessage(MessageModel messageModel) throws CustomServiceException;
}
