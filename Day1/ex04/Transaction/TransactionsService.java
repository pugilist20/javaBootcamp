package ex04.Transaction;

import ex04.User.User;
import ex04.User.UserNotFoundException;
import ex04.User.UsersArrayList;

import java.util.UUID;

public class TransactionsService {
    private UsersArrayList usersList=new UsersArrayList();
    public void addUser(User user){
        usersList.addUser(user);
    }
    public Integer getUserBalance(User user){
        return user.getBalance();
    }
    public void makeTransaction(Integer id1, Integer id2, Integer transferAmount) throws UserNotFoundException, IllegalTransactionException {
        UUID transactionID=UUID.randomUUID();
        User sender=usersList.getUserByID(id1);
        User recipient=usersList.getUserByID(id2);
        if(sender.equals(recipient)||transferAmount> sender.getBalance()||transferAmount<0){
            throw new IllegalTransactionException();
        }
        Transaction debits=new Transaction(sender, recipient, "debits", transferAmount);
        Transaction credits= new Transaction(sender, recipient, "credits", -transferAmount);
        debits.setIdentifier(transactionID);
        credits.setIdentifier(transactionID);
        sender.addTransaction(debits);
        recipient.addTransaction(credits);
        sender.setBalance(sender.getBalance() - transferAmount);
        recipient.setBalance(recipient.getBalance() + transferAmount);
    }
    public Transaction[] getTransactions(User user){
        return user.getTransactionsList().toArray();
    }
    public Transaction[] removeTransaction(UUID ID, Integer userID) throws UserNotFoundException, TransactionNotFoundException {
        usersList.getUserByID(userID).deleteTransaction(ID);
        return usersList.getUserByID(userID).getTransactionsList().toArray();
    }

    public Transaction[] getUnpairedTransactions() throws UserNotFoundException {
        if(usersList.getAmountOfUsers()==0){
            System.out.println("The list of users is empty");
        }
        Transaction[] allTransactionsLinkedList=getAllTransactions();
        TransactionsLinkedList unpairedTransactions=new TransactionsLinkedList();
        for (int i = 0; i < allTransactionsLinkedList.length; i++) {
            boolean isPaired=false;
            for (int j = i+1; j < allTransactionsLinkedList.length;j++) {
                if(allTransactionsLinkedList[i]==allTransactionsLinkedList[j]){
                    isPaired=true;
                    break;
                }
            }
            if(!isPaired){
                unpairedTransactions.addTransaction(allTransactionsLinkedList[i]);
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
}
