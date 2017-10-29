package chapter4;

/**
 * 线程安全性的委托
 *  -实例：基于委托的车辆追踪器
 *  -独立的状态变量
 *  -当委托实效时
 *  -发布底层的状态变量
 */
public class _3 {
    /**
     * 使用一个final的point的。由于point是不可变的，所以是线程安全的
     * 我们将所有对状态的访问都ConcureentHashMap来管理，并且Map的所有键值都是不可变的
     * 见类delegatingVehicleTracker Point
     *
     * 如果各个状态是独立，则可以分开委托
     *
     * 如果某个类含有复合操作，那么仅靠委托是不足以实现线程安全性。
     * 这种情况下，必须提供自己的加锁加锁机制
     *
     * 而PublishingVehicleTracker,SafrPoint使用了另外一种方式
     * 和上面的委托不同的是Map中存的是线程可变的point
     *
     *
     */

    //TODO PublishingVehicleTracker,SafrPoint类的完善。
}
