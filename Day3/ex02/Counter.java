package ex02;

public class Counter implements Runnable {

    int[] arr;
    int startPos;
    int endPos;
    static int sum;
    int number;

    public Counter(int[] arr, int number, int startPos, int endPos) {
        this.arr = arr;
        this.number = number;
        this.startPos = startPos;
        this.endPos = endPos;
    }

    @Override
    public void run() {
        int localSum = 0;
        for (int i = startPos; i < endPos; i++) {
            localSum += arr[i];
        }
        sum += localSum;
        System.out.printf("Thread %d: from %d to %d sum is %d\n", number, startPos, endPos-1, localSum);
        if(endPos==arr.length){
            System.out.printf("Sum by threads: %d\n", sum);
        }
    }
}
