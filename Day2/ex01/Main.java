package ex01;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;

public class Main {
    static HashSet<String> uniqueWords = new HashSet<>();

    public static void main(String[] args) {
        comparisonStrings(args[0], args[1]);
    }

    public static void comparisonStrings(String firstFileName, String secondFileName) {
        ArrayList<String> wordsFromFirstFile = new ArrayList<>();
        ArrayList<String> wordsFromSecondFile = new ArrayList<>();
        read(firstFileName, wordsFromFirstFile);
        read(secondFileName, wordsFromSecondFile);

        Vector<Integer> vectorFirst = findOutQuantity(wordsFromFirstFile);
        Vector<Integer> vectorSecond = findOutQuantity(wordsFromSecondFile);

        findOutSimilarity(vectorFirst,vectorSecond);
    }

    public static void read(String fileName, ArrayList<String> arrayList) {
        BufferedReader reader;
        boolean stop = true;
        while (stop) {
            try {
                reader = new BufferedReader(new FileReader(fileName));
                String temp = "";
                while ((temp = reader.readLine()) != null) {
                    String[] words=temp.split(" ");
                    for (String value : words) {
                        arrayList.add(value);
                        uniqueWords.add(value);
                    }
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
        write();
    }

    public static void write() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("result.txt"));
            for (String value : uniqueWords) {
                writer.write(value + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Vector<Integer> findOutQuantity(ArrayList<String> wordsFromFile) {
        Vector<Integer> vector = new Vector<>(uniqueWords.size());
        for (String value: uniqueWords) {
            int count=0;
            for (String word :wordsFromFile) {
                if(value.equals(word)){
                    count++;
                }
            }
            vector.add(count);
        }
        return vector;
    }
    public static void findOutSimilarity(Vector<Integer> firstVector, Vector<Integer> secondVector){
        int numerator=0;
        float denominatorA=0;
        float denominatorB=0;
        for (int i = 0; i < firstVector.size(); i++) {
            int first=firstVector.get(i);
            int second=secondVector.get(i);
            numerator+=first*second;
            denominatorA+= (float) Math.pow(first,2);
            denominatorB+= (float)Math.pow(second,2);
        }
        System.out.println(Math.floor((numerator/((Math.sqrt(denominatorA))*(Math.sqrt(denominatorB))))*100)/100);
    }
}
