package com.tmg.lesson9.validator.message.service;

import com.tmg.lesson9.facade.exception.ServiceCustomException;
import com.tmg.lesson9.model.message.MessageModel;

public interface MessageServiceValidator {
    boolean isMessageCreatorValid(String creator) throws ServiceCustomException;
    boolean isMessageModelValid(MessageModel messageModel) throws ServiceCustomException;
}
