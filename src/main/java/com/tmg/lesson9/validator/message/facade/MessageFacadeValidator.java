package com.tmg.lesson9.validator.message.facade;

import com.tmg.lesson9.front.form.MessageSendForm;
import com.tmg.lesson9.model.message.MessageModel;

public interface MessageFacadeValidator {

    boolean isMessageCreatorValid(String creator) throws IllegalArgumentException;
    boolean isMessageModelValid(MessageModel messageModel) throws IllegalArgumentException;
    boolean isMessageSendFormValid(MessageSendForm messageSendForm) throws IllegalArgumentException;
}
