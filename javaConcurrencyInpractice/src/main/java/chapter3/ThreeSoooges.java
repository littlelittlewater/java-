package chapter3;

import java.util.HashSet;
import java.util.Set;

/**
 * 这是一个不可变的类，所以是安全的
 */
public class ThreeSoooges {
    private final Set<String> stooges = new HashSet<String>();

    public ThreeSoooges() {
        stooges.add("moe");
        stooges.add("laryy");
        stooges.add("curly");
    }

    public boolean isStooge(String name){
        return stooges.contains(name);
    }
}
