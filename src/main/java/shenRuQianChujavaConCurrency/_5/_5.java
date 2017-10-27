package shenRuQianChujavaConCurrency._5;

/**
 * 锁机制存在以下问题：

 （1）在多线程竞争下，加锁、释放锁会导致比较多的上下文切换和调度延时，引起性能问题。

 （2）一个线程持有锁会导致其它所有需要此锁的线程挂起。

 （3）如果一个优先级高的线程等待一个优先级低的线程释放锁会导致优先级倒置，引起性能风险。

 volatile是不错的机制，但是volatile不能保证原子性。因此对于同步最终还是要回到锁机制上来。


 独占锁是一种悲观锁，synchronized就是一种独占锁，会导致其它所有需要锁的线程挂起，等待持有锁的线程释放锁。
 而另一个更加有效的锁就是乐观锁。所谓乐观锁就是，每次不加锁而是假设没有冲突而去完成某项操作，如果因为冲突失败就重试，直到成功为止。


 CAS 操作

 上面的乐观锁用到的机制就是CAS，Compare and Swap。

 CAS有3个操作数，内存值V，旧的预期值A，要修改的新值B。当且仅当预期值A和内存值V相同时，将内存值V修改为B，否则什么都不做。


 public final int incrementAndGet() {
 for (;;) {
 int current = get();
 int next = current + 1;
 if (compareAndSet(current, next))
 return next;
 }
 }


 比如说一个线程one从内存位置V中取出A，这时候另一个线程two也从内存中取出A，并且two进行了一些操作变成了B，
 然后two又将V位置的数据变成A，这时候线程one进行CAS操作发现内存中仍然是A，然后one操作成功。尽管线程one的CAS操作成功，
 但是不代表这个过程就是没有问题的。如果链表的头在变化了两次后恢复了原值，但是不代表链表就没有变化。因此前面提到的原子操作AtomicStampedReference/AtomicMarkableReference就很有用了。这允许一对变化的元素进行原子操作。
 */
public class _5 {
}
