package ex00;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1=new Thread(new Printer("egg"));
        Thread thread2=new Thread(new Printer("chicken"));
        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()) {
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("Human");
        }

    }
}
