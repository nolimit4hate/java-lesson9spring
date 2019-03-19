package com.tmg.lesson9.dao.message;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.dao.user.UserDao;
import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.model.user.UserModel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appContextDBConfig.xml")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextBeforeModesTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@DatabaseSetup("/com.tmg.lesson9/dao/message/messages-dataset.xml")
public class DefaultMessageDaoImplTest {

    @Resource
    private MessageDao messageDao;

    private List<MessageModel> allDbMessages;
    private List<UserModel> allDbUsers;

    @Before
    public void setUp() throws Exception {
        FakeDbDataGetter dbDataGetter = new FakeDbDataGetter();
        this.allDbMessages = dbDataGetter.getAllDbMessages();
        this.allDbUsers = dbDataGetter.getAllDbUsers();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetAllMessages() {
        //given
        List<MessageModel> allMessages = this.allDbMessages;
        System.out.println("###MSG###\n" + allMessages.toString());
        //when
        List<MessageModel> allGettingMessages = messageDao.getAllMessages();
        //then
        Assert.assertEquals(allMessages, allGettingMessages);
    }

    @Test
    public void testGetMessagesByCreator_CreatorWithMultipleMessages() {
        //given
        List<MessageModel> allMessages = this.allDbMessages;
        String creatorNameWith2Messages = "helloboy";
        //when
        List<MessageModel> creatorMessagesList = messageDao.getMessagesByCreator(creatorNameWith2Messages);
        //then
        Assert.assertEquals(creatorMessagesList.size(), 2);
        Assert.assertTrue(allMessages.containsAll(creatorMessagesList));

    }

    @Test
    public void testGetMessagesByCreator_CreatorWithOneMessage() {
        //given
        List<MessageModel> allMessages = this.allDbMessages;
        String creatorNameWithOneMessage = "terminator";
        //when
        List<MessageModel> creatorMessagesList = messageDao.getMessagesByCreator(creatorNameWithOneMessage);
        //then
        Assert.assertEquals(creatorMessagesList.size(), 1);
        Assert.assertTrue(allMessages.containsAll(creatorMessagesList));
    }

    @Test
    public void testGetMessagesByCreator_CreatorWithNoMessages() {
        //given
        List<MessageModel> allMessages = this.allDbMessages;
        String creatorNameWithNoMessages = "humhum";
        //when
        List<MessageModel> creatorMessagesList = messageDao.getMessagesByCreator(creatorNameWithNoMessages);
        //then
        Assert.assertTrue(creatorMessagesList.isEmpty());
    }

    @Test(expected = CustomDaoException.class)
    public void testGetMessagesByCreator_CreatorNameNotExist() {
        //given
        List<MessageModel> allMessages = this.allDbMessages;
        String creatorNameNotExist = "hlboy";
        //when
        messageDao.getMessagesByCreator(creatorNameNotExist);
        //then
    }

    @Test
    public void testInsertIntoMessages_HappyEnd() {
        //given
        String messageCreator = allDbUsers.get(0).getUserName();
        MessageModel messageCorrect = new MessageModel();
        messageCorrect.setMessageTopic("12345 12345");
        messageCorrect.setMessageBody("0123456789 0123456789");
        messageCorrect.setMessageCreator(messageCreator);
        messageCorrect.setDateTimeCreation("2010-10-10 20:20:20");
        //when
        boolean isMessageInserted = messageDao.insertIntoMessages(messageCorrect);
        //then
        Assert.assertTrue(isMessageInserted);
    }

    @Test(expected = CustomDaoException.class)
    public void testInsertIntoMessages_InsertExistingMessage() {
        //given
        String messageCreator = allDbUsers.get(0).getUserName();
        MessageModel messageCorrect = new MessageModel();
        messageCorrect.setMessageTopic("12345 12345");
        messageCorrect.setMessageBody("0123456789 0123456789");
        messageCorrect.setMessageCreator(messageCreator);
        messageCorrect.setDateTimeCreation("2010-10-10 20:20:20");
        //when
        messageDao.insertIntoMessages(messageCorrect);
        messageDao.insertIntoMessages(messageCorrect);
        //then
    }

    @Test(expected = CustomDaoException.class)
    public void testInsertIntoMessages_InsertMessageWithNotExistingCreator() {
        //given
        MessageModel messageCorrect = new MessageModel();
        messageCorrect.setMessageTopic("12345 12345");
        messageCorrect.setMessageBody("0123456789 0123456789");
        messageCorrect.setMessageCreator("oleh");
        messageCorrect.setDateTimeCreation("2010-10-10 20:20:20");
        //when
        messageDao.insertIntoMessages(messageCorrect);
        //then
    }
}