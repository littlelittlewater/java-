AbstractQueuedSynchronizer是CountDownLatch/FutureTask/ReentrantLock/RenntrantReadWriteLock/Semaphore的基础，
因此AbstractQueuedSynchronizer是Lock/Executor实现的前提。
公平锁、不公平锁、Condition、CountDownLatch、Semaphore等放到后面的篇幅中说明。

基本思想
获取锁：首先判断当前状态是否允许获取锁，如果是就获取锁，否则就阻塞操作或者获取失败，
也就是说如果是独占锁就可能阻塞，如果是共享锁就可能失败。
另外如果是阻塞线程，那么线程就需要进入阻塞队列。
当状态位允许获取锁时就修改状态，并且如果进了队列就从队列中移除。
释放锁:这个过程就是修改状态位，如果有线程因为状态位阻塞的话就唤醒队列中的一个或者更多线程。

要支持上面两个操作就必须有下面的条件：
  原子性操作同步器的状态位
  阻塞和唤醒线程
  一个有序的队列

状态位的原子操作
  里使用一个32位的整数来描述状态位，前面章节的原子操作的理论知识整好派上用场，在这里依然使用CAS操作来解决这个问题。事实上这里还有一个64位版本的同步器（AbstractQueuedLongSynchronizer），这里暂且不谈。
  
阻塞和唤醒线程
  在JDK 5.0以后利用JNI在LockSupport类中实现了此特性。

有序队列
    在AQS中采用CHL列表来解决有序的队列的问题。
    private volatile int state; //描述多少个线程可以取得锁
    private transient volatile Node head;
    private transient volatile Node tail;
    
Node结构：
     volatile int waitStatus; 节点的等待状态，一个节点可能位于以下几种状态：
         CANCELLED = 1： 节点操作因为超时或者对应的线程被interrupt。节点不应该留在此状态，一旦达到此状态将从CHL队列中踢出。
          SIGNAL = -1： 节点的继任节点是（或者将要成为）BLOCKED状态（例如通过LockSupport.park()操作），因此一个节点一旦被释放（解锁）或者取消就需要唤醒（LockSupport.unpack()）它的继任节点。
          CONDITION = -2：表明节点对应的线程因为不满足一个条件（Condition）而被阻塞。
           0： 正常状态，新生的非CONDITION节点都是此状态。
          非负值标识节点不需要被通知（唤醒）。
    volatile Node prev;此节点的前一个节点。节点的waitStatus依赖于前一个节点的状态。
    volatile Node next;此节点的后一个节点。后一个节点是否被唤醒（uppark()）依赖于当前节点是否被释放。
    volatile Thread thread;节点绑定的线程。
    Node nextWaiter;下一个等待条件（Condition）的节点，由于Condition是独占模式，因此这里有一个简单的队列来描述Condition上的线程节点。

------------------------------------------
reentrantLock代码分析：
//TODO http://www.blogjava.net/xylz/archive/2010/07/06/325390.html
----------------------
LockSupport 是什么,其底层原理是什么？
//TODO 

  