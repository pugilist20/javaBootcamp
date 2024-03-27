package ex00;

public class Main {
    public static void main(String[] args) {
        int i=479598;
        int sum=0;
        while (i>0){
            sum += i % 10;
            i/=10;
        }
        System.out.println(sum);
    }
}