package chapter2;

/**
 * Created by liyuan on 17-10-20.
 */
public class LazyIni {
    private static LazyIni ini;
    public static  LazyIni getInstance(){
        if(ini == null){
            Thread.yield();
            ini = new LazyIni();
        }
        return ini;
    }
    private LazyIni(){

    }
}
