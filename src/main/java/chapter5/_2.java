package chapter5;

/**
 * 并发容器  通过并发容器来代替同步容器，可以极大的提升伸缩性，并降低风险
 * -concureentHashMap
 * -额外的原子map操作
 * -CopyOnwriteArrayList
 */
public class _2 {
    /**
     * ConcurrentHashMap 用于代替散裂的Map
     * CopyOnWriteArrayList 用于遍历操作为主的操作
     * java5新增了两种容器类型 Queue 和 BlockingQueue
     * Queue包括 conCurrentLinkedQueue  priorityQueue 不会阻塞，如果为空，则返回一个空
     * BLockingQueue 扩展与Queue 增加了可阻塞的插入和获取操作
     * Java 6 也引入了 ConcurrentSkipListMap 和 concurrentSkipListSet分别作为同步的sortedMap，sortedSet。
     */


     //TODO examples


    /**
     * concureenthashMap也是一个基于散列的map,使用了分段锁的机制
     * 在这种机制中，任意数量的读取线程可以并发访问Map，并且可以允许一定写入线程来并发的修改MAp
     * 对于size()和empty()方法，他实际上可能只是一个估计值，我们也允许这样做
     * 与hashTable和synchronzedMap相比，conCureentHsahMap有着更强的伸缩性
     */

    /**
     * 由于ConcurrentHashMap 不能被加锁来执行独自访问，
     * 所以接口中有这些方法
     * putIfAbsent() //没有这个值才添加
     * remove()    //当k被映射到v才删除
     * replace()  //当key被映射到某个旧值才替换
     * replace() //当key被映射到某个值，才替换
     */

    /**
     * CopyOnWrite 每次修改时，都会重新创建和发布一个新的容器
     * 用的是ReentrantLock这种锁
     */
}
