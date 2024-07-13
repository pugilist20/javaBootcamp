package ex03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Reader {
    public static void read(ArrayList<String> arrayList) {
        BufferedReader reader;
        boolean stop = true;
        while (stop) {
            try {
                reader = new BufferedReader(new FileReader("src\\ex03\\files_urls.txt"));
                String temp = "";
                while ((temp = reader.readLine()) != null) {
                    String[] words=temp.split(" ");
                    arrayList.addAll(Arrays.asList(words));
                }
                stop=false;
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден");
                stop=false;
            } catch (IOException e) {
                System.out.println("Произошла ошибка при считывании файла");
                stop=false;
            }
        }
    }
}
