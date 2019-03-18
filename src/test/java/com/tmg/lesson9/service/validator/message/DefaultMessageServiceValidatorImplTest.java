package com.tmg.lesson9.service.validator.message;

import com.tmg.lesson9.commons.validator.base.BaseStringFieldValidator;
import com.tmg.lesson9.commons.validator.base.message.BaseMessageValidator;
import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.service.exception.CustomServiceException;
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
public class DefaultMessageServiceValidatorImplTest {

    @Mock
    BaseMessageValidator baseMessageValidator;
    @Mock
    BaseStringFieldValidator baseStringFieldValidator;
    @InjectMocks
    MessageServiceValidator messageServiceValidator = new DefaultMessageServiceValidatorImpl();

    private MessageModel messageValid;
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
        this.messageValid = messageValid1;
        this.messageInvalid = messageInvalid;
        this.messageModelListValid = messageModelListValid;
        this.messageModelListInvalid = messageModelListInvalid;
    }

//////////
//  testing DefaultMessageServiceValidatorImpl.isMessageCreatorValid()
//////////

    @Test
    public void testIsMessageCreatorValidWithHappyEnd() {
        //given
        String creatorValid = "vasia";
        Mockito.when(messageServiceValidator.isMessageCreatorValid(creatorValid)).thenReturn(true);
        Mockito.when(baseMessageValidator.isMessageCreatorValid(creatorValid)).thenReturn(true);
        //when
        boolean isCreatorValidByServiceValidator = messageServiceValidator.isMessageCreatorValid(creatorValid);
        boolean isCreatorValidByBaseValidator = baseMessageValidator.isMessageCreatorValid(creatorValid);
        //then
        Assert.assertTrue(isCreatorValidByBaseValidator);
        Assert.assertTrue(isCreatorValidByServiceValidator);
    }

    @Test(expected = CustomServiceException.class)
    public void testIsMessageCreatorValidWithInvalidCreator() {
        //given
        String creatorInvalid = "v";
        Mockito.when(messageServiceValidator.isMessageCreatorValid(creatorInvalid)).thenThrow(CustomServiceException.class);
        Mockito.when(baseMessageValidator.isMessageCreatorValid(creatorInvalid)).thenThrow(IllegalArgumentException.class);
        //when
        messageServiceValidator.isMessageCreatorValid(creatorInvalid);
        baseMessageValidator.isMessageCreatorValid(creatorInvalid);
        //then
    }

//////////
//  testing DefaultMessageServiceValidatorImpl.isMessageModelValid()
//////////

    @Test
    public void testIsMessageModelValidWithHappyEnd() {
        //given
        MessageModel message = this.messageValid;

        Mockito.when(messageServiceValidator.isMessageModelValid(message)).thenReturn(true);
        Mockito.when(baseMessageValidator.isMessageModelValid(message)).thenReturn(true);
        Mockito.when(baseStringFieldValidator.isStringFieldsValidByLength(5, 50, message.getMessageTopic())).thenReturn(true);
        Mockito.when(baseStringFieldValidator.isStringFieldsValidByLength(10, 200, message.getMessageBody())).thenReturn(true);
        //when
        boolean isMessageValidByServiceValidator = messageServiceValidator.isMessageModelValid(message);
        boolean isMessageValidByBaseValidator = baseMessageValidator.isMessageModelValid(message);
        boolean isMessageTopicValidByLength = baseStringFieldValidator.isStringFieldsValidByLength(5, 50, message.getMessageTopic());
        boolean isMessageBodyValidByLength = baseStringFieldValidator.isStringFieldsValidByLength(10, 200, message.getMessageBody());
        //then
        Assert.assertTrue(isMessageValidByBaseValidator);
        Assert.assertTrue(isMessageTopicValidByLength);
        Assert.assertTrue(isMessageBodyValidByLength);
        Assert.assertTrue(isMessageValidByServiceValidator);
    }

    @Test(expected = CustomServiceException.class)
    public void testIsMessageModelValidWithInvalidMessageBody() {
        //given
        MessageModel message = this.messageValid;
        message.setMessageBody("12345678");

        Mockito.when(messageServiceValidator.isMessageModelValid(message)).thenThrow(CustomServiceException.class);
        Mockito.when(baseStringFieldValidator.isStringFieldsValidByLength(10, 200, message.getMessageBody())).thenThrow(IllegalArgumentException.class);
        //when
        messageServiceValidator.isMessageModelValid(message);
        baseStringFieldValidator.isStringFieldsValidByLength(10, 200, message.getMessageBody());
        //then
    }

    @Test(expected = CustomServiceException.class)
    public void testIsMessageModelValidWithInvalidMessageTopic() {
        //given
        MessageModel message = this.messageValid;
        message.setMessageTopic("1234");

        Mockito.when(messageServiceValidator.isMessageModelValid(message)).thenThrow(CustomServiceException.class);
        Mockito.when(baseStringFieldValidator.isStringFieldsValidByLength(5, 50, message.getMessageTopic())).thenThrow(IllegalArgumentException.class);
        //when
        messageServiceValidator.isMessageModelValid(message);
        baseStringFieldValidator.isStringFieldsValidByLength(5, 50, message.getMessageTopic());
        //then
    }

    @Test(expected = CustomServiceException.class)
    public void testIsMessageModelValidWithInvalidMessage() {
        //given
        MessageModel message = this.messageValid;
        message.setMessageTopic("1");

        Mockito.when(messageServiceValidator.isMessageModelValid(message)).thenThrow(CustomServiceException.class);
        Mockito.when(baseMessageValidator.isMessageModelValid(message)).thenThrow(IllegalArgumentException.class);
        //when
        messageServiceValidator.isMessageModelValid(message);
        baseMessageValidator.isMessageModelValid(message);
        //then
    }

//////////
//  testing BaseStringFieldValidator.isStringFieldsValidByLength()
//  message body length = low limit
//////////

    @Test
    public void testIsMessageBodyValidByLengthWithLowLimit (){
        //given
        MessageModel message = this.messageValid;
        message.setMessageBody(makeStringByStringLength(10));
        Mockito.when(baseStringFieldValidator.isStringFieldsValidByLength(10, 200, message.getMessageBody())).thenReturn(true);
        //when
        messageServiceValidator.isMessageModelValid(message);
        boolean isMessageValidByMessageBodyLength = baseStringFieldValidator.isStringFieldsValidByLength(10, 200, message.getMessageBody());
        //then
        Assert.assertTrue(isMessageValidByMessageBodyLength);
    }

//////////
//  testing BaseStringFieldValidator.isStringFieldsValidByLength()
//  message body length = middle limit
//////////

    @Test
    public void testIsMessageBodyValidByLengthWithMiddleLimit (){
        //given
        MessageModel message = this.messageValid;
        message.setMessageBody(makeStringByStringLength(100));
        Mockito.when(baseStringFieldValidator.isStringFieldsValidByLength(10, 200, message.getMessageBody())).thenReturn(true);
        //when
        messageServiceValidator.isMessageModelValid(message);
        boolean isMessageValidByMessageBodyLength = baseStringFieldValidator.isStringFieldsValidByLength(10, 200, message.getMessageBody());
        //then
        Assert.assertTrue(isMessageValidByMessageBodyLength);
    }

//////////
//  testing BaseStringFieldValidator.isStringFieldsValidByLength()
//  message body length = high limit
//////////

    @Test
    public void testIsMessageBodyValidByLengthWithHighLimit (){
        //given
        MessageModel message = this.messageValid;
        message.setMessageBody(makeStringByStringLength(200));
        Mockito.when(baseStringFieldValidator.isStringFieldsValidByLength(10, 200, message.getMessageBody())).thenReturn(true);
        //when
        messageServiceValidator.isMessageModelValid(message);
        boolean isMessageValidByMessageBodyLength = baseStringFieldValidator.isStringFieldsValidByLength(10, 200, message.getMessageBody());
        //then
        Assert.assertTrue(isMessageValidByMessageBodyLength);
    }

//////////
//  testing BaseStringFieldValidator.isStringFieldsValidByLength()
//  message body length = under low limit
//////////

    @Test(expected = IllegalArgumentException.class)
    public void testIsMessageBodyValidByLengthWithUnderLowLimit (){
        //given
        MessageModel message = this.messageValid;
        message.setMessageBody(makeStringByStringLength(9));
        Mockito.when(baseStringFieldValidator.isStringFieldsValidByLength(10, 200, message.getMessageBody())).thenThrow(IllegalArgumentException.class);
        //when
        baseStringFieldValidator.isStringFieldsValidByLength(10, 200, message.getMessageBody());
        //then
    }

//////////
//  testing BaseStringFieldValidator.isStringFieldsValidByLength()
//  message body length = upper high limit
//////////

    @Test(expected = IllegalArgumentException.class)
    public void testIsMessageBodyValidByLengthWithUpperHighLimit (){
        //given
        MessageModel message = this.messageValid;
        message.setMessageBody(makeStringByStringLength(201));
        Mockito.when(baseStringFieldValidator.isStringFieldsValidByLength(10, 200, message.getMessageBody())).thenThrow(IllegalArgumentException.class);
        //when
        baseStringFieldValidator.isStringFieldsValidByLength(10, 200, message.getMessageBody());
        //then
    }

//////////
//  testing DefaultMessageServiceValidatorImpl.isMessageModelValid()
//  message topic length = low limit
//////////

    @Test
    public void testIsMessageTopicValidByLengthWithLowLimit (){
        //given
        MessageModel message = this.messageValid;
        message.setMessageTopic(makeStringByStringLength(5));
        Mockito.when(baseStringFieldValidator.isStringFieldsValidByLength(5, 50, message.getMessageBody())).thenReturn(true);
        //when
        messageServiceValidator.isMessageModelValid(message);
        boolean isMessageValidByMessageBodyLength = baseStringFieldValidator.isStringFieldsValidByLength(5, 50, message.getMessageBody());
        //then
        Assert.assertTrue(isMessageValidByMessageBodyLength);
    }

//////////
//  testing DefaultMessageServiceValidatorImpl.isMessageModelValid()
//  message topic length = middle limit
//////////

    @Test
    public void testIsMessageTopicValidByLengthWithMiddleLimit (){
        //given
        MessageModel message = this.messageValid;
        message.setMessageTopic(makeStringByStringLength(25));
        Mockito.when(baseStringFieldValidator.isStringFieldsValidByLength(5, 50, message.getMessageBody())).thenReturn(true);
        //when
        messageServiceValidator.isMessageModelValid(message);
        boolean isMessageValidByMessageBodyLength = baseStringFieldValidator.isStringFieldsValidByLength(5, 50, message.getMessageBody());
        //then
        Assert.assertTrue(isMessageValidByMessageBodyLength);
    }

//////////
//  testing DefaultMessageServiceValidatorImpl.isMessageModelValid()
//  message topic length = high limit
//////////

    @Test
    public void testIsMessageTopicValidByLengthWithHighLimit (){
        //given
        MessageModel message = this.messageValid;
        message.setMessageTopic(makeStringByStringLength(50));
        Mockito.when(baseStringFieldValidator.isStringFieldsValidByLength(5, 50, message.getMessageBody())).thenReturn(true);
        //when
        messageServiceValidator.isMessageModelValid(message);
        boolean isMessageValidByMessageBodyLength = baseStringFieldValidator.isStringFieldsValidByLength(5, 50, message.getMessageBody());
        //then
        Assert.assertTrue(isMessageValidByMessageBodyLength);
    }

//////////
//  testing DefaultMessageServiceValidatorImpl.isMessageModelValid()
//  message topic length = under low limit
//////////

    @Test(expected = IllegalArgumentException.class)
    public void testIsMessageTopicValidByLengthWithUnderLowLimit (){
        //given
        MessageModel message = this.messageValid;
        message.setMessageTopic(makeStringByStringLength(4));
        Mockito.when(baseStringFieldValidator.isStringFieldsValidByLength(5, 50, message.getMessageBody())).thenThrow(IllegalArgumentException.class);
        //when
        baseStringFieldValidator.isStringFieldsValidByLength(5, 50, message.getMessageBody());
        //then
    }

//////////
//  testing DefaultMessageServiceValidatorImpl.isMessageModelValid()
//  message topic length = upper high limit
//////////

    @Test(expected = IllegalArgumentException.class)
    public void testIsMessageTopicValidByLengthWithUpperHighLimit (){
        //given
        MessageModel message = this.messageValid;
        message.setMessageTopic(makeStringByStringLength(51));
        Mockito.when(baseStringFieldValidator.isStringFieldsValidByLength(5, 50, message.getMessageBody())).thenThrow(IllegalArgumentException.class);
        //when
        baseStringFieldValidator.isStringFieldsValidByLength(5, 50, message.getMessageBody());
        //then
    }

//////////
//  testing DefaultMessageServiceValidatorImpl.isMessageModelListValid()
//////////

    @Test
    public void testIsMessageModelListValidWithHappyEnd() {
        //given
        List<MessageModel> messageListValid = this.messageModelListValid;
        Mockito.when(messageServiceValidator.isMessageModelListValid(messageListValid)).thenReturn(true);
        Mockito.when(baseMessageValidator.isMessageModelListValid(messageListValid)).thenReturn(true);
        //when
        boolean isMessageListValidByServiceValidator = messageServiceValidator.isMessageModelListValid(messageListValid);
        boolean isMessageListValidByBaseValidator = baseMessageValidator.isMessageModelListValid(messageListValid);
        //then
        Assert.assertTrue(isMessageListValidByBaseValidator);
        Assert.assertTrue(isMessageListValidByServiceValidator);
    }

    @Test(expected = CustomServiceException.class)
    public void testIsMessageModelListValidWithInvalidList() {
        //given
        List<MessageModel> messageListInvalid = this.messageModelListInvalid;
        Mockito.when(messageServiceValidator.isMessageModelListValid(messageListInvalid)).thenThrow(CustomServiceException.class);
        Mockito.when(baseMessageValidator.isMessageModelListValid(messageListInvalid)).thenThrow(IllegalArgumentException.class);
        //when
        messageServiceValidator.isMessageModelListValid(messageListInvalid);
        baseMessageValidator.isMessageModelListValid(messageListInvalid);
        //then
    }

    private String makeStringByStringLength(int stringLength) {
        StringBuilder stringBuilder = new StringBuilder("");
        for(int i = 0; i < stringLength; i++){
            stringBuilder.append("a");
        }
        return stringBuilder.toString();
    }
}