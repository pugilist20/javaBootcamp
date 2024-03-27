package ex01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Math.sqrt;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int num=Integer.parseInt(reader.readLine());
            if(num<=1){
                throw new IOException();
            }
            isPrimeNumber(num);
        }
        catch(IOException | NumberFormatException e){
            System.err.println("Illegal argument");
        }
    }
    static void isPrimeNumber(int num){
        for (int i = 2; i <= sqrt(num) ; i++) {
            if(num%i==0){
                System.out.println("false" + " " + --i);
                return;
            }
        }
        System.out.println("true");
    }
}

