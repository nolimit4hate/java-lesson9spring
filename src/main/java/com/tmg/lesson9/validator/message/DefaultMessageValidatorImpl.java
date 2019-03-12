package com.tmg.lesson9.validator.message;

import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.validator.base.BaseInvalidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DefaultMessageValidatorImpl implements MessageValidator {

    @Resource
    BaseInvalidator defaultBaseInvalidatorImpl;
    private MessageModel message;

    public void setMessage(MessageModel message) {
        this.message = message;
    }

    @Override
    public boolean isMessageValid(MessageModel messageModel) {
        setMessage(messageModel);
        if(message == null || isMessageCreatorInvalid() || isMessageDateTimeInvalid() ||
                isMessageTopicInvalid() || isMessageBodyInvalid()){
            return false;
        }
        return true;
    }

    @Override
    public boolean isMessageCreatorInvalid(String messageCreator) {
        return defaultBaseInvalidatorImpl.isUserNameInvalid(messageCreator);
    }

    private boolean isMessageCreatorInvalid(){
        String userName = message.getMessageCreator();
        return defaultBaseInvalidatorImpl.isUserNameInvalid(userName);
    }

    private boolean isMessageDateTimeInvalid(){
        String dateTime = message.getDateTimeCreation();
        return defaultBaseInvalidatorImpl.isDateTimeInvalid(dateTime);
    }

    private boolean isMessageTopicInvalid() {
        String messageTopic = message.getMessageTopic();
        int minTopicLength = 5;
        int maxTopicLength = 50;
        if(defaultBaseInvalidatorImpl.isStringInvalidByLength(messageTopic, minTopicLength, maxTopicLength)){
            return true;
        }
        return false;
    }

    private boolean isMessageBodyInvalid() {
        String messageTopic = message.getMessageBody();
        int minTopicLength = 8;
        int maxTopicLength = 256;
        if(defaultBaseInvalidatorImpl.isStringInvalidByLength(messageTopic, minTopicLength, maxTopicLength)){
            return true;
        }
        return false;
    }
}
