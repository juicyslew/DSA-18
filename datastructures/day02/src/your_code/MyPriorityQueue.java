package your_code;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {
    private ArrayList<Integer> al;

    public MyPriorityQueue(){
        al = new ArrayList<>();
    }

    //O(n)
    public void enqueue(int item) {
        for (int i = 0; i < al.size(); i++){
            if (al.get(i) >= item){
                al.add(i, item);
                return;
            }
        }
        al.add(item);
    }

    /**
     * Return and remove the largest item on the queue.
     */
    // O(1)
    public int dequeueMax() {
        int val = al.remove(al.size()-1);
        return val;
    }

}