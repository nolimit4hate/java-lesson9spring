package DAO;

import model.User;
import model.UserMessage;

import java.util.List;

public interface DAOMessage {
    void addMessage(UserMessage userMessage);
    User getUserByMessageID(int messageID);
    List<UserMessage> getMessagesByUserName(String userName);
    List<UserMessage> getAllMessages();
}
