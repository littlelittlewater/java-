void lock();  获取锁。
Condition newCondition();
boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
如果锁在给定的等待时间内空闲，并且当前线程未被中断，则获取锁。
void lockInterruptibly() throws InterruptedException;
如果当前线程未被中断，则获取锁。可以响应中断，也就是可以取消。
如果锁可用，则获取锁，并立即返回
boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
如果锁在给定的等待时间内空闲，并且当前线程未被中断，则获取锁。
