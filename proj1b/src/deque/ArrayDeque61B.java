package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayDeque61B<T>  implements Deque61B<T> {

    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;
    private int capacity = 8;
    private double RFACTOR = 2;

    /** An empty constructor */
    public ArrayDeque61B() {
        size = 0;
        items = (T[]) new Object[capacity];
        nextFirst = 4;
        nextLast = 5;
    }

    public ArrayDeque61B(int cap) {
        size = 0;
        items = (T[]) new Object[(cap > 8) ? cap : 8];
        nextFirst = 4;
        nextLast = 5;
    }


    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        items[nextFirst] = x;
        nextFirst = Math.floorMod(nextFirst - 1, capacity);
//        if (nextFirst == 0) {
//            nextFirst = capacity;
//        } else {
//            nextFirst -= 1;
//        }
        size += 1;
        if (size == capacity) {
            resize((int) Math.round(capacity * RFACTOR));
        }
    }

    private void resize(int newCapacity) {
        T[] a = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            a[Math.floorMod(i + 5, newCapacity)] = items[Math.floorMod(nextFirst + i + 1, capacity)];
        }
        nextFirst = 4;
        nextLast = 5 + size;
        items = a;
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        items[nextLast] = x;
        nextLast = Math.floorMod(nextLast + 1, capacity);
//        if (nextLast == capacity) {
//            nextLast = 0;
//        } else {
//            nextLast += 1;
//        }
        size += 1;
        if (size == capacity) {
            resize((int) Math.round(capacity * RFACTOR));
            capacity = (int) (capacity * RFACTOR);
        }
    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            returnList.add(items[nextFirst + i]);
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
        return (size == 0);
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
        if (size == 0) {
            return null;
        } else {
            nextFirst = Math.floorMod(nextFirst + 1, capacity);
            T returnItem = items[nextFirst];
            items[nextFirst] = null;
            size -= 1;
            if (size < capacity * 0.25) {
                resize(capacity/2);
            }
            return returnItem;
        }
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            nextLast = Math.floorMod(nextLast - 1, capacity);
            T returnItem = items[nextLast];
            items[nextLast] = null;
            size -= 1;
            if (size < capacity * 0.25) {
                resize(capacity/2);
            }
            return returnItem;
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
        if (index < 0 || index > size) {
            return null;
        } else {
            return items[Math.floorMod(nextFirst + index + 1, capacity)];
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
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    private class ArrayDequeIterator implements Iterator<T> {

        private int returned;

        ArrayDequeIterator() {
            returned = 0;
        }
        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return returned < size;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            T returnItem = items[Math.floorMod(nextFirst + 1 + returned, capacity)];
            returned += 1;
            return returnItem;
        }
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public String toString() {
        StringBuilder returnString = new StringBuilder("{");
        for (T x : this) {
            returnString.append(x);
            returnString.append(", ");
        }
        returnString.deleteCharAt(returnString.length());
        returnString.deleteCharAt(returnString.length());
        returnString.append("}");
        return returnString.toString();
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
