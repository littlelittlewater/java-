package chapter2;

import org.junit.Test;

public class _1 {

    /**
     * 当多个线程访问某个类时，这个始终都能变现出正确的行为，那么就称这个类是线程安全的
     */
    /**
     * 没有状态的的对象一定是线程安全的
     */
    @Test
    public void testNoStatus(){
        for(int i =0 ; i < 10 ; i ++){
            final  int j = i;
            new Thread(()-> NoStatusObject.doSometing(j)).start();
        }
    }

}
