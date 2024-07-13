package ex03;

import java.util.ArrayList;

import static ex03.Downloader.counter;
import static ex03.Reader.read;

public class Main {
    static ArrayList<String> urls=new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[5];
        read(urls);
        for (int i = 0; i < threads.length; i++) {
            threads[i]=new Downloader();
            threads[i].setName("Thread-"+(i+1));
            threads[i].start();
        }
    }
}
