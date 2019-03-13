package com.tmg.lesson9.validator.message.service;

import com.tmg.lesson9.service.exception.CustomServiceException;
import com.tmg.lesson9.model.message.MessageModel;

import java.util.List;

public interface MessageServiceValidator {
    boolean isMessageCreatorValid(String creator) throws CustomServiceException;
    boolean isMessageModelValid(MessageModel messageModel) throws CustomServiceException;
    boolean isMessageModelListValid(List<MessageModel> messageModelList) throws CustomServiceException;
}
