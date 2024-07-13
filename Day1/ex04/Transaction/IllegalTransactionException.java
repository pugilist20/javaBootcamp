package ex04.Transaction;

public class IllegalTransactionException extends Exception{
    public IllegalTransactionException(){
        System.err.println("Error: invalid transaction data");
    }
}
