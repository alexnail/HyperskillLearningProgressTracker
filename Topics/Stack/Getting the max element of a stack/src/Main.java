import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> maxStack = new ArrayDeque<>();

        int inputSize = scanner.nextInt();
        scanner.nextLine();
        Deque<String> commands = new ArrayDeque<>(inputSize);

        for (int i = 0; i < inputSize; i++) {
            commands.add(scanner.nextLine());
        }

        for (String s : commands) {
            if (s.startsWith("push")) {
                Integer n = Integer.parseInt(s.substring(s.indexOf(" ") + 1));
                stack.push(n);
                if (maxStack.isEmpty() || n > maxStack.peek()) {
                    maxStack.push(n);
                } else {
                    maxStack.push(maxStack.peek());
                }
            } else if (s.equals("pop")) {
                stack.pop();
                maxStack.pop();
            } else if (s.equals("max")) {
                System.out.println(maxStack.peek());
            }
        }
    }
}