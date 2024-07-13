package ex01;

public class Hen implements Runnable {
    Printer printer;

    public Hen(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            printer.print();
        }
    }
}
