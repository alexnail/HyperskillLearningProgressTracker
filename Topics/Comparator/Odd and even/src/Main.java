import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

class Utils {

    public static List<Integer> sortOddEven(List<Integer> numbers) {
        List<Integer> odd = numbers.stream()
                .filter(n -> n % 2 != 0)
                .sorted().toList();
        List<Integer> even = numbers.stream()
                .filter(n -> n % 2 == 0)
                .sorted(Comparator.reverseOrder()).toList();
        List<Integer> result = new LinkedList<>(odd);
        result.addAll(even);
        return result;
    }
}