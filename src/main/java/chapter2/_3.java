package chapter2;

import org.junit.Test;

/**
 * 加锁机制
 *  -内置锁
 *  -重入
 */
public class _3 {
    /**
     * 如果存在多个状态变量，使用线程安全类并不全是正确的
     * 原子引用是正确的，但存在竟态条件仍然可能产生错误的结果
     * 要保持状态的一致性，就需要在单个原子操作中更新左右的状态变量
     *
     */
    @Test
    public void testManyStatus(){
        Encrypt e = new Encrypt();
        for(int i = 0 ; i < 20 ; i++){
            Integer j = i / 2 ;
            new Thread(()-> e.encrypt(j)).start();

        }
    }

    /***
     * java 提供了一种内置的锁机制来支持原子性：同步代码快（锁的对象引用，横跨代码的同步代码快）
     * 静态的synchronied方法以class对象为锁
     * 内置锁相当于一种互斥体，意味着只有一个线程能够持有这个锁，因此这个同步代码块会以原子的方式执行
     */
    @Test
    public void TestSynchronized(){
        SafeEncrypt e = new SafeEncrypt();
        for(int i = 0 ; i < 20 ; i++){
            Integer j = i / 2 ;
            new Thread(()-> e.encrypt(j)).start();

        }
    }

    /***
     * 重入意味着获取锁操作的是"线程"而不是"调用"
     * 重入实现的一种方式：为每一个锁关联一个计数器和所有者线程，
     * 内置锁是可重入的，重入提升了加锁行为的封装性
     * 如果内置锁不是可重入的，那么下面这个例子就会产生死锁
     */
    @Test
    public void TestReentraintLock(){
        new LoggingWidget().doSomething();
    }
}
