package ex04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Character, Integer> map=new HashMap<>();
        for (int i = 0; i < 999; i++) {
            char symbol=(char)reader.read();
            if(symbol=='\n'){
                break;
            }
            else {
                    map.put(symbol, map.getOrDefault(symbol,0) + 1);
            }
        }
        if(map.isEmpty()){
            System.err.println("Error");
            System.exit(-1);
        }
        print(map);
    }
    static void print(HashMap<Character,Integer> map){
        char[] symbols;
        int[] count;
        if(map.size()>=10) {
            symbols = new char[10];
            count = new int[10];
        }
        else{
            symbols = new char[map.size()];
            count = new int[map.size()];
        }
        for (int i = 0; i < symbols.length; i++) {
            int max=Integer.MIN_VALUE;
            char symbol='\0';
            for (Map.Entry<Character, Integer> entry: map.entrySet()) {
                if (entry.getValue() > max) {
                    max = entry.getValue();
                    symbol = entry.getKey();
                }
            }
            count[i]=max;
            symbols[i]=symbol;
            map.remove(symbols[i]);
        }
        double del=(double) count[0]/ symbols.length;
        int j=0;
        int i=-1;
        while(j!=symbols.length){
            i++;
            if(count[j]>=del*(symbols.length-i)){
                for (int k = 0; k < j; k++) {
                    System.out.print("#   ");
                }
                if(j+1!=symbols.length&&count[j+1]>=del*(symbols.length-i)){
                    i--;
                    System.out.print(count[j]+"  "+count[j+1]);
                    System.out.println();
                    j++;
                }
                else {
                    System.out.println(count[j]);
                }
                j++;
            }
            else{
                for (int k = 0; k < j; k++) {
                    System.out.print("#   ");
                }
                System.out.println();
            }
        }
        for (char charik :
                symbols) {
            System.out.print(charik+"   ");
        }
    }
}
