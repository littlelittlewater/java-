下面是JAVA NIO中的一些主要Channel的实现：
 * FileChannel
 * DatagramChannel
 * SocketChannel
 * ServerSocketChannel 
 
 
 * 既可以从通道中读取数据，又可以写数据到通道。但流的读写通常是单向的。
 * 通道可以异步地读写。
 * 通道中的数据总是要先读到一个Buffer，或者总是要从一个Buffer中写入。

这些Buffer覆盖了你能通过IO发送的基本数据类型：byte, short, int, long, float, double 和 char。
  *  ByteBuffer
  *  CharBuffer
  *  DoubleBuffer
  *  FloatBuffer
  *  IntBuffer
  *  LongBuffer
  *  ShortBuffer
  * MappedByteBuffer
  
  
使用Buffer读写数据一般遵循以下四个步骤：
1. 写入数据到Buffer
2. 调用flip()方法
3. 从Buffer中读取数据
4. 调用clear()方法或者compact()方法

当向buffer写入数据时，buffer会记录下写了多少数据。
一旦要读取数据，需要通过flip()方法将Buffer从写模式切换到读模式。
在读模式下，可以读取之前写入到buffer的所有数据。

一旦读完了所有的数据，就需要清空缓冲区，让它可以再次被写入。有两种方式能清空缓冲区：调用clear()或compact()方法。
clear()方法会清空整个缓冲区。compact()方法只会清除已经读过的数据。任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面。
 
buffer的构成：
  * capacity 容量
  * position 当前的位置
  * limit  写模式下，limit等于Buffer的capacity。读模式时，limit会被设置成写模式下的position值。

向Buffer中写数据
  * Channel写到Buffer。
  * 通过Buffer的put()方法写到Buffer里。

flip()方法
  写模式切换到读模式
  
从Buffer中读取数据
    * 从Buffer读取数据到Channel
    * 使用get()方法从Buffer中读取数据
    
rewind()方法
    Buffer.rewind()将position设回0，所以你可以重读Buffer中的所有数据。limit保持不变，仍然表示能从Buffer中读取多少个元素（byte、char等）

Scatter/Gather 
    scatter/gather用于描述从Channel（译者注：Channel在中文经常翻译为通道）中读取或者写入到Channel的操作。

Selector
  * Selector允许单线程处理多个 Channel。
  * 要使用Selector，得向Selector注册Channel，然后调用它的select()方法。这个方法会一直阻塞到某个注册的通道有事件就绪。一旦这个方法返回，线程就可以处理这些事件，事件的例子有如新连接进来，数据接收等。


