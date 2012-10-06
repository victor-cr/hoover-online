package edu.programming.hoover.model;

import edu.programming.hoover.exception.BagOverflowHooverException;
import edu.programming.hoover.exception.EmptyBagHooverException;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: id967092
 * Date: 06/10/12
 * Time: 13:13
 * To change this template use File | Settings | File Templates.
 */
public class Bag implements Serializable, Iterable<Integer> {
    private static final long serialVersionUID = 1L;

    private final int capacity;
    private final LinkedList<Integer> queue = new LinkedList<Integer>();

    public Bag(int capacity) {
        this.capacity = capacity;
    }

    public Bag(Bag bag) {
        this.capacity = bag.capacity;

        this.queue.addAll(bag.queue);
    }

    public Bag(int capacity, Bag bag) throws BagOverflowHooverException {
        if (capacity == 0 || bag.queue.size() > capacity) {
            throw new BagOverflowHooverException(capacity);
        }

        this.capacity = capacity;
        this.queue.addAll(bag.queue);
    }

    public int pop() throws EmptyBagHooverException {
        if (queue.isEmpty()) {
            throw new EmptyBagHooverException();
        }

        return queue.pollFirst();
    }

    public void push(int item) throws BagOverflowHooverException {
        if (queue.size() >= capacity) {
            throw new BagOverflowHooverException(capacity);
        }

        queue.offerFirst(item);
    }

    public int peek() throws EmptyBagHooverException {
        if (queue.isEmpty()) {
            throw new EmptyBagHooverException();
        }

        return queue.peekFirst();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public boolean isFull() {
        return queue.size() == capacity;
    }

    public void pushAll(int... items) throws BagOverflowHooverException {
        for (int item : items) {
            push(item);
        }
    }

    public void clear() {
        queue.clear();
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Itr(queue.iterator());
    }

    private static class Itr implements Iterator<Integer> {
        private final Iterator<Integer> i;

        private Itr(Iterator<Integer> i) {
            this.i = i;
        }

        @Override
        public boolean hasNext() {
            return i.hasNext();
        }

        @Override
        public Integer next() {
            return i.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove() does not supported");
        }
    }
}
