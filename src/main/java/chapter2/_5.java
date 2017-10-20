package chapter2;

import org.junit.Test;

/**
 * 活跃性和性能
 */
public class _5 {
    /**
     * 通过sychronized方法确实可以保护一个方法，但在高负载下可能会给用户带来糟糕的体验
     * 如果排队请求过多可能就会造成不良的并发
     * 可以通过缩小代码的同步块，来确保程序的安全性
     * 如果持有的锁时间太长都有坑会引起活跃性或性能问题（如IO操作）。
     */
    //FIXME 这儿没有办法进行对比
    @Test
    public void TestSynchronized(){

        QuickSafeEncrypt e = new QuickSafeEncrypt();
        for(int i = 0 ; i < 20 ; i++){
            Integer j = i / 2 ;
            new Thread(()-> e.encrypt(j)).start();
        }

    }


}
