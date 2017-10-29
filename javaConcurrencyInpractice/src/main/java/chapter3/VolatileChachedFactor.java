package chapter3;

import java.math.BigInteger;

/**
 * Created by liyuan on 17-10-23.
 */
public class VolatileChachedFactor {

    private volatile OneValueCache cache = new OneValueCache(null,null);


    public void service(BigInteger number){
        BigInteger result = cache.getLastEncrypt(number);
        if(result == null){
            result = number.add(number);
            cache = new OneValueCache(number,result);
        }
        System.out.println(number+":"+result);
    }
}
