package com.tmg.lesson9.model.message;

public class MessageModel {

    private int id;
    private String messageTopic;
    private String messageBody;
    private String messageCreator;
    private String dateTimeCreation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMessageCreator() {
        return messageCreator;
    }

    public void setMessageCreator(String messageCreator) {
        this.messageCreator = messageCreator;
    }

    public String getDateTimeCreation() {
        return dateTimeCreation;
    }

    public void setDateTimeCreation(String dateTimeCreation) {
        this.dateTimeCreation = dateTimeCreation;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "id='" + id + '\'' +
                ", messageTopic='" + messageTopic + '\'' +
                ", messageBody='" + messageBody + '\'' +
                ", messageCreator='" + messageCreator + '\'' +
                ", dateTimeCreation='" + dateTimeCreation + '\'' +
                '}';
    }
}
