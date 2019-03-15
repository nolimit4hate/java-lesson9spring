package com.tmg.lesson9.facade.validator.message;

import com.tmg.lesson9.facade.exception.CustomFacadeException;
import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.web.form.MessageSendForm;

import java.util.List;

public interface MessageFacadeValidator {

    boolean isMessageCreatorValid(String creator) throws CustomFacadeException;

    boolean isMessageModelValid(MessageModel messageModel) throws CustomFacadeException;

    boolean isMessageSendFormValid(MessageSendForm messageSendForm) throws CustomFacadeException;

    boolean isMessageModelListValid(List<MessageModel> messageModelList) throws CustomFacadeException;
}
