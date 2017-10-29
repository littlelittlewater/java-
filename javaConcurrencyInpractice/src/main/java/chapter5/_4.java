package chapter5;

/**
 * 阻塞方法与中断方法
 */
public class _4 {
    /**
     * 被阻塞的线程必须等待某个不受他控制的事件发生后才能继续执行
     * Thread 提供了interrupt方法 用于中断线程或者查询线程是否被中断
     * 每个线程中都有一个布尔类型的属性，表示线程的中断状态
     *
     * 中断是一种协作机制，一个线程不能强迫其他线程中断，被中断的线程只会在他愿意停下来的地方停下来
     *
     * 当传递一个中断异常的时候，你的方法也就变成了一个阻塞的方法，必须要丢中断进行处理
     *  -传递 传递给调用者
     *  -恢复中断 调用interrcept恢复中断
     *
     */
}