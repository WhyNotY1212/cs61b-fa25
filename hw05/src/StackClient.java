public class StackClient {
    public static Stack flipped(Stack s){
        Stack flip = new Stack();
        Stack temp = new Stack();
        while(s.size()>0){
            int x = s.pop();
            temp.push(x);
            flip.push(x);
        }
        while(temp.size()>0){
            int x =temp.pop();
            s.push(x);
        }
        return flip;
    }

}
