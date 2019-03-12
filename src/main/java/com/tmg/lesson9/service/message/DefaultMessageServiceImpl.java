package com.tmg.lesson9.service.message;

import com.tmg.lesson9.converter.util.ClassChecker;
import com.tmg.lesson9.dao.message.MessageDao;
import com.tmg.lesson9.dao.user.UserDao;
import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.validator.message.MessageValidator;
import com.tmg.lesson9.validator.user.UserValidator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultMessageServiceImpl implements MessageService {

    @Resource
    MessageValidator defaultMessageValidatorImpl;
    @Resource
    MessageDao defaultMessageDaoImpl;

    @Override
    public List<MessageModel> getAllMessages() {
        List<MessageModel> result = defaultMessageDaoImpl.getAllMessages();
        return result;
    }

    @Override
    public List<MessageModel> getMessageByCreator(String creatorName) {
        List<MessageModel> result;
        if(creatorName == null || defaultMessageValidatorImpl.isMessageCreatorInvalid(creatorName)){
            return null;
        } else {
            result = defaultMessageDaoImpl.getMessageByCreator(creatorName);
            return result;
        }
    }

    @Override
    public boolean addMessage(MessageModel messageModel) {
        boolean result;
        if(messageModel == null || !defaultMessageValidatorImpl.isMessageValid(messageModel)){
            return false;
        } else {
            result = defaultMessageDaoImpl.insertIntoMessages(messageModel);
            return result;
        }
    }
}
