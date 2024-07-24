package edu.school21.numbers;

public class NumberWorker {
    public boolean isPrime(int num) {
        if(num < 2){
            throw new IllegalNumberException("Illegal number");
        }
        int counter=0;
        for(int i=2;i<=num/2;i++){
            if(num%i==0){
                counter++;
            }
        }
        return counter == 0;
    }
    public int digitsSum(int num){
        int sum=0;
        while(num>0){
            sum+=num%10;
            num/=10;
        }
        return sum;
    }
}
