package ex05.Transaction;

import ex05.User.UserNotFoundException;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private Transaction first = null;
    private Transaction last = null;
    private Integer amountOfTransactions = 0;

    @Override
    public void addTransaction(Transaction transaction) {
        if (amountOfTransactions == 0) {
            first = transaction;
            last = transaction;
            transaction.setNext(transaction);
            transaction.setPrev(transaction);
        } else {
            last.setNext(transaction);
            transaction.setPrev(last);
            transaction.setNext(first);
            last=transaction;
        }
        amountOfTransactions++;
    }
    @Override
    public void removeTransaction(UUID uuid) throws TransactionNotFoundException {
        Transaction transaction = findTransaction(uuid);
        deleteTransactionByID(transaction);
    }

    public Transaction findTransaction(UUID uuid) throws TransactionNotFoundException {
        Transaction current = first;
        while (current != null) {
            if (current.getIdentifier().equals(uuid)) {
                return current;
            }
            current = current.getNext();
        }
        throw new TransactionNotFoundException();
    }
    public void deleteTransactionByID(Transaction transaction) throws TransactionNotFoundException {
        if (first != null) {
            if (transaction.getNext() == null) {
                last = transaction.getPrev();
            } else if (transaction.getPrev() == null) {
                first = transaction.getNext();
            } else {
                transaction.getPrev().setNext(transaction.getNext());
                transaction.getNext().setPrev(transaction.getPrev());
            }
            amountOfTransactions--;
        }
        else {
            throw new TransactionNotFoundException();
        }
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] transactions = new Transaction[amountOfTransactions];
        Transaction tmp=first;
        for (int i=0; i<amountOfTransactions;i++){
            transactions[i]=tmp;
            tmp=tmp.getNext();
        }
        return transactions;
    }

    public Transaction getFirst() {
        return first;
    }

    public Transaction getLast() {
        return last;
    }

    public Integer getAmountOfTransactions() {
        return amountOfTransactions;
    }
}
