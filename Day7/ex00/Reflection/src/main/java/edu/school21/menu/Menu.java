package edu.school21.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Menu {
    Class firstClass;
    Class secondClass;
    Object object;

    public Menu(Class firstClass, Class secondClass) {
        this.firstClass = firstClass;
        this.secondClass = secondClass;
    }

    public void start() {
        chooseClass();
    }

    private void chooseClass() {
        System.out.print("Classes: \n    -" +
                firstClass.getSimpleName() + "\n    -" +
                secondClass.getSimpleName() + "\n" +
                "---------------------\n" +
                "Enter class name:\n" +
                "-> ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String className;
        try {
            className = reader.readLine();
            printFields(Class.forName("edu.school21.classes." + className));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void printFields(Class className) {
        Field[] fields = className.getDeclaredFields();
        System.out.println("fields:");
        for (Field field : fields) {
            System.out.println("        " + field.getType().getSimpleName() + " " + field.getName());
        }
        printMethods(className);
    }

    private void printMethods(Class className) {
        Method[] methods = className.getDeclaredMethods();
        System.out.println("methods:");
        for (Method method : methods) {
            if (!Objects.equals(Arrays.toString(method.getParameterTypes()), "[]")) {
                System.out.println("        " + method.getReturnType().getSimpleName() + " " + method.getName() + "(" + Arrays.toString(method.getParameterTypes()).replace("[", "").replace("]", "") + ")");
            }
        }
        System.out.println("---------------------");
        createObject(className);
    }

    private void createObject(Class className) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            object = Class.forName(String.valueOf(className).replace("class ", "")).newInstance();
            System.out.println("Let’s create an object.");
            Field[] fields = className.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                System.out.print(field.getName() + ":\n -> ");
                setField(reader, field);
            }
            System.out.println("Object created: " + object.toString());
            System.out.println("---------------------");
            changeField(className);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    private int checkType(Class type) throws IOException {
        if (type.equals(int.class)) {
            return 1;
        } else if (type.equals(double.class)) {
            return 2;
        } else if (type.equals(boolean.class)) {
            return 3;
        } else if (type.equals(long.class)) {
            return 4;
        } else {
            return 5;
        }
    }

    private void setField(BufferedReader reader, Field field) throws IOException, IllegalAccessException {
        int type = checkType(field.getType());
        switch (type) {
            case 1: {
                field.set(object, Integer.parseInt(reader.readLine()));
                break;
            }
            case 2: {
                field.set(object, Double.parseDouble(reader.readLine()));
                break;
            }
            case 3: {
                field.set(object, Boolean.parseBoolean(reader.readLine()));
                break;
            }
            case 4: {
                field.set(object, Long.parseLong(reader.readLine()));
                break;
            }
            case 5: {
                field.set(object, reader.readLine());
                break;
            }
        }
    }

    private void changeField(Class className) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter name of the field for changing:\n-> ");
        try {
            String fieldName = reader.readLine();
            Field[] fields = className.getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().equals(fieldName)) {
                    field.setAccessible(true);
                    System.out.print("Enter " + field.getType().getSimpleName() + " value\n-> ");
                    setField(reader, field);
                }
            }
            System.out.println("Object updated " + object.toString());
            System.out.println("---------------------");
            callMethod(className);
        } catch (IOException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void callMethod(Class className) {
        System.out.print("Enter name of the method for call:\n-> ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String methodName = reader.readLine();
            Method[] methods = className.getDeclaredMethods();
            for (Method method : methods) {
                if (Objects.equals(method.getName() + "(" + Arrays.toString(method.getParameterTypes()).replace("[", "").replace("]", "") + ")", methodName)) {
                    System.out.print("Enter " + Arrays.toString(method.getParameterTypes()).replace("[", "").replace("]", "") + " value:\n-> ");
                    Class<?>[] parameters = method.getParameterTypes();
                    Object result;
                    ArrayList<Object> parametersInput=new ArrayList<>();
                    for (Class<?> parameter : parameters) {
                        String parameterName = parameter.getSimpleName();
                        if(parameterName.equals(int.class.getName())) {
                            parametersInput.add(Integer.parseInt(reader.readLine()));
                        }
                        else if(parameterName.equals(double.class.getName())) {
                            parametersInput.add(Double.parseDouble(reader.readLine()));
                        }
                        else if(parameterName.equals(boolean.class.getName())) {
                            parametersInput.add(Boolean.parseBoolean(reader.readLine()));
                        }
                        else if(parameterName.equals(long.class.getName())) {
                            parametersInput.add(Long.parseLong(reader.readLine()));
                        }
                        else if(parameterName.equals(String.class.getName())) {
                            parametersInput.add(reader.readLine());
                        }
                    }
                    result = method.invoke(object, parametersInput.toArray());
                    System.out.println("Method returned:\n" + result);
                }
            }
        } catch (IOException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
