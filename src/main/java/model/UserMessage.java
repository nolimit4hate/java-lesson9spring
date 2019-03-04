package model;

import java.util.List;

public class UserMessage {

    private int messageID;
    private String creationDate;
    private User creator;
    private String messageTopic;
    private String messageBody;

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getMessageTopic() {
        return messageTopic;
    }

    public void setMessageTopic(String messageTopic) {
        this.messageTopic = messageTopic;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "messageID=" + messageID +
                ", creationDate='" + creationDate + '\'' +
                ", creator=" + creator +
                ", messageTopic='" + messageTopic + '\'' +
                ", messageBody='" + messageBody + '\'' +
                '}';
    }
}
