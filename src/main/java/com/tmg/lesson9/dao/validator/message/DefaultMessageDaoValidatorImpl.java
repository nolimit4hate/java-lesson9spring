package com.tmg.lesson9.dao.validator.message;

import com.tmg.lesson9.commons.validator.base.message.BaseMessageValidator;
import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.model.message.MessageModel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Class implements MessageFacadeValidator - specific validator for dao layer
 */

@Component("messageDaoValidator")
public class DefaultMessageDaoValidatorImpl implements MessageDaoValidator {

    /**
     * Inject BaseMessageValidator implementation
     */

    @Resource
    BaseMessageValidator baseMessageValidator;

    /**
     * Use implementation of BaseMessageValidator and do specific message creator validation for dao layer
     *
     * @param creator input string value with name of message creator
     * @return true if input string is valid
     * @throws CustomDaoException if IllegalArgumentException will be thrown
     */

    @Override
    public boolean isMessageCreatorValid(String creator) throws CustomDaoException {
        try {
            return baseMessageValidator.isMessageCreatorValid(creator);
        } catch (IllegalArgumentException e) {
            throw new CustomDaoException(e.getMessage(), e);
        }
    }

    /**
     * Use implementation of BaseMessageValidator and do specific MessageModel validation for dao layer
     *
     * @param messageModel MessageModel type object with information about message
     * @return true if input value is valid
     * @throws CustomDaoException if IllegalArgumentException will be thrown
     */

    @Override
    public boolean isMessageModelValid(MessageModel messageModel) throws CustomDaoException {
        try {
            return baseMessageValidator.isMessageModelValid(messageModel);
        } catch (IllegalArgumentException e) {
            throw new CustomDaoException(e.getMessage(), e);
        }
    }
}
