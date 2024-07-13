package ex05.User;

import ex05.Transaction.Transaction;
import ex05.Transaction.TransactionNotFoundException;
import ex05.Transaction.TransactionsLinkedList;

import java.util.UUID;

public class User {
    private TransactionsLinkedList transactionsLinkedList=new TransactionsLinkedList();
    private Integer identifier;
    private String name;
    private Integer balance;
    public User(String name, Integer balance){
        this.identifier= UserIdsGenerator.generateID();
        this.name=name;
        this.balance=balance;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        if(identifier>=0) {
            this.identifier = identifier;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name!=null&& !name.isEmpty()) {
            this.name = name;
        }
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        if(balance>=0) {
            this.balance = balance;
        }
        else{
            System.out.println("Incorrect balance");
            System.exit(-1);
        }
    }
    public TransactionsLinkedList getTransactionsList() {
        return transactionsLinkedList;
    }

    public void addTransaction(Transaction transaction) {
        transactionsLinkedList.addTransaction(transaction);
    }

    public void deleteTransaction(UUID identifier) throws TransactionNotFoundException {
        transactionsLinkedList.removeTransaction(identifier);
    }
}
