package chapter2;

/**
 * 会出现竟态条件的加法计数器
 */
public class UnsSafeCounter{
    int count = 0 ;
    public void add() {
        count ++ ;
        System.out.println(count);
    }
}
