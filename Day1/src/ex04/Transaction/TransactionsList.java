package ex04.Transaction;

import java.util.UUID;

public interface TransactionsList {
    public void addTransaction(Transaction transaction);
    public void removeTransactionByID(UUID identifier) throws TransactionNotFoundException;
    public Transaction[] toArray();
}
