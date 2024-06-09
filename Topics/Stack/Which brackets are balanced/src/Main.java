import java.util.*;

class Main {
    private static final List<Character> ALL_BRACKET_PAIRS = new LinkedList<>(List.of('(', ')', '{', '}', '[', ']'));
    private static final Set<Character> OPEN_BRACKET = Set.of('(', '{', '[');
    private static final Set<Character> CLOSE_BRACKET = Set.of(')', '}', ']');

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Deque<Character> stack = new ArrayDeque<>();
        boolean isBalanced = true;

        for (int i = 0; i < input.length(); i++) {
            Character character = input.charAt(i);
            if (OPEN_BRACKET.contains(character)) {
                stack.push(character);
            } else if (CLOSE_BRACKET.contains(character)) {
                if (stack.isEmpty()) {
                    isBalanced = false;
                    break;
                }
                int idxO = ALL_BRACKET_PAIRS.indexOf(character) - 1;
                if (!stack.pop().equals(ALL_BRACKET_PAIRS.get(idxO))) {
                    isBalanced = false;
                    break;
                }
            }
        }

        System.out.println(stack.isEmpty() && isBalanced);
    }
}