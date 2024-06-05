import java.util.*;

class ListOperations {
    public static void removeTheSame(LinkedList<String> linkedList, ArrayList<String> arrayList) {
        int i = 0;
        while (i < linkedList.size()) {
            if (arrayList.get(i).equals(linkedList.get(i))) {
                linkedList.remove(i);
                arrayList.remove(i);
            } else {
                i++;
            }
        }
    }
}