package chapter3;

import org.junit.Test;

import java.math.BigInteger;

/**
 * 不变性
 *  -final域
 *  -使用vailtile来发布不可变对象
 */
public class _4 {
    /**
     * 不变对象很简单 ，也更加的安全，如果将一个可变的变量传递一个不可信的代码
     *  那么不可变的代码就有可能去改他们的状态
     *
     *
     *  当且满足下面条件时，对象才是不可变的：
     *  1.对象创建以后其状态就不可以改变
     *  2.对象的所有域是final的
     *  3.对象是正确创建的（没有this逸出）
     *
     *  不用去担心不可变量被修改，因此可以安全的共享不可变对象
     */

    /**
     *下面这个对象就是线程安全的
     * 尽管set是可变的，但是set构造完成后，就无法对其修改
     */
    public void testFinalclass(){
        ThreeSoooges th = new ThreeSoooges();
    }

    /**
     * final类型的域是不可修改的，但是引用的对象可以修改
     *
     * 除非需要某个域是可变的，否者应该将其声明为final域，这也是一个良好的编程习惯
     *
     */


    /**
     * 在某些情况下，不可变对象能够提供一种弱形式的原子性
     *
     */
    @Test
    public void testFinal(){
        VolatileChachedFactor e = new VolatileChachedFactor();
        for(int i = 0 ; i < 20 ; i++){
            Integer j = i / 2 ;
            new Thread(()-> e.service(BigInteger.valueOf(j))).start();

        }
    }
}
