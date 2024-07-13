package ex01;

public class Printer{
    private boolean print=true;
    public synchronized void print(){
        if(print){
            System.out.println("egg");
            print=false;
        }
        else{
            System.out.println("hen");
            print=true;
        }
    }
}
