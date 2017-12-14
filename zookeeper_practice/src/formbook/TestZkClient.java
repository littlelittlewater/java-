package formbook;

import org.I0Itec.zkclient.ZkClient;

public class TestZkClient {
    public static void main(String[] args) {
        ZkClient zk = new ZkClient("127.0.0.1:2181");
        zk.createPersistent("/zk",true);
        zk.subscribeChildChanges("/zk" ,(path,currentChlid)->{
            System.out.println(path+"is changed");
        });
        System.out.println("seesion is established");
        zk.createPersistent("/zk/c1",true);
        zk.createPersistent("/zk/c2",true);
        System.out.println(zk.getChildren("/zk"));
        zk.writeData("/zk/c1","fds");
        System.out.println(zk.readData("/zk/c1").toString());
    }
}
