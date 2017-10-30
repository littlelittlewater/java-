package bio;


import org.junit.*;
import org.junit.Test;

import java.io.*;

/**
 * 程序中的输入输出都是以流的形式保存的，流中保存的实际上全都是字节文件
 *
 * 在java.io包中操作文件内容的主要有两大类：字节流、字符流，两类都分为输入和输出操作。
 * 在字节流中输出数据主要是使用OutputStream完成，输入使的是InputStream，在字符流中输出主要是使用Writer类完成，输入流主要使用Reader类完成
 *
 * 字节流主要是操作byte类型数据，以byte数组为准，主要操作类就是OutputStream、InputStream
 *
 * 在程序中一个字符等于两个字节(unicode 编码)，那么java提供了Reader、Writer两个专门操作字符流的类。
 *
 * 字节流在操作的时候本身是不会用到缓冲区（内存）的，是与文件本身直接操作的，而字符流在操作的时候是使用到缓冲区的
 * 字节流在操作文件时，即使不关闭资源（close方法），文件也能输出，但是如果字符流不使用close方法的话,
 * 则不会输出任何内容，说明字符流用的是缓冲区，并且可以使用flush方法强制进行刷新缓冲区，
 * 这时才能在不close的情况下输出内容
 *
 *
 * 从JDK文档中可以知道FileOutputStream是OutputStream的直接子类。FileInputStream也是InputStream的直接子类，
 * 但是在字符流文件的两个操作类却有一些特殊，FileWriter并不直接是Writer的子类，而是OutputStreamWriter的子类，
 * 而FileReader也不直接是Reader的子类，而是InputStreamReader的子类，那么从这两个类的继承关系就可以清楚的发现，
 * 不管是使用字节流还是字符流实际上最终都是以字节的形式操作输入输出流的。
 * 也就是说，传输或者从文件中读取数据的时候，文件里真正保存的数据永远是字节。
 */
public class TestStream {
    static String path = "/data/var" + File.separator + "test.txt";

    @org.junit.Test
    public void TestInputStream() throws Exception {
        File f = new File(path);
        FileInputStream inputStream = new FileInputStream(f);
        byte[] b = new byte[2014];
        int lenth = inputStream.read(b);
        inputStream.close();
        System.out.println(new String(b, 0, lenth));
    }

    @org.junit.Test
    public void testOutputStream() throws Exception {
        File f = new File(path);
        FileOutputStream fileOutputStream = new FileOutputStream(f);

        fileOutputStream.write("fdasf".getBytes());
        fileOutputStream.close();
    }

    @Test
    public void testWriter() throws Exception {
        File f = new File(path);
        Writer out = new FileWriter(f);
        String str = "Hello World";
        out.write(str);
        out.close();
    }

    @Test
    public void testReader() throws Exception {
        File f = new File(path);
        Reader input = new FileReader(f);
        char[] c = new char[1024];
        int len = input.read(c);
        input.close();
        System.out.println(new String(c, 0, len));
    }

    @Test
    public void OutputStreamConvertToWriter() throws Exception{
        File f = new File(path);
        Writer out=new OutputStreamWriter(new FileOutputStream(f));
        out.write("Hello World!!!");
        out.close();
    }

    @Test
    public void InputStreamConvertToOuter() throws Exception{
        File f = new File(path);
        Reader input=new InputStreamReader(new FileInputStream(f));
        char[] c=new char[1024];
        int len=input.read(c);
        input.close();
        System.out.println(new String(c,0,len));
    }
}
