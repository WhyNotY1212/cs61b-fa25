import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

     @Test
     public void getTest(){
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]
         assertThat(lld1.get(2)).isEqualTo(0);
         assertThat(lld1.get(3)).isEqualTo(1);
         assertThat(lld1.get(5)).isEqualTo(null);
         assertThat(lld1.get(-6)).isEqualTo(null);
     }

    @Test
    public void getRecursiveTest() {
        LinkedListDeque61B<Integer> lld = new LinkedListDeque61B<>();

        lld.addLast(10);
        lld.addLast(20);
        lld.addLast(30);

        assertThat(lld.getRecursive(0)).isEqualTo(10);
        assertThat(lld.getRecursive(1)).isEqualTo(20);
        assertThat(lld.getRecursive(2)).isEqualTo(30);

        assertThat(lld.getRecursive(3)).isNull();
        assertThat(lld.getRecursive(-1)).isNull();
    }

    @Test
    public void removeFirstEmptyTest() {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();

        assertThat(lld.removeFirst()).isNull();
        assertThat(lld.isEmpty()).isTrue();
        assertThat(lld.size()).isEqualTo(0);
    }
    @Test
    public void removeFirstSingleTest() {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addLast(10);

        assertThat(lld.removeFirst()).isEqualTo(10);
        assertThat(lld.isEmpty()).isTrue();
        assertThat(lld.size()).isEqualTo(0);
        assertThat(lld.toList()).containsExactly().inOrder();
    }

    @Test
    public void removeFirstMultipleTest() {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addLast(10);
        lld.addLast(20);
        lld.addLast(30);

        assertThat(lld.removeFirst()).isEqualTo(10);
        assertThat(lld.size()).isEqualTo(2);
        assertThat(lld.isEmpty()).isFalse();
        assertThat(lld.toList()).containsExactly(20, 30).inOrder();
    }
    @Test
    public void removeLastEmptyTest() {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();

        assertThat(lld.removeLast()).isNull();
        assertThat(lld.isEmpty()).isTrue();
        assertThat(lld.size()).isEqualTo(0);
    }
    @Test
    public void removeLastSingleTest() {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addLast(10);

        assertThat(lld.removeLast()).isEqualTo(10);
        assertThat(lld.isEmpty()).isTrue();
        assertThat(lld.size()).isEqualTo(0);
        assertThat(lld.toList()).containsExactly().inOrder();
    }

    @Test
    public void removeLastMultipleTest() {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addLast(10);
        lld.addLast(20);
        lld.addLast(30);

        assertThat(lld.removeLast()).isEqualTo(30);
        assertThat(lld.size()).isEqualTo(2);
        assertThat(lld.isEmpty()).isFalse();
        assertThat(lld.toList()).containsExactly(10, 20).inOrder();
    }

    @Test
    public void removeFirstRepeatedTest() {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addLast(1);
        lld.addLast(2);
        lld.addLast(3);

        assertThat(lld.removeFirst()).isEqualTo(1);
        assertThat(lld.toList()).containsExactly(2, 3).inOrder();

        assertThat(lld.removeFirst()).isEqualTo(2);
        assertThat(lld.toList()).containsExactly(3).inOrder();

        assertThat(lld.removeFirst()).isEqualTo(3);
        assertThat(lld.isEmpty()).isTrue();
        assertThat(lld.size()).isEqualTo(0);

        assertThat(lld.removeFirst()).isNull();
    }
    @Test
    public void removeLastRepeatedTest() {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addLast(1);
        lld.addLast(2);
        lld.addLast(3);

        assertThat(lld.removeLast()).isEqualTo(3);
        assertThat(lld.toList()).containsExactly(1, 2).inOrder();

        assertThat(lld.removeLast()).isEqualTo(2);
        assertThat(lld.toList()).containsExactly(1).inOrder();

        assertThat(lld.removeLast()).isEqualTo(1);
        assertThat(lld.isEmpty()).isTrue();
        assertThat(lld.size()).isEqualTo(0);

        assertThat(lld.removeLast()).isNull();
    }

    @Test
    public void mixedRemoveTest() {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addLast(10);
        lld.addLast(20);
        lld.addLast(30);
        lld.addLast(40);

        assertThat(lld.removeFirst()).isEqualTo(10);
        assertThat(lld.removeLast()).isEqualTo(40);
        assertThat(lld.toList()).containsExactly(20, 30).inOrder();

        assertThat(lld.removeLast()).isEqualTo(30);
        assertThat(lld.removeFirst()).isEqualTo(20);

        assertThat(lld.isEmpty()).isTrue();
        assertThat(lld.size()).isEqualTo(0);
    }

    @Test
    public void sizeRobustTest() {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();

        assertThat(lld.size()).isEqualTo(0);

        lld.addFirst(1);
        lld.addLast(2);
        lld.addLast(3);

        assertThat(lld.size()).isEqualTo(3);

        lld.removeFirst();
        assertThat(lld.size()).isEqualTo(2);

        lld.removeLast();
        assertThat(lld.size()).isEqualTo(1);

        lld.removeLast();
        assertThat(lld.size()).isEqualTo(0);
    }
    @Test
    public void isEmptyRobustTest() {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();

        assertThat(lld.isEmpty()).isTrue();

        lld.addLast(1);
        assertThat(lld.isEmpty()).isFalse();

        lld.removeFirst();
        assertThat(lld.isEmpty()).isTrue();

        assertThat(lld.removeFirst()).isNull();
        assertThat(lld.isEmpty()).isTrue();
    }
}