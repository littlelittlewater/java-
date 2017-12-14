package myzookeeper;


/**
 * 提案
 */
public class Value {
    //同一提案的id相同
    private  int id ;
    //同一提案 不同提出者 vlaue不同
    private int value ;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Value() {
    }

    public Value(int id) {

        this.id = id;
    }

    public Value(int id, int value) {

        this.id = id;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
