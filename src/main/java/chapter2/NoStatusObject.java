package chapter2;

/**
 * 没有状态的对象是相册安全的
 */
public class NoStatusObject {
    public static void doSometing(int str){
        for(int i = 0 ; i < 10 ; i++)
             System.out.println(str * i);
    }

}
