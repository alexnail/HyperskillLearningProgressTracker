import java.util.ArrayList;
import java.util.LinkedList;

class ListOperations {
    public static void transferAllElements(LinkedList<String> linkedList, ArrayList<String> arrayList) {
        // write your code here
        int i = 0;
        while (i < linkedList.size()) {
            String tmp = linkedList.get(i);
            linkedList.set(i, arrayList.get(i));
            arrayList.set(i, tmp);
            i++;
        }

    }
}