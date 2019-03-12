package com.tmg.lesson9.validator.message;

import com.tmg.lesson9.model.message.MessageModel;

public interface MessageValidator {
    boolean isMessageValid(MessageModel messageModel);
    boolean isMessageCreatorInvalid(String messageCreator);
}
