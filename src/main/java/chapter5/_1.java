package chapter5;

import org.junit.Test;

import java.util.*;

/**
 * 同步容器类
 *  -同步容器类的问题
 *  -迭代器与ConcurrentModificationExceptin
 *  -隐藏的迭代器
 */
public class _1 {

    /**
     * 同步类容器是线程安全的，但在某些情况下需要额外的客户端来保护复合操作
     * 多个线程并发修改Vector时，另一个线程删除了一个元素，可能这种迭代就会抛出一个异常
     */

    @Test
    public void testVector() throws InterruptedException {
        Vector<Double> vector = new Vector<>();

        new Thread(()->{
            while(true){
                vector.add(Math.random());
            }
        }).start();

        new Thread(()->{
            while (true){
                for(Double d : vector){
                    System.out.println(d);
                }
            }
        }).start();

        Thread.sleep(10000);
    }

    /**
     * 对容器标准的访问法方法就是使用一个迭代器
     * 设计同步容器类的迭代器的时候没有考虑到并发修改的问题，并且他们表现的行为是及时失败的
     * 如果在迭代过程中修改，就会抛出concurrentModificationException
     *
     * 如果不希望对容器加锁，一种替代的方法就是克隆容器，并在副本上进行迭代
     */

    //会抛出异常
    @Test
    public void testList() throws InterruptedException {
        List<Double> list = new ArrayList<>();

        new Thread(()->{
            while(true){
                list.add(Math.random());
            }
        }).start();

        new Thread(()->{
            while (true){
                for(Double d : list){
                    System.out.println(d);
                }
            }
        }).start();

        Thread.sleep(10000);
    }

    //不会抛出异常,因为遍历的是方法的副本
    @Test
    public void testCloneList() throws InterruptedException {
        List<Double> list = new ArrayList<>();

        new Thread(()->{
            while(true){
                list.add(Math.random());
            }
        }).start();

        new Thread(()->{
            while (true){
                List<Double> doubles  = new ArrayList<>();
                doubles.addAll(list);
                for(Double d : doubles){
                    System.out.println(d);
                }
            }
        }).start();

        Thread.sleep(10000);
    }


    /***
     * 虽然加锁可以防止迭代器抛出异常，但是要在所有使用容器的地方都加锁。
     * 实际情况会比较复杂，例如hashcode（）和toString() equals等方法会间接的执行迭代操作
     */
    //调用toString也会抛出异常
    @Test
    public void testToString() throws InterruptedException {
        List<Double> list = new ArrayList<>();

        new Thread(()->{
            while(true){
                list.add(Math.random());
            }
        }).start();

        new Thread(()->{
            while (true){
                System.out.println(list.toString());
            }
        }).start();

        Thread.sleep(10000);
    }

}

