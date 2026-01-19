import deque.ArrayDeque61B;
import deque.LinkedListDeque61B;

import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LinkedListDeque61BTest {

    @Test
    @DisplayName("deque.LinkedListDeque61B has no fields besides nodes and primitives")
    void noNonTrivialFields() {
        List<Field> badFields = Reflection.getFields(deque.LinkedListDeque61B.class)
                .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object.class) || f.isSynthetic()))
                .toList();

        assertWithMessage("Found fields that are not primitives or Object").that(badFields).isEmpty();
    }

    @Test
    @DisplayName("Test constructor")
    void testConstructor() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        assertThat(deque.isEmpty()).isTrue();
        assertThat(deque.size()).isEqualTo(0);
        assertThat(deque.toList()).isEmpty();
    }

    @Test
    @DisplayName("Test addFirst on empty deque")
    void testAddFirstEmpty() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addFirst(1);
        assertThat(deque.isEmpty()).isFalse();
        assertThat(deque.size()).isEqualTo(1);
        assertThat(deque.toList()).containsExactly(1);
    }

    @Test
    @DisplayName("Test addLast on empty deque")
    void testAddLastEmpty() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addLast(1);
        assertThat(deque.size()).isEqualTo(1);
        assertThat(deque.toList()).containsExactly(1);
    }

    @Test
    @DisplayName("Test addFirst multiple")
    void testAddFirstMultiple() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        assertThat(deque.size()).isEqualTo(3);
        assertThat(deque.toList()).containsExactly(3,2,1);
    }

    @Test
    @DisplayName("Test addLast multiple")
    void testAddLastMultiple() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        assertThat(deque.size()).isEqualTo(3);
        assertThat(deque.toList()).containsExactly(1,2,3);
    }

    @Test
    @DisplayName("Test removeFirst on empty")
    void testRemoveFirstEmpty() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        assertThat(deque.removeFirst()).isNull();
        assertThat(deque.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Test removeFirst on one element")
    void testRemoveFirstOne() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addFirst(1);
        assertThat(deque.removeFirst()).isEqualTo(1);
        assertThat(deque.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Test removeFirst multiple")
    void testRemoveFirstMultiple() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        assertThat(deque.removeFirst()).isEqualTo(3);
        assertThat(deque.removeFirst()).isEqualTo(2);
        assertThat(deque.removeFirst()).isEqualTo(1);
        assertThat(deque.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Test removeLast on empty")
    void testRemoveLastEmpty() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        assertThat(deque.removeLast()).isNull();
    }

    @Test
    @DisplayName("Test removeLast one")
    void testRemoveLastOne() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addLast(1);
        assertThat(deque.removeLast()).isEqualTo(1);
    }

    @Test
    @DisplayName("Test removeLast multiple")
    void testRemoveLastMultiple() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        assertThat(deque.removeLast()).isEqualTo(3);
        assertThat(deque.removeLast()).isEqualTo(2);
        assertThat(deque.removeLast()).isEqualTo(1);
    }

    @Test
    @DisplayName("Test get valid index")
    void testGetValid() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        assertThat(deque.get(0)).isEqualTo(1);
        assertThat(deque.get(1)).isEqualTo(2);
        assertThat(deque.get(2)).isEqualTo(3);
    }

    @Test
    @DisplayName("Test get invalid index")
    void testGetInvalid() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addLast(1);
        assertThat(deque.get(-1)).isNull();
        assertThat(deque.get(1)).isNull();
        assertThat(deque.get(2)).isNull();
    }

    @Test
    @DisplayName("Test getRecursive")
    void testGetRecursive() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        assertThat(deque.getRecursive(0)).isEqualTo(1);
        assertThat(deque.getRecursive(1)).isEqualTo(2);
        assertThat(deque.getRecursive(2)).isEqualTo(3);
    }

    @Test
    @DisplayName("Test mixed operations")
    void testMixed() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(0);
        deque.addLast(3);
        // should be 0,1,2,3
        assertThat(deque.toList()).containsExactly(0,1,2,3);
        assertThat(deque.size()).isEqualTo(4);
        assertThat(deque.removeFirst()).isEqualTo(0);
        assertThat(deque.removeLast()).isEqualTo(3);
        assertThat(deque.toList()).containsExactly(1,2);
    }

    @Test
    @DisplayName("Test iterator on empty deque")
    void testIteratorEmpty() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        Iterator<Integer> iterator = deque.iterator();
        assertThat(iterator.hasNext()).isFalse();
        assertThrows(java.util.NoSuchElementException.class, iterator::next);
    }

    @Test
    @DisplayName("Test iterator on deque with elements")
    void testIteratorWithElements() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        Iterator<Integer> iterator = deque.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.hasNext()).isFalse();
        assertThrows(java.util.NoSuchElementException.class, iterator::next);
    }

    @Test
    @DisplayName("Test iterator does not modify deque")
    void testIteratorNonDestructive() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        Iterator<Integer> iterator = deque.iterator();
        iterator.next();
        iterator.next();
        assertThat(deque.size()).isEqualTo(2);
        assertThat(deque.toList()).containsExactly(1,2);
    }

    @Test
    @DisplayName("Test equals with empty deques")
    void testEqualsEmpty() {
        LinkedListDeque61B<Integer> deque1 = new LinkedListDeque61B<>();
        LinkedListDeque61B<Integer> deque2 = new LinkedListDeque61B<>();
        assertThat(deque1.equals(deque2)).isTrue();
        assertThat(deque2.equals(deque1)).isTrue();
    }

    @Test
    @DisplayName("Test equals with same elements")
    void testEqualsSameElements() {
        LinkedListDeque61B<Integer> deque1 = new LinkedListDeque61B<>();
        LinkedListDeque61B<Integer> deque2 = new LinkedListDeque61B<>();
        deque1.addLast(1);
        deque1.addLast(2);
        deque1.addLast(3);
        deque2.addLast(1);
        deque2.addLast(2);
        deque2.addLast(3);
        assertThat(deque1.equals(deque2)).isTrue();
        assertThat(deque2.equals(deque1)).isTrue();
    }

    @Test
    @DisplayName("Test equals with different elements")
    void testEqualsDifferentElements() {
        LinkedListDeque61B<Integer> deque1 = new LinkedListDeque61B<>();
        LinkedListDeque61B<Integer> deque2 = new LinkedListDeque61B<>();
        deque1.addLast(1);
        deque1.addLast(2);
        deque1.addLast(3);
        deque2.addLast(1);
        deque2.addLast(2);
        deque2.addLast(4);
        assertThat(deque1.equals(deque2)).isFalse();
        assertThat(deque2.equals(deque1)).isFalse();
    }

    @Test
    @DisplayName("Test equals with different sizes")
    void testEqualsDifferentSizes() {
        LinkedListDeque61B<Integer> deque1 = new LinkedListDeque61B<>();
        LinkedListDeque61B<Integer> deque2 = new LinkedListDeque61B<>();
        deque1.addLast(1);
        deque1.addLast(2);
        deque2.addLast(1);
        assertThat(deque1.equals(deque2)).isFalse();
        assertThat(deque2.equals(deque1)).isFalse();
    }

    @Test
    @DisplayName("Test equals with same elements different order")
    void testEqualsSameElementsDifferentOrder() {
        LinkedListDeque61B<Integer> deque1 = new LinkedListDeque61B<>();
        LinkedListDeque61B<Integer> deque2 = new LinkedListDeque61B<>();
        deque1.addLast(1);
        deque1.addLast(2);
        deque1.addLast(3);
        deque2.addLast(3);
        deque2.addLast(2);
        deque2.addLast(1);
        assertThat(deque1.equals(deque2)).isFalse();
        assertThat(deque2.equals(deque1)).isFalse();
    }

    @Test
    @DisplayName("Test equals with same deque")
    void testEqualsSameInstance() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addLast(1);
        assertThat(deque.equals(deque)).isTrue();
    }

    @Test
    @DisplayName("Test equals with null")
    void testEqualsNull() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        assertThat(deque.equals(null)).isFalse();
    }

    @Test
    @DisplayName("Test equals with different implementation")
    void testEqualsDifferentImplementation() {
        ArrayDeque61B<Integer> arrayDeque = new ArrayDeque61B<>();
        LinkedListDeque61B<Integer> linkedDeque = new LinkedListDeque61B<>();
        arrayDeque.addLast(1);
        arrayDeque.addLast(2);
        linkedDeque.addLast(1);
        linkedDeque.addLast(2);
        assertThat(linkedDeque.equals(arrayDeque)).isTrue();
        assertThat(arrayDeque.equals(linkedDeque)).isTrue();
    }

    @Test
    @DisplayName("Test equals with non-Deque61B object")
    void testEqualsNonDeque() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addLast(1);
        assertThat(deque.equals("not a deque")).isFalse();
    }

}
