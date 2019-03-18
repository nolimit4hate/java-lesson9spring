package com.tmg.lesson9.service.message;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.dao.message.MessageDao;
import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.service.exception.CustomServiceException;
import com.tmg.lesson9.service.validator.message.MessageServiceValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DefaultMessageServiceImplTest {

    @Mock
    private MessageDao messageDao;
    @Mock
    private MessageServiceValidator messageServiceValidator;
// tested class
    @InjectMocks
    private MessageService messageService = new DefaultMessageServiceImpl();

    private MessageModel messageValid1;
    private MessageModel messageValid2;
    private MessageModel messageInvalid;
    private List<MessageModel> messageModelListValid;
    private List<MessageModel> messageModelListInvalid;

    @Before
    public void setUp() throws Exception {
        MessageModel messageValid1 = new MessageModel(
                1,
                "topic topic",
                "body body body body",
                "vasia",
                "2010-10-10 20:20:20"
        );
        MessageModel messageValid2 = new MessageModel(
                2,
                "topic topic",
                "body body body body",
                "vasia",
                "2010-10-10 20:20:20"
        );
        MessageModel messageInvalid = new MessageModel(
                3,
                "t",
                "b",
                "v",
                "2010-10-10 20:20:20"
        );
        List<MessageModel> messageModelListValid = new ArrayList<>();
        messageModelListValid.add(messageValid1);
        messageModelListValid.add(messageValid2);
        List<MessageModel> messageModelListInvalid = new ArrayList<>();
        messageModelListInvalid.add(messageValid1);
        messageModelListInvalid.add(messageValid2);
        messageModelListInvalid.add(messageInvalid);
        this.messageValid1 = messageValid1;
        this.messageInvalid = messageValid2;
        this.messageInvalid = messageInvalid;
        this.messageModelListValid = messageModelListValid;
        this.messageModelListInvalid = messageModelListInvalid;
    }

//////////
//  testing DefaultMessageServiceImpl.getAllMessages()
//////////

    @Test
    public void testGetAllMessagesWithHappyEnd() {
        //given
        Mockito.when(messageDao.getAllMessages()).thenReturn(this.messageModelListValid);
        Mockito.when(messageServiceValidator.isMessageModelListValid(this.messageModelListValid)).thenReturn(true);

        //when
        messageService.getAllMessages();
        List<MessageModel> messageModelList = messageDao.getAllMessages();
        boolean isMessageModelListValid = messageServiceValidator.isMessageModelListValid(messageModelList);
        //then
        Assert.assertEquals(this.messageModelListValid, messageModelList);
        Assert.assertTrue(isMessageModelListValid);

    }

    @Test(expected = CustomServiceException.class)
    public void testGetAllMessagesWithInvalidMessageModelList() {
        //given
        Mockito.when(messageDao.getAllMessages()).thenReturn(this.messageModelListInvalid);
        Mockito.when(messageServiceValidator.isMessageModelListValid(this.messageModelListInvalid)).thenThrow(CustomServiceException.class);

        //when
        messageService.getAllMessages();
        List<MessageModel> messageModelList = messageDao.getAllMessages();
        messageServiceValidator.isMessageModelListValid(messageModelList);
        //then
        Assert.assertEquals(this.messageModelListInvalid, messageModelList);
    }

    @Test(expected = CustomDaoException.class)
    public void testGetAllMessagesWithDaoException() {
        //given
        Mockito.when(messageDao.getAllMessages()).thenThrow(CustomDaoException.class);

        //when
        messageService.getAllMessages();
        //then
    }

//////////
//  testing DefaultMessageServiceImpl.getMessageByCreator()
//////////

    @Test
    public void testGetMessageByCreatorWithHappyEnd() {
        //given
        String messageCreatorValid = "vasia";
        List<MessageModel> messageList = this.messageModelListValid;
        Mockito.when(messageServiceValidator.isMessageCreatorValid(messageCreatorValid)).thenReturn(true);
        Mockito.when(messageDao.getMessagesByCreator(messageCreatorValid)).thenReturn(messageList);
        Mockito.when(messageServiceValidator.isMessageModelListValid(messageList)).thenReturn(true);

        //when
        messageService.getMessageByCreator(messageCreatorValid);
        boolean isMessageCreatorValid = messageServiceValidator.isMessageCreatorValid(messageCreatorValid);
        List<MessageModel> messageModelListResult = messageDao.getMessagesByCreator(messageCreatorValid);
        boolean isMessageModelListResultValid = messageServiceValidator.isMessageModelListValid(messageModelListResult);

        //then
        Assert.assertTrue(isMessageCreatorValid);
        Assert.assertEquals(messageList, messageModelListResult);
        Assert.assertTrue(isMessageModelListResultValid);
    }

    @Test(expected = CustomServiceException.class)
    public void testGetMessageByCreatorWithInvalidList() {
        //given
        String messageCreatorValid = "vasia";
        List<MessageModel> messageList = this.messageModelListInvalid;
        Mockito.when(messageServiceValidator.isMessageCreatorValid(messageCreatorValid)).thenReturn(true);
        Mockito.when(messageDao.getMessagesByCreator(messageCreatorValid)).thenReturn(messageList);
        Mockito.when(messageServiceValidator.isMessageModelListValid(messageList)).thenThrow(CustomServiceException.class);

        //when
        messageService.getMessageByCreator(messageCreatorValid);
        boolean isMessageCreatorValid = messageServiceValidator.isMessageCreatorValid(messageCreatorValid);
        List<MessageModel> messageModelListResult = messageDao.getMessagesByCreator(messageCreatorValid);
        boolean isMessageModelListResultValid = messageServiceValidator.isMessageModelListValid(messageModelListResult);

        //then
        Assert.assertTrue(isMessageCreatorValid);
        Assert.assertEquals(messageList, messageModelListResult);
    }

    @Test(expected = CustomDaoException.class)
    public void testGetMessageByCreatorWithDaoException() {
        //given
        String messageCreatorValid = "vasia";
        List<MessageModel> messageList = this.messageModelListInvalid;
        Mockito.when(messageServiceValidator.isMessageCreatorValid(messageCreatorValid)).thenReturn(true);
        Mockito.when(messageDao.getMessagesByCreator(messageCreatorValid)).thenThrow(CustomDaoException.class);

        //when
        messageService.getMessageByCreator(messageCreatorValid);
        boolean isMessageCreatorValid = messageServiceValidator.isMessageCreatorValid(messageCreatorValid);
        List<MessageModel> messageModelListResult = messageDao.getMessagesByCreator(messageCreatorValid);

        //then
        Assert.assertTrue(isMessageCreatorValid);
    }

    @Test(expected = CustomServiceException.class)
    public void testGetMessageByCreatorWithCreatorInvalid() {
        //given
        String messageCreatorValid = "vasia";
        List<MessageModel> messageList = this.messageModelListInvalid;
        Mockito.when(messageServiceValidator.isMessageCreatorValid(messageCreatorValid)).thenThrow(CustomServiceException.class);

        //when
        messageService.getMessageByCreator(messageCreatorValid);

        //then
    }

//////////
//  testing DefaultMessageServiceImpl.addMessage()
//////////

    @Test
    public void testAddMessageWithHappyEnd() {
        //given
        MessageModel message = this.messageValid1;
        Mockito.when(messageServiceValidator.isMessageModelValid(message)).thenReturn(true);
        Mockito.when(messageDao.insertIntoMessages(message)).thenReturn(true);
        //when
        messageService.addMessage(message);
        boolean isMessageValid = messageServiceValidator.isMessageModelValid(message);
        boolean isMessageAdded = messageDao.insertIntoMessages(message);
        //then
        Assert.assertTrue(isMessageValid);
        Assert.assertTrue(isMessageAdded);
    }

    @Test(expected = CustomDaoException.class)
    public void testAddMessageWithDaoException() {
        //given
        MessageModel message = this.messageValid1;
        Mockito.when(messageServiceValidator.isMessageModelValid(message)).thenReturn(true);
        Mockito.when(messageDao.insertIntoMessages(message)).thenThrow(CustomDaoException.class);
        //when
        messageService.addMessage(message);
        boolean isMessageValid = messageServiceValidator.isMessageModelValid(message);
        boolean isMessageAdded = messageDao.insertIntoMessages(message);
        //then
        Assert.assertTrue(isMessageValid);
    }

    @Test(expected = CustomServiceException.class)
    public void testAddMessageWithMessageInvalid() {
        //given
        MessageModel message = this.messageInvalid;
        Mockito.when(messageServiceValidator.isMessageModelValid(message)).thenThrow(CustomServiceException.class);
        //when
        messageService.addMessage(message);
        //then
    }

}