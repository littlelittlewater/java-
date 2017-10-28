package shenRuQianChujavaConCurrency._5;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 非阻塞的计数器
 */
public class NoBlockCounter {
    private AtomicInteger integer = new AtomicInteger(0);

    public void icrement(int n) {
        int value;
        do {
            value = integer.get();
            System.out.println(value);
        }
        while (!integer.compareAndSet(value, value + n));

    }

    public static void main(String[] args) throws InterruptedException {
        NoBlockCounter noBlockCounter = new NoBlockCounter();
        for(int j = 0 ; j < 10 ; j++){
            Thread thread = new Thread1(noBlockCounter);
            thread.start();
        }
      Thread.sleep(100000);

    }


    static class Thread1 extends Thread {
        NoBlockCounter counter;

        public Thread1(NoBlockCounter counter) {
            this.counter = counter;

        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++)
                counter.icrement(1);
        }

    }
}
