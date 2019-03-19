package com.tmg.lesson9.commons.validator.base.message;

import com.tmg.lesson9.commons.validator.base.BaseDateTimeValidator;
import com.tmg.lesson9.commons.validator.base.BaseNameValidator;
import com.tmg.lesson9.commons.validator.base.BaseStringFieldValidator;
import com.tmg.lesson9.commons.validator.base.impl.DefaultBaseDateTimeValidatorImpl;
import com.tmg.lesson9.commons.validator.base.impl.DefaultBaseNameValidatorImpl;
import com.tmg.lesson9.commons.validator.base.impl.DefaultBaseStringFieldValidatorImpl;
import com.tmg.lesson9.model.message.MessageModel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * default implementation of base MessageModel validator interface. This base message validator implementation was created
 * to help realize same specific class for each web program layer(facade, service, dao)
 */

@Component("baseMessageValidator")
public class DefaultBaseMessageValidatorImpl implements BaseMessageValidator {

    /**
     * @param baseNameValidator is base validation for name
     */
    BaseNameValidator baseNameValidator = new DefaultBaseNameValidatorImpl();
    /**
     * @param baseStringFieldValidator is base validation for string fields
     */
    BaseStringFieldValidator baseStringFieldValidator = new DefaultBaseStringFieldValidatorImpl();
    /**
     * @param baseDateTimeValidator is base validation for date-time format string
     */
    BaseDateTimeValidator baseDateTimeValidator = new DefaultBaseDateTimeValidatorImpl();

    /**
     * @param message is checking for valid value of type MessageModel. This was made this for more comfortable process of validation.
     */

    MessageModel message = new MessageModel();

    /**
     * method check MessageModel type value for valid. every field of input object is checking for valid
     *
     * @param messageModel is value of MessageModel
     * @return true if input value is valid
     * @throws IllegalArgumentException if input value is null or any field of input value object is null or not valid
     */

    @Override
    public boolean isMessageModelValid(MessageModel messageModel) throws IllegalArgumentException {
        if (messageModel == null) {
            throw new IllegalArgumentException("message model cant be null");
        }
        setMessage(messageModel);
        isAllParamsValid();
        return true;
    }

    /**
     * method checks all fields of @param message for valid
     *
     * @return true if all fields of @param message is valid
     * @throws IllegalArgumentException if any field of @param message is invalid
     */

    private boolean isAllParamsValid() throws IllegalArgumentException {
        isIdValid();
        isTopicValid();
        isBodyValid();
        isCreatorValid();
        isDateTimeValid();
        return true;
    }

    /**
     * check for valid message creator field of @param message
     *
     * @param creator input string means dao.dao name who create message
     * @return true if input string is valid
     * @throws IllegalArgumentException if input string is not valid
     */

    @Override
    public boolean isMessageCreatorValid(String creator) throws IllegalArgumentException {
        return baseNameValidator.isUserNameValid(creator);
    }

    /**
     * check list of messages(MessageModel type) for valid
     *
     * @param messageModelList input list of messages
     * @return true if all messages of input list messages is valid
     * @throws IllegalArgumentException if any message of input list messages is invalid
     */

    @Override
    public boolean isMessageModelListValid(List<MessageModel> messageModelList) throws IllegalArgumentException {
        if (messageModelList == null) {
            throw new IllegalArgumentException("message model list cant be null");
        }
        for (MessageModel messageModel : messageModelList) {
            isMessageModelValid(messageModel);
        }
        return true;
    }

    /**
     * check for valid message id field of @param message
     *
     * @return true if messageId field is positive
     * @throws IllegalArgumentException if messageId is negative
     */

    private boolean isIdValid() throws IllegalArgumentException {
        if (message.getId() >= 0) {
            return true;
        } else {
            throw new IllegalArgumentException("message id must be >= 0");
        }
    }

    /**
     * check for valid messageTopic field of @param message
     *
     * @return true if topic string have length from 5 to 63 without spaces
     * @throws IllegalArgumentException if topic is null or invalid
     */

    private boolean isTopicValid() throws IllegalArgumentException {
        return baseStringFieldValidator.isStringFieldsValidByLength(5, 63, message.getMessageTopic());
    }

    /**
     * check for valid messageBody field of @param message
     *
     * @return true if topic string have length from 5 to 63 without spaces
     * @throws IllegalArgumentException if topic is null or invalid
     */

    private boolean isBodyValid() throws IllegalArgumentException {
        return baseStringFieldValidator.isStringFieldsValidByLength(10, 256, message.getMessageBody());
    }

    /**
     * check for valid messageCreator field of @param message
     *
     * @return true if messageCreator is valid
     * @throws IllegalArgumentException if messageCreator is invalid
     */

    private boolean isCreatorValid() throws IllegalArgumentException {
        return isMessageCreatorValid(message.getMessageCreator());
    }

    /**
     * check for valid dateTimeCreation field of @param message
     *
     * @return true if dateTimeCreation is valid
     * @throws IllegalArgumentException if dateTimeCreation is invalid
     */

    private boolean isDateTimeValid() throws IllegalArgumentException {
        return baseDateTimeValidator.isDateTimeStringValid(message.getDateTimeCreation());
    }

    public MessageModel getMessage() {
        return message;
    }

    public void setMessage(MessageModel message) {
        this.message = message;
    }
}
