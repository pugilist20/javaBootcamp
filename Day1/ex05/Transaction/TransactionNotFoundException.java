package ex05.Transaction;

public class TransactionNotFoundException extends Exception{
    public TransactionNotFoundException(){
        super("Transaction not found");
    }
}
