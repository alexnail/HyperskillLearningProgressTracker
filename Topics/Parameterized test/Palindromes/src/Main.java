class StringUtils {
    public static boolean isPalindrome(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        String adjusted = str.replaceAll("[\\s']", "")
                .toLowerCase();

        int begin = 0;
        int end = adjusted.length() - 1;
        while (begin < end) {
            if (adjusted.charAt(begin) != adjusted.charAt(end)) {
                return false;
            }
            begin++;
            end--;
        }
        return true;
    }
}