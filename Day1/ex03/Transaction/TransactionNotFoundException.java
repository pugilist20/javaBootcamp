package ex03.Transaction;

public class TransactionNotFoundException extends Exception{
    public TransactionNotFoundException(){
        super("Transaction not found");
    }
}