package ex05;

import ex05.Transaction.*;
import ex05.User.User;
import ex05.User.UserNotFoundException;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private boolean isDev = false;
    private TransactionsService transactionsService = new TransactionsService();
    private Scanner scanner = new Scanner(System.in);

    public Menu(boolean isDev) {
        this.isDev = isDev;
    }

    public void menu() {
        boolean stop = true;
        while (stop) {
            System.out.println("1. Add a user");
            System.out.println("2. View user balances");
            System.out.println("3. Perform a transfer");
            System.out.println("4. View all transactions for a specific user");
            if (isDev) {
                System.out.println("5. DEV – remove a transfer by ID");
                System.out.println("6. DEV – check transfer validity");
                System.out.println("7. Finish execution");
            } else {
                System.out.println("5. Finish execution");
            }
            try {
                int command = scanner.nextInt();
                switch (command) {
                    case 1: {
                        addUser();
                        break;
                    }
                    case 2: {
                        viewUserBalance();
                        break;
                    }
                    case 3: {
                        performTransfer();
                        break;
                    }
                    case 4: {
                        viewAllUserTransaction();
                        break;
                    }
                    case 5: {
                        if (isDev) {
                            removeTransactionByID();
                        } else {
                            stop = false;
                        }
                        break;
                    }
                    case 6: {
                        if (isDev) {
                            checkTransferValidity();
                        }
                        break;
                    }
                    case 7: {
                        if (isDev) {
                            stop = false;
                        }
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: incorrect data was entered");
                System.out.println();
                scanner.next();
            }
        }
    }

    public void addUser() {
        try {
            System.out.println("Enter a user name and balance");
            User user = new User(scanner.next(), scanner.nextInt());
            transactionsService.addUser(user);
            System.out.println("User with id = " + user.getIdentifier() + " is added");
        } catch (Exception exception) {
            System.out.println("Error: incorrect data of user. Please try again.");
            addUser();
        } finally {
            System.out.println();
        }
    }

    public void viewUserBalance() {
        try {
            System.out.println("Enter a user ID");
            Integer userID = scanner.nextInt();
            User user = transactionsService.getUser(userID);
            System.out.println(user.getName() + " - " + user.getBalance());
        } catch (Exception e) {
            System.out.println("Error: non-existent user ID. Please try again.");
            viewUserBalance();
        } finally {
            System.out.println();
        }
    }

    public void performTransfer() {
        try {
            System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
            Integer senderID = scanner.nextInt();
            Integer recipientID = scanner.nextInt();
            Integer transferAmount = scanner.nextInt();
            transactionsService.makeTransaction(senderID, recipientID, transferAmount);
            System.out.println("The transfer is completed");
        } catch (InputMismatchException e) {
            System.out.println("Error: incorrect data was entered");
            scanner.next();
        } catch (UserNotFoundException e) {
            System.out.println("Error: the user was not found");
        } catch (IllegalTransactionException e) {
            System.out.println("Error: invalid transaction data. Please try again.");
        } finally {
            System.out.println();
        }
    }

    public void viewAllUserTransaction() {
        try {
            transactionsService.printUserTransactions(scanner.nextInt());
        } catch (InputMismatchException e) {
            System.out.println("Error: incorrect data was entered");
            scanner.next();
        } finally {
            System.out.println();
        }
    }

    public void removeTransactionByID() {
        try {
            System.out.println("Enter a user ID and a transfer ID");
            Integer userID = scanner.nextInt();
            String transferId = scanner.next();
            User user = transactionsService.getUser(userID);
            TransactionsLinkedList transactionsLinkedList=user.getTransactionsList();
            Transaction removedTransaction = transactionsLinkedList.findTransaction(UUID.fromString(transferId));
            transactionsLinkedList.removeTransaction(UUID.fromString(transferId));
            removedTransaction.printTransaction();
            System.out.println(" removed");
        } catch (InputMismatchException e) {
            System.out.println("Error: incorrect data was entered");
            scanner.next();
        } catch (UserNotFoundException e) {
            System.out.println("Error: the user was not found");
        } catch (TransactionNotFoundException e) {
            System.out.println("Error: the transaction was not found");
        } finally {
            System.out.println();
        }
    }

    public void checkTransferValidity() {
        try {
            System.out.println("Check results:");
            transactionsService.printUnpairedTransactions(transactionsService.getUnpairedTransactions());
        } catch (UserNotFoundException e) {
            System.out.println("Error: the user was not found");
        } finally {
            System.out.println();
        }
    }
}
