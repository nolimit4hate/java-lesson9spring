package com.tmg.lesson9.converter.util;

import com.tmg.lesson9.front.form.MessageSendForm;
import com.tmg.lesson9.model.message.MessageModel;

public interface MessageChecker {
    boolean checkSendForm(MessageSendForm sendForm);
    boolean checkMessageModel(MessageModel messageModel);

}
