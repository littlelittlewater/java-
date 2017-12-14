package formbook;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestZookeeper {

    /***
     * Zookeeper并不是调用方法就会返回一个客户端
     * 而是会进入一个connecting的一个状态
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testCreateZookeeper() throws IOException, InterruptedException {
        CountDownLatch connectedSemphore = new CountDownLatch(1);

        ZooKeeper zookeeper = new ZooKeeper("127.0.0.1:2181",5000,(event)->{
            assertEquals(ZooKeeper.States.CONNECTING,111);
            if(Watcher.Event.KeeperState.SyncConnected == event.getState()){
                connectedSemphore.countDown();
            }
        });
        connectedSemphore.await();
        assertEquals(zookeeper.getState(),ZooKeeper.States.CONNECTED);

    }


    //同步创建zookeeper节点
    @Test
    public void testCreatNode1() throws Exception{
        CountDownLatch connectedSemphore = new CountDownLatch(1);
        ZooKeeper zookeeper = new ZooKeeper("127.0.0.1:2181",5000,(event)->{
            if(Watcher.Event.KeeperState.SyncConnected == event.getState()){
                connectedSemphore.countDown();
            }
        });
        connectedSemphore.await();

        String path1 = zookeeper.create("/zk-test-ephemeral","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        assertEquals(path1,"/zk-test-ephemeral");

        //临时节点 后面回加序列号数字
        String tempPtah = zookeeper.create("/zk-test-ephemeral","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        assertNotEquals(tempPtah,"/zk-test-ephemeral");
    }

    //异步创建zookeeper节点
    @Test
    public void testCreatNode2() throws Exception{
        CountDownLatch connectedSemphore = new CountDownLatch(1);
        ZooKeeper zookeeper = new ZooKeeper("127.0.0.1:2181",5000,(event)->{
            if(Watcher.Event.KeeperState.SyncConnected == event.getState()){
                connectedSemphore.countDown();
            }
        });
        connectedSemphore.await();

        //创建节点成功
        zookeeper.create("/zk-test","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL,(rc,path,ctx,name)->{
            assertEquals(path,"/zk-test");
            assertEquals(name,"/zk-test");
        },"i am context");

        //创建节点失败 因为已经有节点存在了
        zookeeper.create("/zk-test","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL,(rc,path,ctx,name)->{
            assertEquals(path,"/zk-test");
            assertNotEquals(name,"/zk-test");
        },"i am context");
    }


    @Test
    public void getChildren() throws IOException, InterruptedException, KeeperException {
        CountDownLatch connectedSemphore = new CountDownLatch(1);
        ZooKeeper zookeeper = new ZooKeeper("127.0.0.1:2181",5000,(event)->{
            if(Watcher.Event.KeeperState.SyncConnected == event.getState()){
                connectedSemphore.countDown();
            }else
            System.out.println("子节点变更");
        });
        connectedSemphore.await();

        //创建节点成功
        zookeeper.create("/zk-test","2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT,(rc,path,ctx,name)->{
            System.out.println("创建节点成功");
        },"i am context");


        //观察子节点变化
        zookeeper.getChildren("/zk-test",true,(rc,path,ctx,name)->{
            System.out.println("/zk-test"+"节点发生变化");
        },null);

        //创建一个子节点
        zookeeper.create("/zk-test/ll","222".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT,(rc,path,ctx,name)->{
            System.out.println("/zk-test/ll"+"创建子节点成功");
        },"i am context");

        //创建另一个节点
        zookeeper.create("/zk-test/l2","222".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT,(rc,path,ctx,name)->{
            System.out.println("/zk-test/l2"+"创建子节点成功");
        },"i am context");


        zookeeper.setData("/zk-test/l2","lalala".getBytes(),-1);
        System.out.println(new String(zookeeper.getData("/zk-test/11" ,true,new Stat())));

    }
}
