import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B <T> implements Deque61B<T>{
    private  T[] items;
    private  int  size;
    private int nextfirst;
    private  int nextlast;
    public ArrayDeque61B() {
        items = (T[]) new Object[8];
        size = 0;
        nextfirst = 0;
        nextlast = 1;
    }

    private void resize(int capacity){
        T[] a = (T[]) new Object[capacity];
        int firstIndex = (nextfirst + 1) % items.length;

        for (int i = 0; i < size; i++) {
            a[i] = items[(firstIndex + i) % items.length];
        }

        items = a;
        nextfirst = capacity - 1;
        nextlast = size;
    }

    private void lim_size(){

    }
    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        if(size == items.length){
            resize(items.length*2);
        }
        items[nextfirst] = x;
        nextfirst =(nextfirst-1+ items.length)%items.length;

        size++;
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        if(size==items.length){
            resize(items.length*2);
        }
        items[nextlast] = x;
        nextlast = (nextlast+1)%items.length;

        size++;
    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        for(int i=0;i<size;i++){
            list.add(items[(nextfirst+1+i)% items.length]);
        }
        return list;
    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size==0;
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
        if(size==0){
            return null;
        }
        size--;
        T x = items[(nextfirst+1+items.length)%items.length];
        items[(nextfirst+1+items.length)% items.length] =null;
        nextfirst = (nextfirst+1)%items.length;
        if((double) size /items.length<0.25){
            resize(items.length/2);
        }
        return x;
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
        size--;
        T x = items[(nextlast-1+ items.length)% items.length];
        items[(nextlast-1+ items.length)% items.length] =null;
        nextlast = (nextlast-1+ items.length)%items.length;
        if((double) size /items.length<0.25){
            resize(items.length/2);
        }
        return x;
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
        if(index>=size||index<0){
            return null;
        }
        return items[(nextfirst+1+index)% items.length];
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
        throw new UnsupportedOperationException("No need to implement getRecursive for ArrayDeque61B.");
    }

}
