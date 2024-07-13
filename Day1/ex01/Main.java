package ex01;

public class Main {
    public static void main(String[] args) {
        User john = new User("John", 1000);
        User mike = new User("Mike", 1000);

        Transaction outgoing = new Transaction(john, mike, "credits", -500);
        Transaction incoming = new Transaction(mike, john,"debits", 500);

        outgoing.printTransfer();
        incoming.printTransfer();
    }
}
