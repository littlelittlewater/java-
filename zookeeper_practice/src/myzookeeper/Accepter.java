package myzookeeper;


import javafx.scene.shape.VLineTo;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 接收议案的决议
 */
public class Accepter  {
    private List<Value> memory ;

    //用来保存最大的value
    private HashMap<Integer,Integer> max  = new HashMap();


    public void getMemoryInfo(){
        System.out.println(memory);
    }


    /**
     *
     * 接收预提案
     * 传入一个value。通过比较max中的值来存取并返回value最大的值
     * @param value
     * @return
     */
    public Value getPrepareValue(Value value) {
        synchronized (max){
            max.putIfAbsent(value.getId(),value.getValue());

            //传来的值较大 所以保留，并且返回
            if(max.get(value.getId()) <= value.getValue()){
                max.put(value.getId(),value.getValue());
            }

            //返回最大值
            return new Value(value.getId(),max.get(value.getId()));
        }

    }

    /**
     * 批准提案
     * @param value
     * @return
     */
    public boolean getValue(Value value) {
        synchronized (max){
            if(max.get(value.getId()) <= value.getValue()){
                return true;
            }else{
                return false;
            }
        }

    }
}
