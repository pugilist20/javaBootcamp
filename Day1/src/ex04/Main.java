package ex04;

import ex04.Transaction.Transaction;
import ex04.Transaction.TransactionNotFoundException;
import ex04.User.User;
import ex04.User.UserNotFoundException;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws UserNotFoundException, TransactionNotFoundException {
        User john = new User("John", 1000);
        User mike = new User("Mike", 1000);
        Transaction outcome = new Transaction(john, mike, "credits", -200);
        Transaction income = new Transaction(mike, john, "debits", 500);
        Transaction outcome1 = new Transaction(mike, john, "credits", -500);

        john.addTransaction(outcome);
        john.addTransaction(income);
        john.addTransaction(outcome1);

        System.out.println("\nAdded 3 transactions -> number of transactions = " + john.getTransactionsList().getAmountOfTransactions());
        System.out.println("First transaction: " + john.getTransactionsList().getFirst().getSender().getName() + " -> " +
                john.getTransactionsList().getFirst().getRecipient().getName() + " " + john.getTransactionsList().getFirst().getTransferAmount());
        System.out.println("Last transaction: " + john.getTransactionsList().getLast().getSender().getName() + " -> " +
                john.getTransactionsList().getLast().getRecipient().getName() + " " + john.getTransactionsList().getLast().getTransferAmount());

        john.getTransactionsList().removeTransactionByID(john.getTransactionsList().getFirst().getIdentifier());
        System.out.println("After the first transaction was deleted -> number of transactions = " + john.getTransactionsList().getAmountOfTransactions());
        System.out.println("First transaction: " + john.getTransactionsList().getFirst().getSender().getName() + " -> " +
                john.getTransactionsList().getFirst().getRecipient().getName() + " " + john.getTransactionsList().getFirst().getTransferAmount());
        System.out.println("Last transaction: " + john.getTransactionsList().getLast().getSender().getName() + " -> " +
                john.getTransactionsList().getLast().getRecipient().getName() + " " + john.getTransactionsList().getLast().getTransferAmount());

        Transaction[] array = john.getTransactionsList().toArray();
        System.out.println("Transactions array: " + Arrays.toString(array));
        System.out.println();
    }
}
