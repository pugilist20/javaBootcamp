package ex02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Math.sqrt;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            int num;
            int count=0;
            while((num=Integer.parseInt(reader.readLine()))!=42){
                if(num==0||num==1){
                    throw new IOException();
                }
                if(isPrimeNumber(sumOfDigits(num))){
                    count++;
                }
            }
            System.out.println("Count of coffee-request – "+count);
        }catch(IOException | NumberFormatException e){
            System.err.println("Illegal argument");
        }

    }
    static int sumOfDigits(int num){
        int sum=0;
        while (num>0){
            sum += num % 10;
            num/=10;
        }
        return sum;
    }
    static boolean isPrimeNumber(int num){
        for (int i = 2; i <= sqrt(num) ; i++) {
            if(num%i==0){
                return false;
            }
        }
        return true;
    }
}
