package com.tmg.lesson9.facade.message;

import com.tmg.lesson9.front.form.MessageSendForm;
import com.tmg.lesson9.front.form.MessageShowForm;

import java.util.List;

public interface MessageFacade {
    List<MessageShowForm> getAllMessagesByCreatorName(String creatorName);
    boolean addMessage(MessageSendForm messageSendForm);
    List<MessageShowForm> getAllMessages();
}
