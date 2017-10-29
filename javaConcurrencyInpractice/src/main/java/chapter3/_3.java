package chapter3;

import org.junit.Test;

/**
 * 线程封闭
 *  - ad-hoc 线程封闭
 *  - 栈封闭
 */
public class _3 {
    /**
     * 一种避免同步的方法就是不使用共享数据。如果仅在单线程内共享数据
     * 就不需要同步，这种技术叫做线程封闭
     *
     * 线程封闭的另一种常见应用是JDBC的connection对象，由于大多数请求
     * 是由单个线程来处理的，所以可以用连接池的模式来将connection隐含的封装在线程内
     *
     */


    /**
     * ad-hoc线程封闭是指线程封闭的职责全部有程序实现来承担
     *
     * volatile变量是一种特殊的线程封闭，相当于将 修改操作封装到单个线程
     * 以防止竟态条件
     */


    /**
     * 栈封闭，只能通过局部变量才可以访问对象
     */


    /**
     * ThreadLocal 内部是用一个map存储来当前线程和值来实现的
     * ThreadLocal类是维护线程的一种更规范的方法，这个类可以使线程中某个值和对象关联起来
     * 当线程中止后，这些值将会被垃圾回收处理
     * jdbc的connection就是用ThreadLocal处理的
     * ThreadLocal类似于全局变量，会降低代码的可重用性和耦合性
     */
    @Test
    public void testThreadLocal() throws InterruptedException {
        ThreadLocal t = new ThreadLocal(){
            @Override
            protected Object initialValue() {
                return 4;
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(t.get());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(t.get());
            }
        }).start();
        Thread.sleep(1000);
    }
}


