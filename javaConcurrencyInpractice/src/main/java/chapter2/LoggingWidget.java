package chapter2;

/**
 * Created by liyuan on 17-10-20.
 */
public class LoggingWidget  extends  Widget{
    @Override
    public synchronized void doSomething(){
        System.out.println(toString()+":calling dosomething");
        super.doSomething();
    }
}
