package chapter2;

/**
 * Created by liyuan on 17-10-20.
 */
public abstract class Widget {
    public synchronized  void  doSomething(){
        System.out.println(toString()+"widget.doSometiong");
    }
}
