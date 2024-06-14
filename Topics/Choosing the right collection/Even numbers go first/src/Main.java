import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        Deque<Integer> numbers = new ArrayDeque<>();
        int numOfScans = scanner.nextInt();

        for (int i = 0; i < numOfScans; i++) {
            int number = scanner.nextInt();
            if (number % 2 == 0) {
                numbers.addFirst(number);
            } else {
                numbers.addLast(number);
            }
        }
        numbers.forEach(System.out::println);
    }
}