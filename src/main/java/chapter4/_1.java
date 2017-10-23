package chapter4;

import org.junit.Test;

/**
 * 设计线程安全的类
 *  -收集同步需求
 *  -依赖状态的操作
 *  -状态的所有权
 */
public class _1 {

    /***
     * 在设计线程安全类的过程中，需要包含下面三个要素：
     * 找出构成对象状态的所有变量
     * 找出约束状态变量的不变性条件
     * 建立对象的并发管理策略
     */
    @Test
    public void testCouter(){
        Counter c = new Counter();
        for(int i = 0 ; i < 10 ; i++){
            new Thread(()->{
                for(int j = 0 ; j <10 ; j++){
                    System.out.println(c.increment());
                }
            }).start();
        }
    }


    /**
     * 如果某些状态是无效的，必须对底层的状态变量进行封装，否者客户代码可能会使对象处于无效中
     *
     *
     * 类的不变性条件后验条件 约束 状态和状态装换  有效
     *
     * 注意对象的所有权
     *
     */



}
