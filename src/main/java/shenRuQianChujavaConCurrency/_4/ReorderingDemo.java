package shenRuQianChujavaConCurrency._4;

/*
volatile包含以下语义：
        （1）Java 存储模型不会对valatile指令的操作进行重排序：
        这个保证对volatile变量的操作时按照指令的出现顺序执行的。
        （2）volatile变量不会被缓存在寄存器中（只有拥有线程可见）
        或者其他对CPU不可见的地方，每次总是从主存中读取volatile变量
        的结果。也就是说对于volatile变量的修改，其它线程总是可见的，
        并且不是使用自己线程栈内部的变量。
        也就是在happens-before法则中，
        对一个valatile变量的写操作后，
        其后的任何读操作理解可见此写操作的结果。*/

/**
 * 1， 程序次序法则，如果A一定在B之前发生，则happen before,

 2， 监视器法则,对一个监视器的解锁一定发生在后续对同一监视器加锁之前

 3， Volatie变量法则：写volatile变量一定发生在后续对它的读之前

 4， 线程启动法则：Thread.start()的调用会happens-before于启动线程里面的动作。

 5， 线程终结法则：线程中的任何动作一定发生在括号中的动作之前（其他线程检测到这个线程已经终止，从Thread.join调用成功返回，Thread.isAlive()返回false）

 6， 中断法则：一个线程A调用另一个另一个线程B的interrupt（）都happens-before于线程A发现B被A中断（B抛出异常或者A检测到B的isInterrupted（）或者interrupted()）。

 7， 终结法则：一个对象的构造函数结束一定发生在对象的finalizer之前

 8， 传递性：A发生在B之前，B发生在C之前，A一定发生在C之前。

 */
public class ReorderingDemo {

    static int x = 0, y = 0, a = 0, b = 0;

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 100; i++) {
            x=y=a=b=0;
            Thread one = new Thread() {
                public void run() {
                    a = 1;
                    x = b;
                }
            };
            Thread two = new Thread() {
                public void run() {
                    b = 1;
                    y = a;
                }
            };
            one.start();
            two.start();
            one.join();
            two.join();
            System.out.println(x + " " + y);
        }
    } 

}
