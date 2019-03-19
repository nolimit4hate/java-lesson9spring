package com.tmg.lesson9.dao.message;

import com.tmg.lesson9.model.message.MessageModel;
import com.tmg.lesson9.model.user.UserModel;

import java.util.ArrayList;
import java.util.List;

public class FakeDbDataGetter {

    public List<MessageModel> getAllDbMessages(){
        List<MessageModel> allMessagesList = new ArrayList<>();
        // message 1
        MessageModel messageModel1 = new MessageModel();
        messageModel1.setId(1);
        messageModel1.setMessageTopic("new topic helloboy");
        messageModel1.setMessageBody("newnewnew bodbodybody11");
        messageModel1.setMessageCreator("helloboy");
        messageModel1.setDateTimeCreation("2019-03-12 09:31:14");
        // message 2
        MessageModel messageModel2 = new MessageModel();
        messageModel2.setId(2);
        messageModel2.setMessageTopic("tatatatatata");
        messageModel2.setMessageBody("body body body body body");
        messageModel2.setMessageCreator("helloboy");
        messageModel2.setDateTimeCreation("2019-03-12 09:31:36");
        // message 3
        MessageModel messageModel3 = new MessageModel();
        messageModel3.setId(3);
        messageModel3.setMessageTopic("tytytyty rrrr");
        messageModel3.setMessageBody("tyty1010 tyty1010 rrr");
        messageModel3.setMessageCreator("terminator");
        messageModel3.setDateTimeCreation("2019-03-13 16:26:57");
        // adding messages to message list
        allMessagesList.add(messageModel1);
        allMessagesList.add(messageModel2);
        allMessagesList.add(messageModel3);
        return allMessagesList;
    }

    public List<UserModel> getAllDbUsers() {
        List<UserModel> allUsersList = new ArrayList<>();
        // user 1
        UserModel user1 = new UserModel();
        user1.setId(1);
        user1.setUserName("helloboy");
        user1.setPassword("123123");
        user1.setEmail("helloboy@boy.ru");
        user1.setCountry("Ukraine");
        user1.setGender("Male");
        user1.setCreationDateTime("2019-03-11 22:32:56");
        // user 2
        UserModel user2 = new UserModel();
        user2.setId(23);
        user2.setUserName("humhum");
        user2.setPassword("123123");
        user2.setEmail("hum@hum.com");
        user2.setCountry("USA");
        user2.setGender("Female");
        user2.setCreationDateTime("2019-03-12 09:13:10");
        // user 3
        UserModel user3 = new UserModel();
        user3.setId(29);
        user3.setUserName("terminator");
        user3.setPassword("123123");
        user3.setEmail("terminator@gm.com");
        user3.setCountry("USA");
        user3.setGender("Male");
        user3.setCreationDateTime("2019-03-13 16:25:14");
        // adding users to users list
        allUsersList.add(user1);
        allUsersList.add(user2);
        allUsersList.add(user3);
        return allUsersList;
    }
}
