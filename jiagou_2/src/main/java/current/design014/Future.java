package current.design014;

import java.util.concurrent.Callable;

/**
 * 模拟的一个future
 */
public class Future <T>{
    Object lock = new Object();

    private T t ;
    public Future(Callable<T> callable) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t = callable.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (lock){
                    lock.notifyAll();
                }
            }
        }).start();
    }

    public T get() throws InterruptedException {
        synchronized (lock){
            if(t == null)
                lock.wait();
        }
        return  t;
    }
}
