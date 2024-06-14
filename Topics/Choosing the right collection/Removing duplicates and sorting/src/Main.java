import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        scanner.nextLine();

        Set<String> strings = new TreeSet<>();
        for (int i = 0; i < size; i++) {
            strings.add(scanner.nextLine());
        }
        strings.forEach(System.out::println);
    }
}