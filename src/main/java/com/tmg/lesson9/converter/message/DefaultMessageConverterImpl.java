package com.tmg.lesson9.converter.message;

import com.tmg.lesson9.front.form.MessageSendForm;
import com.tmg.lesson9.front.form.MessageShowForm;
import com.tmg.lesson9.model.message.MessageModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultMessageConverterImpl implements MessageConverter {

    @Override
    public List<MessageShowForm> convertListModelToListShowForm(List<MessageModel> messageModels) {
        List<MessageShowForm> showFormList = new ArrayList();
        MessageShowForm showForm;
        for(MessageModel messageModel : messageModels){
            showForm = convertModelToShowForm(messageModel);
            showFormList.add(showForm);
        }
        return showFormList;
    }

    @Override
    public MessageModel convertSendFormToModel(MessageSendForm sendForm) {
        MessageModel messageModel = new MessageModel();
        messageModel.setMessageTopic(sendForm.getMessageTopic());
        messageModel.setMessageBody(sendForm.getMessageBody());
        messageModel.setMessageCreator(sendForm.getMessageCreator());
        return messageModel;
    }

    @Override
    public MessageShowForm convertModelToShowForm(MessageModel messageModel) {
        MessageShowForm showForm = new MessageShowForm();
        showForm.setMessageTopic(messageModel.getMessageTopic());
        showForm.setMessageBody(messageModel.getMessageBody());
        showForm.setMessageCreator(messageModel.getMessageCreator());
        showForm.setDateTimeCreation(messageModel.getDateTimeCreation());
        return showForm;
    }
}
