package chapter3;

/**
 * 发布和逸出
 *  -安全的对象构建
 */
public class _2 {
    /**
     * 发布一个对象的意思是指，使对象在当前作用域的范围内进行使用
     * 当某个不应该被发布的对象被发布时，这种情况就称为逸出
     */

    /**
     * 不要在构造过程中使this引用逸出
     * 一个常见的错误是，在构造函数中启动一个线程，而this会被新创建的线程
     * 所共享，在未完全构造完线程就可以看到他。
     * 这种构造是不正确。
     */
}
