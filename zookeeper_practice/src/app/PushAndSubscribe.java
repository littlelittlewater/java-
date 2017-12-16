package app;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 发布订阅
 */
public class PushAndSubscribe {
    public static  String path = "/ps";

    //启动一个客户端
    public static void subsribe() {
        new Thread(()->{
            CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
                .retryPolicy(new ExponentialBackoffRetry(1000,5)).build();
            client.start();
            NodeCache nodeCache = new NodeCache(client,path,false);
            try {
                nodeCache.start(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            nodeCache.getListenable().addListener(()->{
            System.out.println(path+"is changed"+new String(nodeCache.getCurrentData().getData()));
        });
        }).start();

    }

    //
    public static  void  push(CuratorFramework client ,String msg){
        try {
            client.setData().forPath(path,msg.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
                .retryPolicy(new ExponentialBackoffRetry(1000,3)).build();
        client.start();
        for(int i = 0 ; i < 5 ; i ++)
            subsribe();
        for(int i = 0 ; i < 100 ; i++){
            push(client,""+ i);
        }

    }
}
