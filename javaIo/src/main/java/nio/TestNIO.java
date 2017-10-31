package nio;

import org.junit.Test;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class TestNIO {
    static String path = "/data/var" + File.separator + "test.txt";

    /**
     * 注意 buf.flip() 的调用，首先读取数据到Buffer，
     * 然后反转Buffer,接着再从Buffer中读取数据。
     *
     *
     */
    @Test
    public void testChannel() throws Exception {
        RandomAccessFile aFile = new RandomAccessFile(path, "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            buf.flip();
            while (buf.hasRemaining()) {  //挨个读取
                System.out.print((char) buf.get());
            }
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

}
