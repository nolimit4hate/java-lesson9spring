package com.tmg.lesson9.validator.message.facade;

import com.tmg.lesson9.facade.exception.CustomFacadeException;
import com.tmg.lesson9.front.form.MessageSendForm;
import com.tmg.lesson9.model.message.MessageModel;

import java.util.List;

public interface MessageFacadeValidator {

    boolean isMessageCreatorValid(String creator) throws CustomFacadeException;
    boolean isMessageModelValid(MessageModel messageModel) throws CustomFacadeException;
    boolean isMessageSendFormValid(MessageSendForm messageSendForm) throws CustomFacadeException;
    boolean isMessageModelListValid(List<MessageModel> messageModelList) throws CustomFacadeException;
}
