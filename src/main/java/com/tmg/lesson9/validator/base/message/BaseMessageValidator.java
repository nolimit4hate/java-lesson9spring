package com.tmg.lesson9.validator.base.message;

import com.tmg.lesson9.model.message.MessageModel;

import java.util.List;

public interface BaseMessageValidator {
    boolean isMessageModelValid(MessageModel messageModel) throws IllegalArgumentException;

    boolean isMessageCreatorValid(String creator) throws IllegalArgumentException;

    boolean isMessageModelListValid(List<MessageModel> messageModelList) throws IllegalArgumentException;
}
