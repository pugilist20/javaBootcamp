package ex04.Transaction;

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
    public void removeTransactionByID(UUID identifier) throws TransactionNotFoundException {
        if (first != null) {
            Transaction tmp = first;
            for (int i = 0; i < amountOfTransactions; i++) {
                if (tmp.getIdentifier() == identifier) {
                    tmp.getPrev().setNext(tmp.getNext());
                    tmp.getNext().setPrev(tmp.getPrev());
                    amountOfTransactions--;
                    if (tmp==last){
                        last=tmp.getPrev();
                    }
                    if (tmp==first){
                        first=tmp.getNext();
                    }
                    return;
                }
                tmp=tmp.getNext();
            }
        }
        throw new TransactionNotFoundException();
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
