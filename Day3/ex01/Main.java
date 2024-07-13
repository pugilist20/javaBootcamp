package ex01;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Printer printer=new Printer();
        Thread thread1=new Thread(new Egg(printer));
        Thread thread2=new Thread(new Hen(printer));
        thread1.start();
        thread2.start();
        while(thread1.isAlive()||thread2.isAlive()){

        }
        for (int i = 0; i < 10; i++) {
            System.out.println("Human");
        }

    }
}
