package naive_string_matching;

import java.util.ArrayList;
import java.util.List;

public class NaiveStringMatching {
    public static List<Integer> naiveStringMatching(String text, String pattern) {
        int textLength = text.length();
        int patternLength = pattern.length();
        List<Integer> result = new ArrayList<>();

        for(int i = 0; i <= textLength - patternLength; i++) {
            int j;
            for(j = 0; j < patternLength; j++) {
                if(text.charAt(i + j)!= pattern.charAt(j)) break;
            }
            if(j == patternLength) result.add(i);
        } return result;
    }

    public static void main(String[] args) {
        String text = "ABAAABCD";
        String pattern = "ABC";

        System.out.println("Text: " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println("naive string matching: " + naiveStringMatching(text, pattern));

    }
}
