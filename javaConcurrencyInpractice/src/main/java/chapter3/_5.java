package chapter3;

import org.junit.Test;

/**
 * 安全发布
 *  -不正确的发布：正确的对象被破坏
 *  -不可变对象与初始化安全
 *  -安全发布的常用模式
 *  -事实不可变对象
 *  -可变对象
 */
public class _5 {


    /**
     * 由于没有使用同步来确保hodler对其他线程可见（即一个线程修改了holder，其他线程无法感知到这个变化）
     * 所以其他线程可能会看到hodler的一个实效值
     * 如果没有正确的同步，就会发生很多奇怪的
     *
     * @throws InterruptedException
     */
    @Test
    public void testSafePUblish() throws InterruptedException {
        UnsafeHodler unsafeHodler = new UnsafeHodler();
        new Thread(()->{while (true)unsafeHodler.initilize();}).start();
        new Thread(()->{while (true)unsafeHodler.assertSanity();}).start();
        Thread.sleep(100000);
    }

    /**
     * 要安全的发布一个对象，可以由以下方法来安全的发布
     * 1.在静态初始化函数中初始化一个对象引用
     * 2.将对象的引用保存到一个voliatile的域中或者AtomaticReferce对象中。
     * 3.将对象的引用保存到某个正确构造对象的final中
     * 4.将对象的引用保存到一个由锁保护的域中
     */

    /**
     * 如果对象从技术上来看是一个可变的，但其状态发布之后并不会改变
     * 那么这种被称为"事实不可变对象"
     * 这种对象也可以安全使用
     */

    /**
     * 如果对象在构造后可以修改，那么安全发布只可以保证"发布当时"的可见性
     * 如果以后需要同步，必须要用其他同步策略来保证
     */

    /**
     *
     */
}
