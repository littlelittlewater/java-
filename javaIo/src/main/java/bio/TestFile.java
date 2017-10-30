package bio;

import org.junit.Test;
import java.io.File;
import java.io.IOException;

/**
 * File类是在java.io包中唯一与文件本身有关的
 * 可以使用File类创建、删除等常见的文件操作
 * 在使用File类指定路径的时候一定要注意操作系统间的差异，尽量使用separator进行分割
 */
public class TestFile {
    static String  path = "/data/var" + File.separator + "test.txt" ;
    @Test
    //创建一个文件
    public void test1() {
        File f = new File(path);//为增加可移植性，建议使用File.separator
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //删除一个文件
    @Test
    public void testDel(){
        File f = new File(path);//为增加可移植性，建议使用File.separator
        if(f.exists()){
            f.delete();
        }
    }

    //创建文件夹
    @Test
    public void testMakedir(){
        File f = new File("/data/var/dir");
        f.mkdir();
    }
}
