package ex05.Transaction;

import java.util.UUID;

public interface TransactionsList {
    public void addTransaction(Transaction transaction);
    public void removeTransaction(UUID identifier) throws TransactionNotFoundException;
    public Transaction[] toArray();
}
