package chapter3;

/**
 * Created by liyuan on 17-10-22.
 */
public class Novisibility implements Runnable{
    static volatile boolean ready;
    static int number;

    public void run() {
            while (!ready){
                Thread.yield();
            }
            System.out.println(number);
    }
}
