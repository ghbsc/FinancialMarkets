package OrderMatchingEngine;

import java.util.LinkedList;
import java.util.Queue;

public class LiteBlockingQueue<T> {

    private Queue<T> queue = new LinkedList<T>();
    private int capacity;
    public LiteBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T element) throws InterruptedException {
        while(queue.size() == capacity) {
            wait();
        }

        queue.add(element);
        notify();
    }

    public synchronized T take() throws InterruptedException {
        while(queue.isEmpty()) {
            wait();
        }

        T item = queue.remove();
        notify();
        return item;
    }
}