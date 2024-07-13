package ex05.Transaction;

import ex05.User.User;
import ex05.User.UserNotFoundException;
import ex05.User.UsersArrayList;

import java.util.UUID;

public class TransactionsService {
    private UsersArrayList usersList=new UsersArrayList();
    public void addUser(User user){
        usersList.addUser(user);
    }
    public User getUser(Integer ID) throws UserNotFoundException {
        return usersList.getUserByID(ID);
    }
    public Integer getUserBalance(User user){
        return user.getBalance();
    }
    public void makeTransaction(Integer id1, Integer id2, Integer transferAmount) throws UserNotFoundException, IllegalTransactionException {
        User sender = usersList.getUserByID(id1);
        User recipient = usersList.getUserByID(id2);

        if (sender.equals(recipient) || transferAmount > sender.getBalance() || transferAmount < 0) {
            throw new IllegalTransactionException();
        }

        Transaction debit = new Transaction(sender, recipient, "debits", transferAmount);
        Transaction credit = new Transaction(sender, recipient, "credits", -transferAmount);
        debit.setIdentifier(credit.getIdentifier());
        credit.setIdentifier(debit.getIdentifier());

        sender.addTransaction(credit);
        recipient.addTransaction(debit);

        sender.setBalance(sender.getBalance() - transferAmount);
        recipient.setBalance(recipient.getBalance() + transferAmount);
    }
    public Transaction[] getTransactions(User user){
        return user.getTransactionsList().toArray();
    }

    public Transaction[] getUnpairedTransactions() throws UserNotFoundException {
        if (usersList.getAmountOfUsers() == 0) {
            System.out.println("User's list is empty");
            return null;
        }

        TransactionsLinkedList unpairedTransactions = new TransactionsLinkedList();
        Transaction[] allTransactions = getAllTransactions();

        if (allTransactions.length == 1) {
            unpairedTransactions.addTransaction(allTransactions[0]);
        } else {
            for (int i = 0; i < allTransactions.length; i++) {
                boolean isPaired = false;
                for (int j = 0; j < allTransactions.length; j++) {
                    if (i != j && allTransactions[i].getIdentifier() == allTransactions[j].getIdentifier()) {
                        isPaired = true;
                        break;
                    }
                }
                if (!isPaired) {
                    unpairedTransactions.addTransaction(allTransactions[i]);
                }
            }
        }

        return unpairedTransactions.toArray();
    }
    public Transaction[] getAllTransactions() throws UserNotFoundException {
        TransactionsLinkedList allTransactionsLinkedList=new TransactionsLinkedList();
        for (int i = 1; i <usersList.getAmountOfUsers()+1; i++) {
            Transaction[] userTransactionsLinkedList=usersList.getUserByID(i).getTransactionsList().toArray();
            for (Transaction transaction : userTransactionsLinkedList) {
                allTransactionsLinkedList.addTransaction(transaction);
            }
        }
        return allTransactionsLinkedList.toArray();
    }
    public void printUserTransactions(Integer userID){
        try {
            User user=getUser(userID);
            Transaction[] transactionsList = user.getTransactionsList().toArray();
            for (Transaction transaction :
                    transactionsList) {
                Integer transferAmount = transaction.getTransferAmount();
                String name = transaction.getSender().getName();
                Integer id = transaction.getSender().getIdentifier();
                if (transferAmount < 0) {
                    System.out.print("To ");
                } else {
                    System.out.print("From ");
                    name = transaction.getSender().getName();
                    id = transaction.getSender().getIdentifier();
                }

                System.out.println(name + "(id = " + id
                        + ")" + " " + transferAmount
                        + " with id = " + transaction.getIdentifier());
            }
        } catch (UserNotFoundException e) {
            System.out.println("Error: the user was not found");
        }
    }
    public void printUnpairedTransactions(Transaction[] unpairedTransactions) {
        for (Transaction transaction : unpairedTransactions) {
            String name = transaction.getSender().getName();
            Integer id = transaction.getSender().getIdentifier();
            UUID transactionId = transaction.getIdentifier();

            System.out.println(name + "(id = " + id + ") has an unacknowledged transfer" +
                    " id = " + transactionId);
        }
    }
}
