package ex00;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        getHex();
    }

    public static void getHex() {
        FileInputStream fis = null;
        StringBuilder sb = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        Scanner scanner = new Scanner(System.in);
        try {
            String str="";
            while(!Objects.equals(str = scanner.nextLine(), "42")) {
                fis = new FileInputStream(str);
                sb = new StringBuilder();
                byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bytes = new byte[8];
                int b;
                for (int i = 0; i < 1; i++) {
                    b = fis.read(bytes);
                    byteArrayOutputStream.write(bytes, 0, b);
                }
                byte[] arr = byteArrayOutputStream.toByteArray();
                for (byte c : arr) {
                    if(c!=0 && c!=32) {
                        sb.append(String.format("%02X ", c));
                    }
                }
                sb.deleteCharAt(sb.length() - 1);
                findOutType(sb);
            }
        }catch (FileNotFoundException e){
            System.out.println("Incorrect file location");;
            getHex();
        }
        catch (IOException e) {
            e.printStackTrace();
            getHex();
        }

    }

    public static void findOutType(StringBuilder sb) {
        try {
            StringBuilder type=new StringBuilder();
            Scanner scanner = new Scanner(new FileInputStream("C:\\Users\\ogrrn\\IdeaProjects\\bootcamp\\Day2\\src\\ex00\\signatures.txt"));
            for (int i = 0; i < 10; i++) {
                String str = scanner.nextLine();
                if (str.contains(sb.toString())) {
                    boolean stop = true;
                    int j = 0;
                    while (stop) {
                        char value = str.charAt(j++);
                        if (value != ',') {
                            type.append(value);
                        } else {
                            stop = false;
                        }
                    }
                }
            }
            if(sb.isEmpty()) {
                System.out.println(type.toString());
            }
            else{
                System.out.println("Unknown type");
            }
            getHex();
        }
        catch(IOException e){
            findOutType(sb);
        }
    }
}
