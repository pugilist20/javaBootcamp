package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.Objects;

public class Chatroom {
    private int id;
    private String name;
    private Integer ownerID;
    private ArrayList<Message> messages;

    public Chatroom(int id, String name, Integer ownerID, ArrayList<Message> messages) {
        this.id = id;
        this.name = name;
        this.ownerID = ownerID;
        this.messages = messages;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getOwnerID() {
        return ownerID;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null && obj.getClass() != getClass()) {
            return false;
        }
        Chatroom other = (Chatroom) obj;
        return Objects.equals(other.getName(), this.getName())&&
                Objects.equals(other.getOwnerID(), this.getOwnerID())&&
                Objects.equals(other.getMessages(), this.getMessages());
    }
    @Override
    public int hashCode() {
        int result=name==null?0:name.hashCode();
        result=31*result+(ownerID==null?0:ownerID.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "room={" +
                "id=" + id +
                ", name=\"" + name + '\"' +
                ", creator=" + ownerID +
                ", messages=" + (messages==null?messages:"null") +
                '}';
    }
}
