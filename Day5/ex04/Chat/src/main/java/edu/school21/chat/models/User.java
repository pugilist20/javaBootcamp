package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    private Integer id;
    private String login;
    private String password;
    private ArrayList<Chatroom> createdRooms;
    private ArrayList<Chatroom> rooms;


    public User(int id, String login, String password, ArrayList<Chatroom> createdRooms, ArrayList<Chatroom> rooms) {
        this.id=id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.rooms = rooms;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int result = login == null ? 0 :login.hashCode();
        result = 31 * result + (password == null ? 0 : password.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null && obj.getClass() != getClass()) {
            return false;
        }
        User user = (User) obj;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) ;
    }

    @Override
    public String toString() {
        return "author={" +
                "id=" + id +
                ", login=\"" + login + '\"' +
                ", password=\"" + password + '\"' +
                ", createdRooms=" + (createdRooms==null? "null" :createdRooms) +
                ", rooms=" + (rooms==null?"null":rooms) +
                '}';
    }
}