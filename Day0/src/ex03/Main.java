package ex03;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        takeGrades();
    }

    static void takeGrades() {
        int result=0;
        try {
            Scanner scanner = new Scanner(System.in);
            for (int week = 1; week < 19; week++) {
                String str = scanner.nextLine();
                if (str.equals("42")) {
                    print(result);
                    break;
                } else if (str.equals("Week " + week)) {
                    int min=Integer.MAX_VALUE;
                    for (int i = 0; i < 5; i++) {
                        int num=scanner.nextInt();
                        if(num<1 || num>9){
                            throw new InputMismatchException();
                        }
                        else if(num<min){
                            min=num;
                        }
                    }
                    if(week==1){
                        result+=min;
                    }
                    else{
                        result+=(week-1)*10*min;
                    }
                    scanner.nextLine();
                } else {
                    throw new InputMismatchException();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Illegal argument");
            System.exit(-1);
        }
        print(result);
    }

    static void print(int result) {
        int week=1;
        while (result > 0) {
            System.out.print("Week " + week+" ");
            int num=result%10;
            for (int i = 0; i < num; i++) {
                System.out.print("=");
            }
            System.out.println(">");
            result/=10;
            week++;
        }
    }
}
