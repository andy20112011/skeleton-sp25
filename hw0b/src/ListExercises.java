import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
    public static int sum(List<Integer> L) {
        int ans = 0;
        for (int i : L) {
            ans += i;
        }
        return ans;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> ans = new ArrayList<>();
        for (int i : L) {
            if (i % 2 == 0) {
                ans.add(i);
            }
        }
        return ans;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> ans = new ArrayList<>();
        for (int i : L1) {
            if (L2.contains(i)) {
                ans.add(i);
            }
        }
        return ans;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int ans = 0;
        for (String i : words) {
            for (char character : i.toCharArray()) {
                if (character == c) {
                    ans += 1;
                }
            }
        }

        return ans;
    }
}
