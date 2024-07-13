package ex04;

import ex04.Transaction.IllegalTransactionException;
import ex04.Transaction.Transaction;
import ex04.Transaction.TransactionNotFoundException;
import ex04.Transaction.TransactionsService;
import ex04.User.User;
import ex04.User.UserNotFoundException;

import java.util.Arrays;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws UserNotFoundException, IllegalTransactionException {
        User john = new User("John", 1000);
        User mike = new User("Mike", 1000);
        User jack = new User("Jack", 1000);

        TransactionsService transactionsService = new TransactionsService();
        transactionsService.addUser(john);
        transactionsService.addUser(mike);
        transactionsService.addUser(jack);

        transactionsService.makeTransaction(john.getIdentifier(), mike.getIdentifier(), 200);
        transactionsService.makeTransaction(mike.getIdentifier(), john.getIdentifier(), 500);
        transactionsService.makeTransaction(john.getIdentifier(), mike.getIdentifier(), 500);

        Transaction[] array = transactionsService.getAllTransactions();
        System.out.println("Transactions:");
        for (int i = 0; i < array.length; i++) {
            System.out.println("\tTransfer amount = " + array[i].getTransferAmount());
        }

        System.out.println("\nJohn's balance = " + transactionsService.getUserBalance(john));
        System.out.println("Mike's balance = " + transactionsService.getUserBalance(mike));
        System.out.println("Jack's balance = " + transactionsService.getUserBalance(jack));

        Transaction[] unpairedTransactions = transactionsService.getUnpairedTransactions();
        if (unpairedTransactions.length == 0) {
            System.out.println("\nNo unpaired transactions");
        } else {
            System.out.println("\nUnpaired transactions have found:");
            for (int i = 0; i < array.length; i++) {
                System.out.println("\tTransaction's id = " + unpairedTransactions[i].getIdentifier());
            }

        }
    }
}
