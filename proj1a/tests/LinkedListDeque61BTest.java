import edu.princeton.cs.algs4.In;
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

    // Below, you'll write your own tests for LinkedListDeque61B.
    @Test
    /** This test verify isEmpty. */
    public void testIsEmpty() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.isEmpty()).isTrue();

        lld1.addFirst("dog");

        assertThat(lld1.isEmpty()).isFalse();
    }

    @Test
    /** This test verify size equal 0. */
    public void testZeroSize() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.size()).isEqualTo(0);
    }

    @Test
    /** This test verify size equal 0. */
    public void testOneSize() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst(1);
        assertThat(lld1.size()).isEqualTo(1);
    }

    @Test
    /** This test performs interspersed isEmpty and size. */
    public void testIsEmptyAndSize() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        lld1.addLast(3);
        lld1.addLast(4);
        lld1.addFirst(2);

        assertThat(lld1.size()).isEqualTo(3);
        assertThat(lld1.isEmpty()).isFalse();
    }

    @Test
    /** This test get. */
    public void testGet() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.get(1)).isNull();

        lld1.addFirst(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addLast(4);

        assertThat(lld1.get(4)).isEqualTo(4);
        assertThat(lld1.get(7)).isNull();
    }

    @Test
    /** This test get. */
    public void testGetRecursive() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.getRecursive(1)).isNull();

        lld1.addFirst(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addLast(4);

        assertThat(lld1.getRecursive(4)).isEqualTo(4);
        assertThat(lld1.get(7)).isNull();
    }

    @Test
    /** This test remove. */
    public void testRemove() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addLast(4);
        lld1.addLast(5);
        lld1.addLast(6);

        assertThat(lld1.toList()).containsExactly(1, 2, 3, 4, 5, 6).inOrder();
        assertThat(lld1.size()).isEqualTo(6);

        int first = lld1.removeFirst();
        assertThat(first).isEqualTo(1);
        assertThat(lld1.toList()).containsExactly(2, 3, 4, 5, 6).inOrder();
        assertThat(lld1.size()).isEqualTo(5);

        int last = lld1.removeLast();
        assertThat(last).isEqualTo(6);
        assertThat(lld1.toList()).containsExactly(2, 3, 4, 5).inOrder();
        assertThat(lld1.size()).isEqualTo(4);

        assertThat(lld1.removeFirst()).isEqualTo(2);
        assertThat(lld1.removeLast()).isEqualTo(5);
    }
}