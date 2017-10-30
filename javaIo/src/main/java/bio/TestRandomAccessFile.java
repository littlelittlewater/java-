package bio;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 之前的File类只是针对文件本身进行操作的，而如果相对文件内容进行操作，则可以使用RandomAccessFile类，此类属于随即读取类，可以随机的读取一个文件中指定位置的数据。
 */
public class TestRandomAccessFile {
    @Test
    public void testWrite() throws IOException {
        File f = new File("/data/var" + File.separator+"test.txt");
        RandomAccessFile raf=new RandomAccessFile(f,"rw");//读写模式，如果该路径不存在会自动创建
        String name1="jim";
        int age1 =20;
        String name2="Tom";
        int age2=30;
        raf.writeBytes(name1);
        raf.writeInt(age1);
        raf.writeBytes(name2);
        raf.writeInt(age2);
        raf.close();
    }

    @Test
    public void testRead() throws IOException {
        File f = new File("/data/var" + File.separator+"test.txt");
        RandomAccessFile raf=new RandomAccessFile(f,"r");//以读模式打开
        raf.skipBytes(7);//跳过第一个人的信息
        byte[] bs=new byte[3];
        for(int i=0;i<bs.length;i++){
            bs[i]=raf.readByte();
        }
        String name2=new String(bs);
        int age2=raf.readInt();
        System.out.println(name2+"  "+age2);

        raf.seek(0);//指针回到文件开头，读取第二个人的信息
        for(int i=0;i<bs.length;i++){
            bs[i]=raf.readByte();
        }
        String name1=new String(bs);
        int age1=raf.readInt();
        System.out.println(name1+"  "+age1);
    }
}
