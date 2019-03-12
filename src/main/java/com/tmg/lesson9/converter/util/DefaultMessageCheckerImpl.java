package com.tmg.lesson9.converter.util;

import com.tmg.lesson9.front.form.MessageSendForm;
import com.tmg.lesson9.model.message.MessageModel;
import org.springframework.stereotype.Component;

@Component
public class DefaultMessageCheckerImpl implements MessageChecker {

    @Override
    public boolean checkSendForm(MessageSendForm sendForm) {
        boolean result = isAnyObjectIsNullOrEmpty(
                sendForm.getMessageBody(), sendForm.getMessageTopic(), sendForm.getMessageCreator()
        );
        return result;
    }

    @Override
    public boolean checkMessageModel(MessageModel messageModel) {
        return false;
    }

    public <T extends Object> boolean isAnyObjectIsNullOrEmpty(T... arg) {
        CheckObjects checkObjects = new CheckObjects();
        return checkObjects.isAnyObjectIsNullOrEmpty(arg);
    }
}
