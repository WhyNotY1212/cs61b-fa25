import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        // TODO: Implement this method
        Map<Character,Integer> m =new HashMap<>();
        int i=1;
        for(char k ='a'; k<='z' ;k++){
            m.put(k,i++);
        }
        return m;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        // TODO: Implement this method
        Map<Integer, Integer> m = new TreeMap<>();
        for(int i :nums){
            m.put(i, (int) Math.pow(i,2));
        }
        return m;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        // TODO: Implement this method
        Map<String,Integer> m = new HashMap<>();
        for(String i:words){
            if(!m.containsKey(i)){
                m.put(i,1);
            }else{
                m.put(i,m.get(i)+1);
            }
        }
        return m;
    }
}
