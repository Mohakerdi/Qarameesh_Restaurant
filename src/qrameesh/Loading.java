package qrameesh;

public class Loading implements Runnable{

    int time;
    public Loading(int time) {
        this.time = time;
    }
    
    @Override
    public void run() {
        System.out.println("started");
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {System.out.println("interrupted");}
        System.out.println("ended");
    }
    
}
