package chapter2;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 加一个锁就变成了同步的
 */

public class SafeEncrypt {
   private  final AtomicReference<Integer> lastNumber = new AtomicReference<>();
    private  final AtomicReference<Integer> lastResult = new AtomicReference<>();

    public synchronized  void encrypt(Integer i){
        if(i == lastNumber.get()){
            System.out.println(i+":"+lastNumber.get());
        }else{
            lastNumber.set(i);
            lastResult.set(new Integer(i*10));
            System.out.println(i+":"+i*10);
        }
    }
}
