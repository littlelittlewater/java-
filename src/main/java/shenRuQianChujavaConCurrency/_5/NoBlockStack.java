package shenRuQianChujavaConCurrency._5;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 非阻塞的栈
 */
public class NoBlockStack <E>{
    public static class  Node<E>{
        Node<E> next;
        final  E item;

        public Node(E item){
            this.item = item ;
        }
    }

    AtomicReference<Node<E>> head = new AtomicReference<>();

    public E pop(){
        Node<E> oldHead;
        Node<E> newHead;
        do {
            oldHead = head.get();
            if (oldHead == null)
                return null;
            newHead = oldHead.next;
        } while (!head.compareAndSet(oldHead,newHead)); //失败则重试
        return oldHead.item;
    }

    public void push(E item) {
        Node<E> oldHead;
        Node<E> newHead = new Node<>(item);
        do{
            oldHead = head.get();
            newHead.next = oldHead;
        }while (head.compareAndSet(oldHead,newHead));
    }

}
