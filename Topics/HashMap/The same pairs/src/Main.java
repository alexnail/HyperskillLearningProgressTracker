import java.util.*;


class MapFunctions {

    public static void calcTheSamePairs(Map<String, String> map1, Map<String, String> map2) {
        // write your code here
        System.out.println(map1.entrySet().stream()
                .filter(e -> map2.containsKey(e.getKey())
                        && map2.get(e.getKey()).equals(e.getValue()))
                .count());

    }
}