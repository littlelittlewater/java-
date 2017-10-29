package chapter2;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 加密方式为乘以10
 * 多个线程的必须保持不变性原则，其中之一就是，lastResult中的值应该等于lastNumber加密后的值
 * 尽管set的方法某次调用都是原子的，但是无法同时更新这两个变量，其他线程的不变性条件就发生了改变 ，不变性就发生了改变
 * 同样。另外的，我们也无法保证同时可以获取两个值，线程不变性也发生了改变
 */
public class Encrypt {
    private  final AtomicReference<Integer> lastNumber = new AtomicReference<>();
    private  final AtomicReference<Integer> lastResult = new AtomicReference<>();

    public void encrypt(Integer i){
        if(i == lastNumber.get()){
            System.out.println(i+":"+lastNumber.get());
        }else{
            lastNumber.set(i);
            lastResult.set(new Integer(i*10));
            System.out.println(i+":"+i*10);
        }
    }
}
