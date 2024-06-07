import java.util.*;


class MapFunctions {

    public static void printWithSameLetter(Map<String, String> map) {
        map.entrySet().stream()
                .filter(entry -> entry.getKey().getBytes()[0] == entry.getValue().getBytes()[0])
                .forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
    }
}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> map = new HashMap<>();

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] pair = s.split(" ");
            map.put(pair[0], pair[1]);
        }

        MapFunctions.printWithSameLetter(map);
    }
}