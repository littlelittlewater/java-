package chapter3;

import java.util.Random;

/**
 * Created by liyuan on 17-10-23.
 */
public class UnsafeHodler {

    public Hodler hodler;

    public void initilize(){
        hodler = new Hodler(new Random().nextInt(100));
    }

    public void assertSanity(){
        hodler.asserSanity();
    }
}
