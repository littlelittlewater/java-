package formbook;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

import java.security.cert.CertificateFactory;

public class TestCurator {
    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
                .retryPolicy(new ExponentialBackoffRetry(1000,3)).build();
        client.start();
        client.create().creatingParentsIfNeeded().forPath("/c2/c2/c2/c2","somecontent".getBytes());
        client.create().creatingParentsIfNeeded().forPath("/c2/c2/c2/c3","somecontent".getBytes());
        client.delete().forPath("/c2/c2/c2/c2");
        System.out.println(new String(client.getData().forPath("/c2/c2/c2/c3")));
        client.setData().forPath("/c2/c2/c2/c3","changed".getBytes());
        System.out.println(new String(client.getData().forPath("/c2/c2/c2/c3")));
        client.delete().forPath("/c2/c2/c2/c3");
    }

    @Test

    public void testNodeCache(){
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
                .retryPolicy(new ExponentialBackoffRetry(1000,3)).build();
        client.start();
    }
}
