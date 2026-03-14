import java.util.ArrayList;
import java.util.List;

public class ListExercises {
    /** Returns the total sum in a list of integers */
    public static int sum(List<Integer> L) {
        // TODO: Implement this method
        int sum = 0;
        for(int i : L){
            sum+=i;
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Implement this method
        List<Integer> L1 = new ArrayList<>();
        for(int i: L){
            if(i%2==0){
                L1.add(i);
            }
        }
        return L1;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Implement this method
        List<Integer> L = new ArrayList<>();
        for(int i: L2){
            if(L1.contains(i)){
                L.add(i);
            }
        }
        return L;
    }

    public static int countOccurrencesOfWord(List<String> words, String w) {
        // TODO: Implement this method
        int count = 0;
        for(String i:words){
            if(i.equals(w)){
                count++;
            }
        }
        return count;
    }

    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Implement this method
        int count = 0;
        for(String s:words){
            for(int i = 0;i<s.length();i++){
                if(s.charAt(i)==c){
                    count++;
                }
            }
        }
        return count;
    }
}
