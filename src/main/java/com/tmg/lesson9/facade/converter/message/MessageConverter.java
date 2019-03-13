package com.tmg.lesson9.facade.converter.message;

import com.tmg.lesson9.web.form.MessageSendForm;
import com.tmg.lesson9.web.form.MessageShowForm;
import com.tmg.lesson9.model.message.MessageModel;

import java.util.List;

public interface MessageConverter {
    MessageModel convertSendFormToModel(MessageSendForm sendForm);
    MessageShowForm convertModelToShowForm(MessageModel messageModel);
    List<MessageShowForm> convertListModelToListShowForm(List<MessageModel> messageModels);
}
