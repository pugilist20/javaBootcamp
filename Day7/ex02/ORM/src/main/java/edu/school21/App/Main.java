package edu.school21.App;

import edu.school21.Manager.OrmManager;
import edu.school21.Model.User;
import edu.school21.App.Database.DBConnector;


import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        OrmManager ormManager=new OrmManager(DBConnector.getConnection());
        User user=new User();
        ormManager.createTable(user);
        ormManager.save(user);
        User user1= (User) ormManager.findById(user,1);
        System.out.println(user1);
    }
}
