package ex00;

public class Printer implements Runnable{
    private String name;
    public Printer(String name){
        this.name=name;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(name);
        }
    }
}
