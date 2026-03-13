public class PrintIndexed {
   /**
     * Prints each character of a given string followed by the reverse of its index.
     * Example: printIndexed("hello") -> h4e3l2l1o0
     */
   public static void printIndexed(String s) {
      // TODO: Fill in this function
      StringBuilder ans = new StringBuilder();
      for(int i=0;i<s.length();i++){
         ans.append(s.charAt(i));
         ans.append(s.length()-i-1);
      }
      System.out.println(ans);
   }

   public static void main(String[] args) {
      printIndexed("hello");
      printIndexed("cat"); // should print c2a1t0
   }
}