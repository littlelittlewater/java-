package myzookeeper;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 *
 * 场景类 模拟分布式环境产生
 */
public class Client {
    public static int NUM_ACCEPTER = 1;
    public static int NUM_PROCCESSOR = 10;
    public static  int NUM_THREAD = 10;
    public static void main(String[] args) throws Exception {
         CountDownLatch countDownLatch = new CountDownLatch(NUM_THREAD -1 );
        CountDownLatch isfinish = new CountDownLatch(NUM_THREAD);
        List<Accepter> accepters = new ArrayList<>();
        List<Processor> processors = new ArrayList<>();
        for(int i = 0 ; i < NUM_ACCEPTER ; i ++)
            accepters.add(new Accepter());

        for(int i = 0 ; i < NUM_PROCCESSOR ; i ++){
            Processor processor = new Processor(i);
            processor.setAccepters(accepters);
            processors.add(processor);
        }


        //开启多个线程发送消息 模拟分布式场景
        for(int i = 0 ; i < NUM_THREAD ; i ++){

            int thred_i = i;
            new Thread(()->{
                try {
                    countDownLatch.countDown();
                    countDownLatch.await();
                    //让每个proocessor发送id相同value不同的协议
                    for(int j = 0 ; j < NUM_PROCCESSOR  ; j ++) {
                        processors.get(j).sendPrepare(getRandomValue(thred_i));
                    }
                    isfinish.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }).start();
        }
        isfinish.await();
        for(int j = 0 ; j < NUM_PROCCESSOR  ; j ++)
            processors.get(j).printMemoryInfo();
    }



    public static Value getRandomValue(int id){
        Value v = new Value(id,new Random().nextInt(100));
        return v;
    }
}
