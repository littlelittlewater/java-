package _5;

import java.util.concurrent.atomic.AtomicReference;

//非阻塞链表
public class NoBlockLinkedQueue<E>{
    private static class Node <E> {
        final E item;
        final AtomicReference<Node<E>> next;
        Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<Node<E>>(next);
        }
    }
    //持有一个链表头
    private AtomicReference<Node<E>> head
            = new AtomicReference<Node<E>>(new Node<E>(null, null));
    //链表尾指针
    private AtomicReference<Node<E>> tail = head;



    /***
     *  放置元素
     * A: 检查是否发生变化
     * B: 辅助作用，将尾指针往后移
     * C: 更新尾节点
     * D: 尝试将尾节点往后移，
     *
     * 第一个 CAS（C）可能因为两个线程竞争访问队列当前的最后一个元素而失败；在这种情况下，没有发生修改，失去 CAS 的线程会重新装入尾指针并再次尝试。
     * 如果第二个 CAS（D）失败，插入线程不需要重试 —— 因为其他线程已经在步骤（B）中替它完成了这个操作！
     */
    public boolean put(E item) {
        Node<E> newNode = new Node<E>(item, null);
        while (true) {
            //获取当前状态的尾指针
            Node<E> curTail = tail.get();
            //获取尾指针的下一个位置
            Node<E> residue = curTail.next.get();
            //当前位置的尾指针是否改变
            if (curTail == tail.get()) {
                //尾指针的后一个是否为空
                if (residue == null) /* A */ {
                    //如果尾指针的后一个位置为空，就插入  如果此时线程有一个竞争导致失败的话
                    if (curTail.next.compareAndSet(null, newNode)) /* C */ {
                        //更新尾节点
                        tail.compareAndSet(curTail, newNode) /* D */ ;  //不需要重试 因为第二个线程已经这样做了
                        return true;
                    }
                } else {
                    //尝试修改尾指针，因为尾指针后面不为空  可以做一个辅助
                    tail.compareAndSet(curTail, residue) /* B */;
                }
            }
        }
    }
}
