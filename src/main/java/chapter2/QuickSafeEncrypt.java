package chapter2;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 锁的粒度更细可以减少时间
 */

public class QuickSafeEncrypt {
   private  final AtomicReference<Integer> lastNumber = new AtomicReference<>();
    private  final AtomicReference<Integer> lastResult = new AtomicReference<>();

    public  void encrypt(Integer i){
        synchronized (this){
        if(i == lastNumber.get()){
            System.out.println(i+":"+lastNumber.get());
        }else{
            lastNumber.set(i);
            lastResult.set(new Integer(i*10));
            System.out.println(i+":"+i*10);
        }}
    }
}
