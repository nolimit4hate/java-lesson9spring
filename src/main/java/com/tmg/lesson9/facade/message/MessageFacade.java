package com.tmg.lesson9.facade.message;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.facade.exception.CustomFacadeException;
import com.tmg.lesson9.service.exception.CustomServiceException;
import com.tmg.lesson9.web.form.MessageSendForm;
import com.tmg.lesson9.web.form.MessageShowForm;

import java.util.List;

public interface MessageFacade {
    List<MessageShowForm> getAllMessagesByCreatorName(String creatorName) throws CustomFacadeException, CustomServiceException, CustomDaoException;
    boolean addMessage(MessageSendForm messageSendForm) throws CustomFacadeException, CustomServiceException, CustomDaoException;
    List<MessageShowForm> getAllMessages() throws CustomFacadeException, CustomServiceException, CustomDaoException;
}
