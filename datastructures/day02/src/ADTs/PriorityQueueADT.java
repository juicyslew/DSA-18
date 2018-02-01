package ADTs;

/**
 * The Priority Queue Abstract Data Type, which has these methods:
 * enqueue(T e) adds an element to the "back" of the queue
 * dequeueMax() removes the Maximum of the queue
 *
 * @param <T>
 */
public interface PriorityQueueADT<T> {
    void enqueue(T item);
    T dequeueMax();
}
