import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> stack = new ArrayDeque<>();

        int inputSize = scanner.nextInt();
        for (int i = 0; i < inputSize; i++) {
            int num = scanner.nextInt();
            stack.push(num);
        }

        while (!stack.isEmpty()) {
            int num = stack.pop();
            System.out.println(num);
        }
    }
}