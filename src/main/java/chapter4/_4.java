package chapter4;

import java.util.Vector;

/**
 * 在现有的线程安全类中添加功能
 * -客户端加锁
 * -组合
 */
public class _4 {
    /**
     * 要添加一个原子操作，最安全的是修改原始的类，但通常无法做到
     * 另一个方法就是拓展这个类，但是这种方法将会更加崔锁：因为现在同步策略会分布到多个单独维护的类中
     * 并且如果底层的类发生改变那么子类也将被破坏
     */
    public class BatterVector<E> extends Vector<E>{
        public synchronized boolean putIfAbset(E e){
            boolean absent = !contains(e);
            if(absent){
                add(e);
            }
            return absent;
        }
    }

    /***
     * 如果以上都不能实现，可以拓展类的功能而不是类本身
     * 添加一个原子操作的拓展类是脆弱的，因为他将加锁代码分布到各个类
     * 而这方式将类c的加锁代码放到了一个与C无关的其他类，会更加脆弱
     */
    //TODO 这儿有一个对加锁的方法，但是是错误，因为只是对当前类加锁
    //TODO 这是一个正确的类，对操作的list进行加锁

    /***
     * 当为现有类添加一个原子操作时，有一个更好的方式：组合
     * 将List委托给底层的list实例。
     * 虽然额外的同步层会带来一层额外的性能损失，但会更为健壮
     *
     */
    //TODO 这儿有一个实现了List借口的类，所有的操作都委托list完成，但有自己的锁
}
