package chapter4;

import org.junit.Test;

/**
 * 设计线程安全的类
 */
public class _1 {

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



}
