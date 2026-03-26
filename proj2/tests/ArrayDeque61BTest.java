import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class ArrayDeque61BTest {

    @Test
    public void testAddFirstCoverage() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();

        // add_first_from_empty
        ad.addFirst(1);
        assertThat(ad.toList()).containsExactly(1).inOrder();
        assertThat(ad.size()).isEqualTo(1);
        assertThat(ad.isEmpty()).isFalse();

        // add_first_nonempty
        ad.addFirst(2);
        ad.addFirst(3);
        assertThat(ad.toList()).containsExactly(3, 2, 1).inOrder();
        assertThat(ad.get(0)).isEqualTo(3);
        assertThat(ad.get(1)).isEqualTo(2);
        assertThat(ad.get(2)).isEqualTo(1);

        // add_first_trigger_resize
        ad.addFirst(4);
        ad.addFirst(5);
        ad.addFirst(6);
        ad.addFirst(7);
        ad.addFirst(8);
        ad.addFirst(9); // 这里应该触发扩容（取决于实现初始容量）
        assertThat(ad.toList()).containsExactly(9, 8, 7, 6, 5, 4, 3, 2, 1).inOrder();
        assertThat(ad.size()).isEqualTo(9);
    }

    @Test
    public void testAddLastCoverage() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();

        // add_last_from_empty
        ad.addLast(1);
        assertThat(ad.toList()).containsExactly(1).inOrder();
        assertThat(ad.size()).isEqualTo(1);
        assertThat(ad.isEmpty()).isFalse();

        // add_last_nonempty
        ad.addLast(2);
        ad.addLast(3);
        assertThat(ad.toList()).containsExactly(1, 2, 3).inOrder();
        assertThat(ad.get(0)).isEqualTo(1);
        assertThat(ad.get(1)).isEqualTo(2);
        assertThat(ad.get(2)).isEqualTo(3);

        // add_last_trigger_resize
        ad.addLast(4);
        ad.addLast(5);
        ad.addLast(6);
        ad.addLast(7);
        ad.addLast(8);
        ad.addLast(9); // 触发扩容
        assertThat(ad.toList()).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9).inOrder();
        assertThat(ad.size()).isEqualTo(9);
    }

    @Test
    public void testAddAfterRemoveToEmptyCoverage() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();

        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);

        assertThat(ad.removeFirst()).isEqualTo(1);
        assertThat(ad.removeFirst()).isEqualTo(2);
        assertThat(ad.removeFirst()).isEqualTo(3);
        assertThat(ad.isEmpty()).isTrue();
        assertThat(ad.size()).isEqualTo(0);

        // add_first_after_remove_to_empty
        ad.addFirst(100);
        assertThat(ad.toList()).containsExactly(100).inOrder();
        assertThat(ad.size()).isEqualTo(1);

        assertThat(ad.removeLast()).isEqualTo(100);
        assertThat(ad.isEmpty()).isTrue();

        // add_last_after_remove_to_empty
        ad.addLast(200);
        assertThat(ad.toList()).containsExactly(200).inOrder();
        assertThat(ad.size()).isEqualTo(1);
    }

    @Test
    public void testRemoveFirstCoverage() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();

        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);

        // remove_first
        assertThat(ad.removeFirst()).isEqualTo(1);
        assertThat(ad.toList()).containsExactly(2, 3).inOrder();
        assertThat(ad.size()).isEqualTo(2);

        // remove_first_to_one
        assertThat(ad.removeFirst()).isEqualTo(2);
        assertThat(ad.toList()).containsExactly(3).inOrder();
        assertThat(ad.size()).isEqualTo(1);

        // remove_first_to_empty
        assertThat(ad.removeFirst()).isEqualTo(3);
        assertThat(ad.toList()).isEmpty();
        assertThat(ad.size()).isEqualTo(0);
        assertThat(ad.isEmpty()).isTrue();
    }

    @Test
    public void testRemoveLastCoverage() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();

        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);

        // remove_last
        assertThat(ad.removeLast()).isEqualTo(3);
        assertThat(ad.toList()).containsExactly(1, 2).inOrder();
        assertThat(ad.size()).isEqualTo(2);

        // remove_last_to_one
        assertThat(ad.removeLast()).isEqualTo(2);
        assertThat(ad.toList()).containsExactly(1).inOrder();
        assertThat(ad.size()).isEqualTo(1);

        // remove_last_to_empty
        assertThat(ad.removeLast()).isEqualTo(1);
        assertThat(ad.toList()).isEmpty();
        assertThat(ad.size()).isEqualTo(0);
        assertThat(ad.isEmpty()).isTrue();
    }

    @Test
    public void testRemoveFirstTriggerResize() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();

        for (int i = 0; i < 32; i++) {
            ad.addLast(i);
        }

        for (int i = 0; i < 24; i++) {
            assertThat(ad.removeFirst()).isEqualTo(i);
        }

        // 现在通常已经接近/达到缩容触发区间
        assertThat(ad.size()).isEqualTo(8);
        assertThat(ad.toList()).containsExactly(24, 25, 26, 27, 28, 29, 30, 31).inOrder();

        for (int i = 24; i < 28; i++) {
            assertThat(ad.removeFirst()).isEqualTo(i);
        }

        // remove_first_trigger_resize
        assertThat(ad.size()).isEqualTo(4);
        assertThat(ad.toList()).containsExactly(28, 29, 30, 31).inOrder();
    }

    @Test
    public void testRemoveLastTriggerResize() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();

        for (int i = 0; i < 32; i++) {
            ad.addLast(i);
        }

        for (int i = 31; i >= 8; i--) {
            assertThat(ad.removeLast()).isEqualTo(i);
        }

        assertThat(ad.size()).isEqualTo(8);
        assertThat(ad.toList()).containsExactly(0, 1, 2, 3, 4, 5, 6, 7).inOrder();

        for (int i = 7; i >= 4; i--) {
            assertThat(ad.removeLast()).isEqualTo(i);
        }

        // remove_last_trigger_resize
        assertThat(ad.size()).isEqualTo(4);
        assertThat(ad.toList()).containsExactly(0, 1, 2, 3).inOrder();
    }

    @Test
    public void testGetCoverage() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();

        ad.addLast(10);
        ad.addLast(20);
        ad.addLast(30);

        // get_valid
        assertThat(ad.get(0)).isEqualTo(10);
        assertThat(ad.get(1)).isEqualTo(20);
        assertThat(ad.get(2)).isEqualTo(30);

        // get_oob_large
        assertThat(ad.get(3)).isNull();
        assertThat(ad.get(100)).isNull();

        // get_oob_neg
        assertThat(ad.get(-1)).isNull();
        assertThat(ad.get(-100)).isNull();
    }

    @Test
    public void testSizeCoverage() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();

        // size
        assertThat(ad.size()).isEqualTo(0);

        ad.addFirst(1);
        ad.addLast(2);
        ad.addFirst(3);
        assertThat(ad.size()).isEqualTo(3);

        ad.removeFirst();
        assertThat(ad.size()).isEqualTo(2);

        ad.removeLast();
        assertThat(ad.size()).isEqualTo(1);
    }

    @Test
    public void testSizeAfterRemoveToEmptyCoverage() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();

        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);

        assertThat(ad.size()).isEqualTo(3);

        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();

        // size_after_remove_to_empty
        assertThat(ad.size()).isEqualTo(0);
        assertThat(ad.isEmpty()).isTrue();
    }

    @Test
    public void testSizeAfterRemoveFromEmptyCoverage() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();

        assertThat(ad.size()).isEqualTo(0);

        ad.removeFirst();
        assertThat(ad.size()).isEqualTo(0);

        ad.removeLast();
        assertThat(ad.size()).isEqualTo(0);

        // size_after_remove_from_empty
        assertThat(ad.isEmpty()).isTrue();
    }

    @Test
    public void testIsEmptyCoverage() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();

        // is_empty_true
        assertThat(ad.isEmpty()).isTrue();

        ad.addLast(1);

        // is_empty_false
        assertThat(ad.isEmpty()).isFalse();

        ad.removeFirst();
        assertThat(ad.isEmpty()).isTrue();
    }

    @Test
    public void testToListCoverage() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();

        // to_list_empty
        assertThat(ad.toList()).isEmpty();

        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);

        // to_list_nonempty
        assertThat(ad.toList()).containsExactly(1, 2, 3).inOrder();
    }

    @Test
    public void testResizeUpAndResizeDownCoverage() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();

        // 先扩容
        for (int i = 0; i < 20; i++) {
            ad.addLast(i);
        }
        assertThat(ad.size()).isEqualTo(20);
        assertThat(ad.get(0)).isEqualTo(0);
        assertThat(ad.get(19)).isEqualTo(19);

        // 再缩容
        for (int i = 0; i < 17; i++) {
            assertThat(ad.removeFirst()).isEqualTo(i);
        }

        // resize_up_and_resize_down
        assertThat(ad.size()).isEqualTo(3);
        assertThat(ad.toList()).containsExactly(17, 18, 19).inOrder();
    }

    @Test
    public void testWrapAroundAndMixedOperations() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();

        for (int i = 0; i < 8; i++) {
            ad.addLast(i);
        }

        for (int i = 0; i < 5; i++) {
            assertThat(ad.removeFirst()).isEqualTo(i);
        }

        ad.addLast(8);
        ad.addLast(9);
        ad.addFirst(100);
        ad.addFirst(200);

        assertThat(ad.toList()).containsExactly(200, 100, 5, 6, 7, 8, 9).inOrder();
        assertThat(ad.get(0)).isEqualTo(200);
        assertThat(ad.get(1)).isEqualTo(100);
        assertThat(ad.get(2)).isEqualTo(5);
        assertThat(ad.get(6)).isEqualTo(9);
        assertThat(ad.size()).isEqualTo(7);
    }

    @Test
    public void testRemoveOnEmptyStillSafe() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();

        assertThat(ad.removeFirst()).isNull();
        assertThat(ad.removeLast()).isNull();
        assertThat(ad.size()).isEqualTo(0);
        assertThat(ad.isEmpty()).isTrue();
        assertThat(ad.toList()).isEmpty();
    }

    @Test
    public void testClearThenReuseHard() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();

        for (int i = 0; i < 16; i++) {
            ad.addLast(i);
        }

        for (int i = 0; i < 16; i++) {
            assertThat(ad.removeFirst()).isEqualTo(i);
        }

        assertThat(ad.isEmpty()).isTrue();
        assertThat(ad.size()).isEqualTo(0);
        assertThat(ad.toList()).isEmpty();

        ad.addFirst(1);
        ad.addLast(2);
        ad.addFirst(0);

        assertThat(ad.toList()).containsExactly(0, 1, 2).inOrder();
        assertThat(ad.get(0)).isEqualTo(0);
        assertThat(ad.get(1)).isEqualTo(1);
        assertThat(ad.get(2)).isEqualTo(2);
    }
}