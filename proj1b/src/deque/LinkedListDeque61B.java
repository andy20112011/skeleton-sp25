package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>{

    /** a helper class Nodes. */
    private class Node {
        private T item;
        private Node prev;
        private Node next;

        private Node(Node p, Node n, T i) {
            prev = p;
            next = n;
            item = i;
        }
    }

    private Node sentinel;
    private int size;

    /** an empty constructor for the list */
    public LinkedListDeque61B() {
        size = 0;
        sentinel = new Node(sentinel, sentinel, null);
    }

    /** a little helper function for when the list is empty. */
    private void addEmpty(T x) {
        sentinel.next = new Node(sentinel, sentinel, x);
        sentinel.prev = sentinel.next;
        size += 1;
    }


    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        if (this.size == 0) {
            addEmpty(x);
        } else {
            sentinel.next = new Node(sentinel, sentinel.next, x);
            sentinel.next.next.prev = sentinel.next;
            size += 1;
        }
    }


    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        if (this.size == 0) {
            addEmpty(x);
        } else {
            sentinel.prev = new Node(sentinel.prev, sentinel, x);
            sentinel.prev.prev.next = sentinel.prev;
            size += 1;
        }
    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        if (size == 0) {
            return returnList;
        }
        Node p = sentinel.next;
        while (p.item != null) {
            returnList.add(p.item);
            p = p.next;
        }
        return returnList;
    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            Node returnNode = sentinel.next;
            sentinel.next = returnNode.next;
            returnNode.next.prev = sentinel;
            size -= 1;
            return returnNode.item;
        }
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            Node returnNode = sentinel.prev;
            sentinel.prev = returnNode.prev;
            returnNode.prev.next = sentinel;
            size -= 1;
            return returnNode.item;
        }
    }

    /**
     * The Deque61B abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T get(int index) {
        if (index > size || index < 0) {
            return null;
        } else if (isEmpty()) {
            return null;
        } else {
            Node p = sentinel;
            for (int i = 0; i <= index; i++) {
                p = p.next;
            }
            return p.item;
            }
    }

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T getRecursive(int index)
    {
        if (index > size || index < 0)
        {
            return null;
        } else if (isEmpty())
        {
            return null;
        } else
            {
                return getRecursiveHelper(sentinel.next, index);
            }
    }

    private T getRecursiveHelper(Node current, int remaining) {
        if (remaining == 0) {
            return current.item;
        }
        // recursive case, move to next node, decrease counter
        return getRecursiveHelper(current.next, remaining - 1);
    }

    private class LinkedListDequeIterator implements Iterator<T> {

        private Node current;

        LinkedListDequeIterator() {
            current = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return current != sentinel;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            T item = current.item;
            current = current.next;
            return item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {return true;}

        if (other instanceof Deque61B<?> otherList) {
            return this.toList().equals(otherList.toList());
        }
        return false;
    }
}
