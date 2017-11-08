package current.coll013;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 执行写操作的时候，直接复制，然后更改原容器的引用
 * 可以实现并发的读，不需要任何锁
 * 写的时候有锁。
 * 读写分离的思想
 *
 *存在内存占用的问题，因为每次对容器结构进行修改的时候都要对容器进行复制，这么一来我们就有了旧有对象和新入的对象，会占用两份内存。如果对象占用的内存较大，就会引发频繁的垃圾回收行为，降低性能；
 * CopyOnWrite只能保证数据最终的一致性，不能保证数据的实时一致性。
 */

public class UseCopyOnWrite {

	public static void main(String[] args) {
		
		CopyOnWriteArrayList<String> cwal = new CopyOnWriteArrayList<String>();
		CopyOnWriteArraySet<String> cwas = new CopyOnWriteArraySet<String>();

		
	}
}
