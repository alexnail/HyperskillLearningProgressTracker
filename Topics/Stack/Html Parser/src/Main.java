import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        Deque<String> tags = new ArrayDeque<>();
        Deque<String> content = new ArrayDeque<>();

        int openTagStartIdx = 0;
        int openTagFinishIdx = 0;
        int closeTagStartIdx = 0;
        int closeTagFinishIdx = 0;

        do {
            // find open tag, trim it and push on tags stack
            openTagStartIdx = input.indexOf("<", closeTagFinishIdx);
            openTagFinishIdx = input.indexOf(">", openTagStartIdx);
            String openTag = input.substring(openTagStartIdx + 1, input.indexOf(">", openTagFinishIdx)).trim();
            tags.push(openTag);

            // right after it is the content of this tag, push it into content stack
            /*closeTagStartIdx = input.indexOf("</", openTagFinishIdx);
            closeTagFinishIdx = input.indexOf(">", closeTagStartIdx);*/
            closeTagStartIdx = input.lastIndexOf("</" + tags.peek() + ">");
            closeTagFinishIdx = closeTagStartIdx + tags.peek().length() + 1;
            content.push(input.substring(openTagFinishIdx + 1, closeTagStartIdx));

            // when we meet the closing pair of the open tag we remove the tag off the tags stack
            String closeTag = input.substring(closeTagStartIdx + 2, closeTagFinishIdx).trim();
            if (tags.peek().equals(closeTag)) {
                tags.pop();
                System.out.println(content.pop());
            }
        } while (!tags.isEmpty());
    }


}