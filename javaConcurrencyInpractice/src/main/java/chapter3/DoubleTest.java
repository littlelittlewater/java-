package chapter3;

/**
 * Created by liyuan on 17-10-22.
 */
public class DoubleTest  implements  Runnable {
     double a = 3;

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a=Math.random();
        }
    }
}
