package chapter5;

/**
 *  阻塞队列和生产者-消费者模式
 *    -实例 桌面搜索
 *    -串行线程封闭
 *    -双端队列与工作密取
 */
public class _3 {
    /***
     * 阻塞队列提供了可阻塞的put和take方法，以及支持定时的offer和pull方法
     *
     * 在构建高可靠的应用程序中，有界队列是一种强大的资源管理工
     * ：他们能抑制并防止产生过多的工作，是应用程序在负荷过载的情况下 变得健壮
     *
     * LinkedBlockingQueue
     * ArrayBlockingQueue
     * PriorityBlockingQueue
     * SynchronousQueue没有存储空间，因此put和take会一直阻塞
     */

    /***
     * 在I/O密集型 和 cpu密集型 ，他们的吞吐量会提高
     */
     //TODO example

    /**
     * 线程封闭对象只能由单个线程拥有，但可以通过安全的"转移"所有权，借给一个请求线程
     */


    /**
     * java 6 中增加了两种容器类型Deque 和BlockingDeque ,是一个双端队列
     * 双端队列适用于另一种相关模式，即工作密取
     */
     //TODO example
}
