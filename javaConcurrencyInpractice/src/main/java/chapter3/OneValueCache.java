package chapter3;

import java.math.BigInteger;

/**
 * 这个类是个不可变的类
 * 所有的域是final类型，并且初始化完成后状态不会发生改变
 */
public class OneValueCache {
    public final BigInteger lastNumber;
    public final BigInteger encrypt;

    public OneValueCache(BigInteger lastNumber ,BigInteger encrypt){
        this.lastNumber = lastNumber;
        this.encrypt = encrypt;
    }

    public BigInteger getLastEncrypt(BigInteger number){
      if(lastNumber == null || !lastNumber.equals(number)){
          return null;
      }
      return encrypt;
    }
}
