import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private Node sentinel;

    public class Node {
        public  T item;
        public Node prev;
        public Node next;

        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    @Override
    public void addFirst(T x) {
        sentinel.next = new Node(x, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
    }

    @Override
    public void addLast(T x) {
        sentinel.prev = new Node(x, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node pointer = sentinel.next;

        while (pointer != sentinel) {
            returnList.add(pointer.item);
            pointer = pointer.next;
        }

        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        Node pointer = sentinel.next;
        int size = 0;

        while (pointer != sentinel) {
            size++;
            pointer = pointer.next;
        }

        return size;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        Node pointer = sentinel;

        if (pointer.next == sentinel || index > size()) {
            return null;
        }

        for (int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
            return pointer.item;
    }

    @Override
    public T getRecursive(int index) {
        return getRecursive(index, sentinel);
    }

    public T getRecursive(int index, Node pointer) {
        if (index == 0 || pointer.next == sentinel || index > size()) {
            return pointer.item;
        } else {
            return getRecursive(index - 1, pointer.next);
        }
    }

}
