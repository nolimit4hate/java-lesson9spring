package com.tmg.lesson9.front.form;

public class MessageShowForm extends MessageSendForm {

    private String dateTimeCreation;

    public String getDateTimeCreation() {
        return dateTimeCreation;
    }

    public void setDateTimeCreation(String dateTimeCreation) {
        this.dateTimeCreation = dateTimeCreation;
    }

    @Override
    public String toString() {
        String superString = super.toString();
        superString = superString.substring(13, superString.length() - 1);
        return "MessageShowForm{" + superString +
                "dateTimeCreation='" + dateTimeCreation + '\'' +
                '}';
    }
}
