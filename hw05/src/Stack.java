

public class Stack {
    static class Node {
        public int value;
        public Node next;
        public Node(int value,Node next){
            this.value =value;
            this .next =next;
        }
    }

    private  int sum;
    private  int size;
    private Node sentinel;
    public Stack() {
            sentinel = new Node(6666,null);
            size = 0;
    }

    public void push(int x){
        Node node = new Node(x,sentinel.next);
        sentinel.next = node;
        size++;
        sum+=x;
    }

    public int pop(){
        int v = sentinel.next.value;
        sum-=v;
        sentinel.next =sentinel.next.next;
        size--;
        return v;
    }

    public int size(){
        return size;
    }

    public int sum(){
        return sum;
    }


}
