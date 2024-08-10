package edu.school21.program;

import edu.school21.classes.Car;
import edu.school21.classes.User;
import edu.school21.menu.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu(User.class, Car.class);
        menu.start();
    }
}
