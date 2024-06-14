import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String original = scanner.nextLine();
        String resulting = scanner.nextLine();

        Map<Character, Character> ciperMap = new HashMap<>();
        Map<Character, Character> deciperMap = new HashMap<>();

        for (int i = 0; i < original.length(); i++) {
            char originalChar = original.charAt(i);
            char resultingChar = resulting.charAt(i);
            ciperMap.put(originalChar, resultingChar);
            deciperMap.put(resultingChar, originalChar);
        }

        String toEncode = scanner.nextLine();
        String toDecode = scanner.nextLine();

        StringBuilder encoded = new StringBuilder();

        for (int i = 0; i < toEncode.length(); i++) {
            char originalChar = toEncode.charAt(i);
            char encodedChar = ciperMap.get(originalChar);
            encoded.append(encodedChar);
        }

        StringBuilder decoded = new StringBuilder();
        for (int i = 0; i < toDecode.length(); i++) {
            char encodedChar = toDecode.charAt(i);
            char originalChar = deciperMap.get(encodedChar);
            decoded.append(originalChar);
        }

        System.out.println(encoded);
        System.out.println(decoded);
    }
}