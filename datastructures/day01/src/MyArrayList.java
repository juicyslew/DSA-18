public class MyArrayList {
    private Cow[] elems;
    private int size;

    // Runtime: O(1)
    public MyArrayList() {
        size = 0;
        elems = new Cow[10];
        System.out.printf("You can now have a cow farm that can hold up to 10 cows.\n");
    }

    // Runtime: O(1)
    public MyArrayList(int capacity) {
        size = 0;
        elems = new Cow[capacity];
        System.out.printf("You can now have a cow farm that can hold up to %d cows.\n", capacity);
    }

    // Runtime: O(1)
    public void add(Cow c) {
        grow();
        elems[size] = c;
        size++;
        System.out.printf("You have a new cow! You now have %d cows. Good work.\n", size);

    }

    // Runtime: O(1)
    public int size() {
        System.out.printf("You currently have %d cows.\n", size);
        return size;
    }

    // Runtime: O(1)
    public Cow get(int index) {
        if (index >= size){
            System.out.printf("What're you doing, you don't have %d cows!  You only have %d.\n", index, size);
            throw new IndexOutOfBoundsException();
        }else {
            System.out.printf("You got Cow.  It's cow %d.\n", index);
            return elems[index];
        }
    }

    // Runtime: O(n)
    public Cow remove(int index) {
        shrink();
        if (index >= size){
            System.out.printf("You can't remove cow %d if you only have %d.  Figure it out.\n", index, size);
            throw new IndexOutOfBoundsException();
        }else {
            size--;
            for (int i = index; i < size; i++) {
                elems[i] = elems[i + 1];
            }
        }
        System.out.printf("Cow %d sadly walks off of your cow farm.  One single tear runs down its face.\n", index);
        return null;
    }

    // Runtime: O(n)
    public void add(int index, Cow c) {
        grow();
        if (index > size){
            System.out.printf("The cow felt too lonely so far away from all the other cows and left as soon as it came.\n");
            throw new IndexOutOfBoundsException();
        }else{
            for (int i = size; i > index; i--) {
                elems[i] = elems[i-1];
            }
            size++;
            elems[index] = c;
            System.out.printf("You shake the cow's large hoof, and directs it to pen number %d.  You kick out all the other cows to the next pens\n", index);
        }
    }
    private void grow() {
        if (size >= elems.length-1){

            Cow[] tmp = new Cow[elems.length * 2];
            System.arraycopy(elems, 0, tmp, 0, elems.length);
            elems = tmp;

            System.out.printf("You ran out of space for your cows, you switch mortgage plans and get yourself a new cow farm.\n");
        }
    }
    private void shrink() {
        if (size <= elems.length/4) {
            Cow[] tmp = new Cow[elems.length/2];
            System.arraycopy(elems, 0, tmp, 0, elems.length);
            elems = tmp;

            System.out.printf("Your farm is toooooo big for your miniscule number of cows.  You chops it in half.\n");
        }
    }
}
