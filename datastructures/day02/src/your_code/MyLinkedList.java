package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        if (tail != null){
            Node newnode = new Node(c, tail, null);
            tail.next = newnode;
            tail = tail.next;
        }else {
            Node newnode = new Node(c, null, null);
            tail = newnode;
            head = newnode;
        }
        size++;
    }

    public void addFirst(Chicken c) {
        // TODO
        if (head != null){
            Node newnode = new Node(c, null, head);
            head.prev = newnode;
            head = head.prev;
        }else {
            Node newnode = new Node(c, null, null);
            tail = newnode;
            head = newnode;
        }
        size++;
    }

    public Chicken get(int index) {
        return Find(index).val;
    }

    public Chicken remove(int index) {
        if (index == size-1){
            return removeLast();
        }else if(index == 0){
            return removeFirst();
        }else {
            Node tmp = Find(index);
            tmp.next.prev = tmp.prev;
            tmp.prev.next = tmp.next;
            size--;
            return tmp.val;
        }
    }

    public Chicken removeFirst() {
        if (head != null){
            Chicken tmp = head.val;
            head = head.next;
            if (head != null) {
                head.prev = null;
            }else{
                head = null;
                tail = null;
            }
            size--;
            return tmp;
        }else{
            throw new IndexOutOfBoundsException();
        }
    }

    public Chicken removeLast() {
        if (tail != null){
            Chicken tmp = tail.val;
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            }else{
                head = null;
                tail = null;
            }

            size--;
            return tmp;
        }else{
            throw new IndexOutOfBoundsException();
        }
    }

    private Node Find(int index){
        if (index >= size){
            throw new IndexOutOfBoundsException();
        }
        if (index > size/2){
            Node tmp = tail;
            int i = size-1;
            while (i > index) { //pretty sure correct
                tmp = tmp.prev;
                i--;
            }
            return tmp;

        }else {
            Node tmp = head;
            int i = 0;
            while (i < index) { //pretty sure correct
                tmp = tmp.next;
                i++;
            }
            return tmp;
        }
    }
}