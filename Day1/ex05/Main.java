package ex05;

import ex05.Transaction.IllegalTransactionException;
import ex05.Transaction.Transaction;
import ex05.Transaction.TransactionsService;
import ex05.User.User;
import ex05.User.UserNotFoundException;

public class Main {
    public static void main(String[] args) throws UserNotFoundException, IllegalTransactionException {
        Menu menu=new Menu(true);
        menu.menu();
    }
}
