package edu.school21.chat.models;

import java.util.Objects;

public class Message {
    private int id;
    private Integer authorID;
    private Integer chatroomID;
    private String text;
    private String dateTime;

    public Message(int id, Integer authorID, Integer chatroomID, String text, String dateTime) {
        this.id = id;
        this.authorID = authorID;
        this.chatroomID = chatroomID;
        this.text = text;
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAuthorID() {
        return authorID;
    }

    public Integer getChatroomID() {
        return chatroomID;
    }

    public String getText() {
        return text;
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(message.getAuthorID(), this.authorID) &&
                Objects.equals(message.getChatroomID(), this.chatroomID) &&
                Objects.equals(message.getText(), this.text) &&
                Objects.equals(message.getDateTime(), this.dateTime);
    }

    @Override
    public int hashCode() {
        int result = authorID == null ? 0 : authorID.hashCode();
        result=31*result+(chatroomID==null?0:chatroomID.hashCode());
        result=31*result+(text == null ? 0 : text.hashCode());
        result=31*result+(dateTime == null ? 0 : dateTime.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", authorID='" + authorID + '\'' +
                ", chatroomID=" + chatroomID +
                ", text='" + text + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
