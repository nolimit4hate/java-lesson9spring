package com.tmg.lesson9.service.message;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.dao.message.MessageDao;
import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.service.exception.CustomServiceException;
import com.tmg.lesson9.service.validator.message.MessageServiceValidator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("messageService")
public class DefaultMessageServiceImpl implements MessageService {

    @Resource
    MessageServiceValidator messageServiceValidator;
    @Resource
    MessageDao messageDao;

    @Override
    public List<MessageModel> getAllMessages() throws CustomServiceException, CustomDaoException {
        try{
            List<MessageModel> result = messageDao.getAllMessages();
            messageServiceValidator.isMessageModelListValid(result);
            return result;
        } catch (CustomDaoException e) {
            throw new CustomServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<MessageModel> getMessageByCreator(String creatorName) throws CustomServiceException, CustomDaoException {
        messageServiceValidator.isMessageCreatorValid(creatorName);
        try{
            List<MessageModel> result = messageDao.getMessagesByCreator(creatorName);
            messageServiceValidator.isMessageModelListValid(result);
            return result;
        } catch (CustomDaoException e) {
            throw new CustomServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean addMessage(MessageModel messageModel) throws CustomServiceException, CustomDaoException {
        messageServiceValidator.isMessageModelValid(messageModel);
        try{
            return messageDao.insertIntoMessages(messageModel);
        } catch (CustomDaoException e) {
            throw new CustomServiceException(e.getMessage(), e);
        }
    }
}
