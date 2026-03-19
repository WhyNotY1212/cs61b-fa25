import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    static class Node<T>{
        T value;
        Node<T> next;
        Node<T> prev;

        public Node(T value, Node<T> a,Node<T> b){
            this.value =value;
            this.prev = a;
            this.next = b;
        }
    }
    private Node<T> sentinel;
    private int size;

    public LinkedListDeque61B(){
        sentinel = new Node<T>(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        Node<T> first =sentinel.next;
        Node<T> p = new Node<T>(x,sentinel,first);
        first.prev =p;
        sentinel.next = p;
        size++;
        }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        Node<T> last =sentinel.prev;
        Node<T> p = new Node<T>(x,last,sentinel);
        last.next =p;
        sentinel.prev = p;
        size++;
    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> List = new ArrayList<>();
        Node<T> p = sentinel.next;
        while(p!=sentinel){
            List.add(p.value);
            p=p.next;
        }
        return List;

    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return (size==0);
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
        if(size ==0){
            return null;
        }
        T val = sentinel.next.value;
        Node<T> p =sentinel.next;
        p.next.prev =sentinel;
        sentinel.next=p.next;
        size --;
        return val;
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        if(size==0){
            return null;
        }
        Node<T> p = sentinel.prev;
        T val = p.value;
        p.prev.next = sentinel;
        sentinel.prev = p.prev;
        size--;
        return val;
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
        if(index>=size){
            return null;
        }
        if(index<0){
            return null;
        }
        Node<T> p =sentinel.next;
        for(int i =0;i<index;i++){
            p=p.next;
        }
        return p.value;
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
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node<T> node, int index) {
        if (index == 0) {
            return node.value;
        }
        return getRecursiveHelper(node.next, index - 1);
    }

    public static void main(String[] args) {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addLast(0);   // [0]
        lld.addLast(1);   // [0, 1]
        lld.addFirst(-1); // [-1, 0, 1]
    }

}
