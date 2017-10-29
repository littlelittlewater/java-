package chapter2;

        import org.junit.Test;

/**
 * 原子性
 *  -竟态条件
 *  -实例：延迟初始化的竟态条件
 *  -复合操作
 */
public class _2 {
    @Test
    /**
     * 当某个计算的正确性取决于多个线程的交替执行时序是，那么就会发生静态条件
     * 下面这个就出现了竟态条件 有些结果会产生偏差
     */
    public void testCounter(){
        UnsSafeCounter unsSafeCounter = new UnsSafeCounter();
        for(int i = 0 ; i < 20 ; i++)
            new Thread(()-> unsSafeCounter.add()).start();
    }

    @Test
    /**
     * 竟态条件并不总会产生错误，还需要某种不恰当的执行时序
     * 这个程序可能会在某个特殊的情况返回两个不同的实例
     */
    public void testLazyIni(){
        for(int i = 0 ; i < 2 ; i++)
        new Thread(()-> System.out.println(LazyIni.getInstance().hashCode())).start();
    }

    @Test
    /**
     * 要避免竟态条件问题，就必须在没某个线程修改该变量是，通过某种方式防止其他线程使用这个变量
     * 从而确保其他线程只能在修改操作完成之前或之后读取和修改状态，而不是在修改状态的过程中
     * 尽可能使用线程安全类来处里问题
     */
    public void testAutomatic() throws InterruptedException {
        SafeCounter safeCounter = new SafeCounter();
        for(int i = 0 ; i < 20 ; i++)
            new Thread(()-> safeCounter.add()).start();
        System.out.println("----");
    }
}
