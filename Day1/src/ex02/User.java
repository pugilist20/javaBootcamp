package ex02;

public class User {
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
}
