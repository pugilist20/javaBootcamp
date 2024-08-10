package edu.school21.Manager;

import edu.school21.Annotations.OrmColumn;
import edu.school21.Annotations.OrmColumnId;
import edu.school21.Annotations.OrmEntity;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrmManager {
    Connection connection;

    public OrmManager(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    public void dropTable(Object obj) throws SQLException {
        String dropTable = "DROP TABLE IF EXISTS " + obj.getClass().getAnnotation(OrmEntity.class).table();
        PreparedStatement preparedStatement = connection.prepareStatement(dropTable);
        preparedStatement.execute();
    }

    public void createTable(Object obj) throws SQLException {
        if (obj.getClass().getAnnotation(OrmEntity.class) != null) {
            dropTable(obj);
            String createTable = "CREATE TABLE " + obj.getClass().getAnnotation(OrmEntity.class).table() + String.format("(%s)", findFields(obj));
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.execute();
        }
    }

    public String findFields(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder();
        for (Field field : fields) {
            if (field.getAnnotation(OrmColumnId.class) != null) {
                stringBuilder.append(String.format("%s serial primary key", field.getName()));
            } else {
                if (field.getAnnotation(OrmColumn.class) != null) {
                    stringBuilder.append(field.getName()).append(" ").append(convertType(field));
                }
            }
            if (field == fields[fields.length - 1]) {
                break;
            }
            stringBuilder.append(", ");
        }
        return stringBuilder.toString();
    }

    public String convertType(Field field) {
        String type = field.getType().getSimpleName();
        return switch (type) {
            case "Integer" -> "int";
            case "Long" -> "BIGINT";
            case "String" -> String.format("VARCHAR(%d)", field.getAnnotation(OrmColumn.class).length());
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

    public void save(Object obj) throws SQLException, IllegalAccessException {
        List<String> columnNamesList = getColumnNames(obj);
        String columnNames = String.join(", ", columnNamesList);
        String values = Stream.generate(() -> "?").limit(columnNamesList.size()).collect(Collectors.joining(", "));
        String query = "INSERT INTO " + obj.getClass().getAnnotation(OrmEntity.class).table() + "(" + columnNames + ") " + "values (" + values + ")";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        int index = 1;
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(OrmColumn.class) != null) {
                field.setAccessible(true);
                preparedStatement.setObject(index++, field.get(obj));
            }
        }
        preparedStatement.execute();
    }

    private static List<String> getColumnNames(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        List<String> list = new ArrayList<>();
        for (Field field : fields) {
            if (field.getAnnotation(OrmColumn.class) != null) {
                list.add(field.getName());
            }
        }
        return list;
    }

    private static List<String> getAllColumnNames(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        List<String> list = new ArrayList<>();
        for (Field field : fields) {
            list.add(field.getName());
        }
        return list;
    }

    public void update(Object obj) throws IllegalAccessException, SQLException {
        List<String> queryBuilder=new ArrayList<>();
        String query = "update" + obj.getClass().getAnnotation(OrmEntity.class).table()+" ";
        String idColumnName="";
        Object index=0;
        Field[] fields=obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(OrmColumn.class) != null) {
                field.setAccessible(true);
                String columnName=field.getAnnotation(OrmColumn.class).name();
                Object value=field.get(obj);
                queryBuilder.add(String.format("%s = '%s'", columnName, value));
                field.setAccessible(false);
            }
            if(field.getAnnotation(OrmColumnId.class)!=null){
                idColumnName=field.getName();
                field.setAccessible(true);
                index= field.get(obj);
                field.setAccessible(false);
            }
        }
        query+=String.join(", ", queryBuilder);

        query+=String.format(" WHERE %s = '%s';", idColumnName, index);

        Statement statement = connection.createStatement();
        statement.execute(query);
    }

    public Object findById(Object obj, int id) throws SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Field[] fields = obj.getClass().getDeclaredFields();
        String columnName = "";
        for (Field field : fields) {
            if (field.getAnnotation(OrmColumnId.class) != null) {
                columnName = field.getName();
            }
        }
        String query = "Select * from " + obj.getClass().getAnnotation(OrmEntity.class).table() + " where " + columnName + "=" + id;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        Object newObject = obj.getClass().getDeclaredConstructor().newInstance();
        Field[] fieldss = obj.getClass().getDeclaredFields();
        ResultSet resultSet = preparedStatement.executeQuery();
        int index = 0;
        List<String> columnNamesList = getAllColumnNames(newObject);
        while (resultSet.next()) {
            if (fieldss[index].getType().getSimpleName().equals("Integer")) {
                int intType = resultSet.getInt(columnNamesList.get(index));
                fieldss[index].setAccessible(true);
                fieldss[index++].set(newObject, intType);
            } else if (fieldss[index].getType().getSimpleName().equals("Long")) {
                long longType = resultSet.getLong(columnNamesList.get(index));
                fieldss[index].setAccessible(true);
                fieldss[index++].set(newObject, longType);
            } else if (fieldss[index].getType().getSimpleName().equals("String")) {
                String stringType = resultSet.getString(columnNamesList.get(index));
                fieldss[index].setAccessible(true);
                fieldss[index++].set(newObject, stringType);
            }
        }
        return newObject;
    }
}
