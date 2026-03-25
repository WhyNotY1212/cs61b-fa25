import edu.princeton.cs.algs4.In;

import java.util.Comparator;

public class WordFinder {
    /**
     *  Returns the maximum string according to the provider comparator.
     *  If multiple strings are considered equal by c, return the first in
     *  the array.
     *  Use loops. Don't use Collections.max or similar.
     */
    public static String findMax(String[] strings, Comparator<String> c) {
        int max =0;
        for(int i=1;i<strings.length;i++){
            if(c.compare(strings[max],strings[i])<0){
                max =i;
            }
        }
        return strings[max];
    }

    public static void main(String[] args) {
        In in = new In("data/mobydick.txt");
        String[] words = in.readAllStrings();
        // TODO: Print only the word with the most lower case vowels.
        //       Use your findMax method!
        //
        //       Start by creating a Comparator that compares based on lower case vowels.
        Comparator<String> vowelComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                char[] vowels ={'a','e','i','o','u'};
                int count1 = 0;
                int count2 = 0;
                for(int i=0;i<o1.length();i++){
                    char a = o1.charAt(i);
                    if(a=='a'||a=='e'||a=='i'||a=='o'||a=='u'){
                        count1++;
                    }
                }
                for(int i=0;i<o2.length();i++){
                    char a = o2.charAt(i);
                    if(a=='a'||a=='e'||a=='i'||a=='o'||a=='u'){
                        count2++;
                    }
                }
                return Integer.compare(count1,count2);
            }
        };
        System.out.println(findMax(words, vowelComparator));
        // Optional task: Play around with lists of words from Wikipedia articles.
        // String[] zebraWords = ParseUtils.fetchWords("https://en.wikipedia.org/wiki/zebra");
        // System.out.println(findMax(zebraWords, vowelComparator));
    }
}
