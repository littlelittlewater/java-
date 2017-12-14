package myzookeeper;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提出议案的角色
 */
public class Processor {

    private List<Value> memory = new ArrayList<>();
    private String name;
    public Processor(int i) {
        name = "P"+i;
    }

    public void setAccepters(List<Accepter> accepters) {
        this.accepters = accepters;
    }

    private List<Accepter> accepters;

    //存储提案中最大的值
    private Map<Value,Integer> getreplied = new HashMap<>();
    private AbortStrategy abortStrategy;


    //提出提案
    public void sendPrepare(Value value){
        synchronized (this){
            int replied = 0 ;
            Value curValue = new Value();
            for(Accepter accepter : accepters){
                //accepter接收请求 预提案 --一阶段
                //   System.out.println("proccessor:预提案 --一阶段 " +value);
                curValue = accepter.getPrepareValue(value);
                //获取accepter的value  --二阶段
                getreplied.putIfAbsent(value,0);
                getreplied.put(value,curValue.getValue() > getreplied.get(value) ? curValue.getValue() :  getreplied.get(value));
                curValue = new Value(value.getId(),getreplied.get(value));
                replied ++ ;
            }
            Thread.yield();
            //正式提案  如果收到了多数acceptor对prepare请求（编号为n）的回应，
            boolean flag =true;
            if(replied >= accepters.size()/2){
                for(Accepter accepter : accepters){
                    //它就向这些acceptor发送议案{n, v}的accept请求，其中v是所有回应中编号最高的议案的决议，
                    if(!accepter.getValue(curValue)){
                        flag =false;
                    }
                }
            }

            if(flag){
                memory.add(curValue);
         //       printRepliedInfo();
            }

        }
    }

    public void printMemoryInfo(){
        System.out.println(name+"提案状态:" + memory);
    }

    public void printRepliedInfo(){
        System.out.println(name+"接收状态:" + getreplied);
    }
}
