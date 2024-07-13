package ex02;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        args=new String[2];
        args[0]="13";
        args[1]="3";
        int size = Integer.parseInt(args[0]);
        int counters = Integer.parseInt(args[1]);
        if (counters > Integer.parseInt(args[0])) {
            System.out.println("The number of threads exceeds the number of elements");
            System.exit(-1);
        }
        Random random = new Random();
        int[] arr = random.ints(size, 0, 1000).toArray();
        System.out.println(Arrays.toString(arr));
        int startPos;
        int endPos;
        int distance = size / counters;
        for (int i = 0; i < counters; i++) {
            startPos = i * distance;
            if (i == counters - 1) {
                endPos = size;
            } else {
                endPos = startPos + distance;
            }
            Thread thread = new Thread(new Counter(arr, i + 1, startPos, endPos));
            thread.start();
        }
    }
}
