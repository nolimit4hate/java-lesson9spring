package com.tmg.lesson9.service.message;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.dao.message.MessageDao;
import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.service.exception.CustomServiceException;
import com.tmg.lesson9.validator.message.service.MessageServiceValidator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DefaultMessageServiceImpl implements MessageService {

    @Resource
    MessageServiceValidator defaultMessageServiceValidatorImpl;
    @Resource
    MessageDao defaultMessageDaoImpl;

    @Override
    public List<MessageModel> getAllMessages() throws CustomServiceException {
        try{
            List<MessageModel> result = defaultMessageDaoImpl.getAllMessages();
            defaultMessageServiceValidatorImpl.isMessageModelListValid(result);
            return result;
        } catch (CustomDaoException e) {
            throw new CustomServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<MessageModel> getMessageByCreator(String creatorName) throws CustomServiceException {
        defaultMessageServiceValidatorImpl.isMessageCreatorValid(creatorName);
        try{
            List<MessageModel> result = defaultMessageDaoImpl.getMessagesByCreator(creatorName);
            defaultMessageServiceValidatorImpl.isMessageModelListValid(result);
            return result;
        } catch (CustomDaoException e) {
            throw new CustomServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean addMessage(MessageModel messageModel) throws CustomServiceException {
        defaultMessageServiceValidatorImpl.isMessageModelValid(messageModel);
        try{
            return defaultMessageDaoImpl.insertIntoMessages(messageModel);
        } catch (CustomDaoException e) {
            throw new CustomServiceException(e.getMessage(), e);
        }
    }
}
