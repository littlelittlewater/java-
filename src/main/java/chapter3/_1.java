package chapter3;

import org.junit.Test;

/**
 * 可见性
 *  -失效的数据
 *  -非原子的64位操作
 *  -加锁和可见性
 *  -volatile变量
 */
public class _1 {

    /**
     * NOvisibiity 会不断的循环下去，因为因为读线程可能永远不知道
     * ready的值。这种现象被称为重排序
     * 在没有足够的同步情况下，编译器和处理器以及运行时都有可能对
     * 操作进行一些意想不到的调整。
     */
    @Test
    public void test1(){
        Novisibility no = new Novisibility();
        no.run();
        Novisibility.number = 42;
        Novisibility.ready = true;

    }

    /**×
     * 除非每次在访问变量的时候都使用同步，否者很有可能看到该变量的一个
     * 实效值
     */


    /**
     *  在没有同步的情况下读取变量，可能会获得一个实效值
     *  但至少是一个之前某个线程设置的值，而不是一个随机的值
     *  这种被称为最低安全性
     */

    /**
     * jvm对于一个非valite类型的long和double变量，JVM允许将
     * 64位的读取操作分解为两步
     *
     * 对于共享可变的long和double等类型的变量也是不安全的
     * 除非用valitile来保护他们
     *
     * 下面这个程序就会导致错误的数据
     */
    @Test
    public void testValitile() throws InterruptedException {
        DoubleTest doubleTest = new DoubleTest();
        new Thread(doubleTest).start();
        while(true){
            Thread.sleep(1000);
            System.out.println(doubleTest.a);
        }
    }

    /**
     * 内置锁用于可以确保某个线程以一种可以预测的方式来
     * 查看另一个线程的执行结果。
     * 加锁就是为了确保某个线程写入该变量的值对于其他线程来说
     * 是可见的。
     * 加锁的含义不仅仅限于互斥行为，还包括内存可见性
     */

    /**
     * 当把变量声明为volatile之后，编译器与运行时都会注意到这个变量是共享的
     * 因此不会将该变量的操作与其他操作一起重排序
     *
     * volatile是一种更轻量级的同步机制，不会使线程阻塞
     *
     * 加锁机制既可以保证可见性又可以保证原子性，而volatile只能保证可见性
     */
}


