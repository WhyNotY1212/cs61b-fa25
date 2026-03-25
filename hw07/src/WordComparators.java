import edu.princeton.cs.algs4.In;
import org.checkerframework.checker.units.qual.C;

import java.util.Comparator;
import java.util.List;

public class WordComparators {

    /**
     * Returns a comparator that orders strings by the number of lowercase 'x' characters (ascending).
     */
    public static Comparator<String> getXComparator() {
        return new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int count1 = 0;
                int count2 = 0;
                for (int i = 0; i < o1.length(); i++) {
                    char c = o1.charAt(i);
                    if (c == 'x') count1++;
                }
                for (int i = 0; i < o2.length(); i++) {
                    char c = o2.charAt(i);
                    if (c == 'x') count2++;
                }
                return Integer.compare(count1, count2);
            }
        };
    }

    /**
     * Returns a comparator that orders strings by the count of the given character (ascending).
     */
    public static Comparator<String> getCharComparator(char c) {
        return new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int count1 = 0;
                int count2 = 0;
                for (int i = 0; i < o1.length(); i++) {
                    char b = o1.charAt(i);
                    if (b == c) count1++;
                }
                for (int i = 0; i < o2.length(); i++) {
                    char b = o2.charAt(i);
                    if (b == c) count2++;
                }
                return Integer.compare(count1, count2);
            }
        };
    }

    /**
     * Returns a comparator that orders strings by the total count of the given characters (ascending).
     */
    public static Comparator<String> getCharListComparator(List<Character> chars) {
        return new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int count1 = 0;
                int count2 = 0;
                for (int i = 0; i < o1.length(); i++) {
                    if (chars.contains(o1.charAt(i))) {
                        count1++;
                    }
                }
                for (int i = 0; i < o2.length(); i++) {
                    if (chars.contains(o2.charAt(i))) {
                        count2++;
                    }
                }
                return Integer.compare(count1, count2);
            }
        };
    }
}
