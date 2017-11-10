package current.disruptor.fromnet;

import java.util.concurrent.CountDownLatch;

/**
 * 对比三种方式的运行快慢：
 *          1.无锁
 *          2.有锁不竞争
 *          3.有锁竞争
 * 通过对比发现，无锁最快
 * 并发很难而锁的性能糟糕。
 *
 * 上面的场景3表明，如果没有注意到多线程访问和写入相同的数据，事情可能会很糟糕。减慢系统的速度
 * 场景3中，使用锁保护代码可能导致诸如死锁或者效率问题。
 */

public class testLock {
    Object syn1 = new Object();
    Object syn2 = new Object();
    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(4);
        testLock testLock = new testLock();
        int biginteger = 100000000;
        new Thread(()->{
            try {
                countDownLatch.countDown();
                System.out.println("method准备执行");
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i = 0 ; i < biginteger ; i ++){
                testLock.method1();
            }
            System.out.println("method1 执行完毕" + testLock.i1);
        }).start();

        new Thread(()->{
            try {
                System.out.println("method2准备执行");
                countDownLatch.countDown();
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i = 0 ; i < biginteger ; i ++){
                testLock.method2();
            }
            System.out.println("method2 执行完毕" + testLock.i2);
        }).start();

        new Thread(()->{
            try {
                System.out.println("method3准备执行");
                countDownLatch.countDown();
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i = 0 ; i < biginteger /2 ; i ++){
                testLock.method3();
            }
            System.out.println("method3 执行完毕" + testLock.i3);
        }).start();

        new Thread(()->{
            try {
                System.out.println("method3准备执行");
                countDownLatch.countDown();
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i = 0 ; i < biginteger /2 ; i ++){
                testLock.method3();
            }
            System.out.println("method3 执行完毕" + testLock.i3);
        }).start();
    }

    int i1 = 0;
    public void method1(){
         i1++ ;
    }
    int i2 = 0;
    public void method2(){
        synchronized (syn1){
            i2++;
        }
    }
    int i3 = 0;
    public void method3(){
        synchronized (syn2){
            i3++;
        }
    }
}
