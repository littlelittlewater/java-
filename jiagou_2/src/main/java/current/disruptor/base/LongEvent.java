package current.disruptor.base;

//http://ifeve.com/disruptor-getting-started/

/**
 * 我们需要传递的对象
 */
public class LongEvent { 
    private long value;
    public long getValue() { 
        return value; 
    } 
 
    public void setValue(long value) { 
        this.value = value; 
    } 
} 