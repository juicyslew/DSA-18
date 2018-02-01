package your_code;
import ADTs.StackADT;

import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll;
    private LinkedList<Integer> maxs;

    public MyStack() {
        ll = new LinkedList<>();
        maxs = new LinkedList<>();
    }

    @Override
    public void push(Integer e) {
        if (ll.size() == 0){
            maxs.addFirst(e);
        }else if (e > maxs.get(0)){
            maxs.addFirst(e);
        }
        ll.addFirst(e);
    }

    @Override
    public Integer pop() {
        Integer pop = ll.removeFirst();
        if (pop == maxs.get(0)){
            maxs.removeFirst();
        }
        return pop;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer maxElement() {
        return maxs.get(0);
    }
}
