package edu.school21.chat.app;

import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            MessagesRepositoryJdbcImpl repositoryJdbc= new MessagesRepositoryJdbcImpl( DBConnector.getConnection());
            while (true) {
                System.out.println("Enter a message ID: ");
                String messageID = scanner.nextLine();
                if(messageID.equals("exit")) {
                    break;
                }
                repositoryJdbc.findById(Long.parseLong(messageID));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
}
