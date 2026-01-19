package deque;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;

public class Maximizer61B {
    /**
     * Returns the maximum element from the given iterable of comparables.
     * You may assume that the iterable contains no nulls.
     *
     * @param iterable  the Iterable of T
     * @return          the maximum element
     */
    public static <T extends Comparable<T>> T max(Iterable<T> iterable) {
        // Java stream works like C# LINQ
        return StreamSupport.stream(iterable.spliterator(), false)
                .max(Comparable::compareTo)
                .orElseThrow(() -> new NoSuchElementException("Empty collection"));
    }

    /**
     * Returns the maximum element from the given iterable according to the specified comparator.
     * You may assume that the iterable contains no nulls.
     *
     * @param iterable  the Iterable of T
     * @param comp      the Comparator to compare elements
     * @return          the maximum element according to the comparator
     */
    public static <T> T max(Iterable<T> iterable, Comparator<T> comp) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .max(comp)
                .orElseThrow(() -> new NoSuchElementException("Empty collection"));
    }

    public static void main(String[] args) {
        // The style checker will complain about this main method, feel free to delete.

         deque.ArrayDeque61B<Integer> ad = new deque.ArrayDeque61B<>();
         ad.addLast(5);
         ad.addLast(12);
         ad.addLast(17);
         ad.addLast(23);
         System.out.println(max(ad));
    }
}
