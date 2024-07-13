package edu.school21.chat.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private Integer id;
    private User creator;
    private Chatroom chatroom;
    private String text;
    private LocalDateTime dateTime;

    public Message(Integer id, User creator, Chatroom chatroom, String text, LocalDateTime dateTime) {
        this.id = id;
        this.creator = creator;
        this.chatroom = chatroom;
        this.text = text;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public User getCreator() {
        return creator;
    }

    public Chatroom getChatroom() {
        return chatroom;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setChatroom(Chatroom chatroom) {
        this.chatroom = chatroom;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(message.getCreator(), this.creator) &&
                Objects.equals(message.getChatroom(), this.chatroom) &&
                Objects.equals(message.getText(), this.text) &&
                Objects.equals(message.getDateTime(), this.dateTime);
    }

    @Override
    public int hashCode() {
        int result = creator == null ? 0 : creator.hashCode();
        result=31*result+(chatroom==null?0:chatroom.hashCode());
        result=31*result+(text == null ? 0 : text.hashCode());
        result=31*result+(dateTime == null ? 0 : dateTime.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", " + creator.toString() + '\'' +
                ", " + chatroom.toString() +
                ", text='" + text + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
