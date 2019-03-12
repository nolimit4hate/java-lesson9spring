package com.tmg.lesson9.front.form;

public class MessageSendForm {

    private String messageTopic;
    private String messageBody;
    private String messageCreator;

    public MessageSendForm() {
    }

    public MessageSendForm(String messageCreator) {
        this.messageCreator = messageCreator;
    }

    public String getMessageCreator() {
        return messageCreator;
    }

    public void setMessageCreator(String messageCreator) {
        this.messageCreator = messageCreator;
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
        return "MessageSendForm{" +
                "messageTopic='" + messageTopic + '\'' +
                ", messageBody='" + messageBody + '\'' +
                ", messageCreator='" + messageCreator + '\'' +
                '}';
    }
}
