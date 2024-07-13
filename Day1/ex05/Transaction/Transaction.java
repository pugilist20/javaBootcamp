package ex05.Transaction;

import ex05.User.User;
import ex05.User.UserNotFoundException;

import java.util.UUID;

public class Transaction {
    enum Category {
        debits,
        credits
    }
    private UUID identifier;
    private User recipient;
    private User sender;
    private Category transferCategory;
    private Integer transferAmount;
private Transaction next;
    private Transaction prev;
    public Transaction(User recipient, User sender, String category, Integer transferAmount) {
        identifier = UUID.randomUUID();
            if (category.equals("debits")) {
                if (sender.getBalance() >= transferAmount && transferAmount>=0) {
                    this.sender = sender;
                    this.recipient = recipient;
                    this.transferCategory = Category.debits;
                    this.transferAmount = transferAmount;
                } else {
                    System.exit(-1);
                }
            } else if (category.equals("credits")) {
                if (sender.getBalance() >= transferAmount && transferAmount<=1) {
                    this.sender = sender;
                    this.recipient = recipient;
                    this.transferCategory = Category.credits;
                    this.transferAmount = transferAmount;
                } else {
                    System.exit(-1);
                }
            }
            transfer();
    }

    private void transfer() {
        this.sender.setBalance(this.sender.getBalance() - transferAmount);
        this.recipient.setBalance(this.recipient.getBalance() + transferAmount);
    }

    public void printTransaction() {
        String name = sender.getName();
        Integer id = sender.getIdentifier();

        if (transferAmount < 0) {
            System.out.print("Transfer To ");
        } else {
            System.out.print("Transfer From ");
            name = recipient.getName();
            id = recipient.getIdentifier();
        }
        System.out.print(name + "(id = " + id
                + ")" + " " + transferAmount);
    }
    public Transaction getNext() {
        return next;
    }

    public Transaction getPrev() {
        return prev;
    }

    public void setNext(Transaction next) {
        this.next = next;
    }

    public void setPrev(Transaction prev) {
        this.prev = prev;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public Integer getTransferAmount() {
        return transferAmount;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }
}
