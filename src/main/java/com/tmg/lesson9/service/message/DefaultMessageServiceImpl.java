package com.tmg.lesson9.service.message;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.dao.message.MessageDao;
import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.service.exception.CustomServiceException;
import com.tmg.lesson9.service.validator.message.MessageServiceValidator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service class for processing messages business logic.
 */

@Service("messageService")
public class DefaultMessageServiceImpl implements MessageService {

    @Resource
    MessageServiceValidator messageServiceValidator;
    @Resource
    MessageDao messageDao;

    /**
     * Method call messageDao.getAllMessages() for getting list of all MessageModel objects in database.
     * Then validate list of all MessageModel objects.
     *
     * @return list of MessageModel objects
     * @throws CustomServiceException if any MessageModel object in list is invalid
     * @throws CustomDaoException     if exception is thrown in dao layer
     */

    @Override
    public List<MessageModel> getAllMessages() throws CustomServiceException, CustomDaoException {
        try {
            List<MessageModel> result = messageDao.getAllMessages();
            messageServiceValidator.isMessageModelListValid(result);
            return result;
        } catch (CustomDaoException e) {
            throw new CustomServiceException(e.getMessage(), e);
        }
    }

    /**
     * Method valid @param creatorName then call messageDao.getMessagesByCreator() method with input parameter creatorName.
     * Valid list of MessageModel objects and return it. If any validation is not pass throw CustomServiceException
     *
     * @param creatorName String value that contain creator name information
     * @return list of MessageModel objects where messageCreator is @param creatorName
     * @throws CustomServiceException if creatorName is invalid or list of MessageModel is invalid
     * @throws CustomDaoException     if exception is thrown in dao layer
     */

    @Override
    public List<MessageModel> getMessageByCreator(String creatorName) throws CustomServiceException, CustomDaoException {
        messageServiceValidator.isMessageCreatorValid(creatorName);
        try {
            List<MessageModel> result = messageDao.getMessagesByCreator(creatorName);
            messageServiceValidator.isMessageModelListValid(result);
            return result;
        } catch (CustomDaoException e) {
            throw new CustomServiceException(e.getMessage(), e);
        }
    }

    /**
     * Method valid UserModel object then call messageDao.insertIntoMessages() and return result of it.
     *
     * @param messageModel MessageModel object that contain data about message
     * @return true if MessageModel data was successfully added to database
     * @throws CustomServiceException input MessageModel object is invalid
     * @throws CustomDaoException     if exception is thrown in dao layer
     */

    @Override
    public boolean addMessage(MessageModel messageModel) throws CustomServiceException, CustomDaoException {
        messageServiceValidator.isMessageModelValid(messageModel);
        try {
            return messageDao.insertIntoMessages(messageModel);
        } catch (CustomDaoException e) {
            throw new CustomServiceException(e.getMessage(), e);
        }
    }
}
