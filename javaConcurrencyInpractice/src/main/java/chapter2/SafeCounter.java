package chapter2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liyuan on 17-10-20.
 */
public class SafeCounter {
    AtomicInteger count = new AtomicInteger(0) ;
    public void add() {
        count.getAndAdd(1);
        System.out.println(count);
    }
}
