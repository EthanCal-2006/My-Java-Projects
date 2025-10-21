package string_analyzer;

import java.util.Scanner;

public class StringAnalyzer {
    public static int wordCounter(String phrase) {
        int wordCount = 1;
        for(int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) == ' ') {
                wordCount++;
            }
        }
        return wordCount;
    }

    public static int vowelCounter(String phrase) {
        int vowelCount = 0;
        for (int i = 0; i < phrase.length(); i++) {
            char c = Character.toLowerCase(phrase.charAt(i));
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowelCount++;
            }
        }
        return vowelCount;
    }

    public static int consonantCounter(String phrase) {
        int consonantCount = 0;
        for (int i = 0; i < phrase.length(); i++) {
            char c = Character.toLowerCase(phrase.charAt(i));
            if (!(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == ' ')) {
                consonantCount++;
            }
        }
        return consonantCount;
    }

    static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String phrase = scanner.nextLine();
        System.out.println("Words: " + wordCounter(phrase));
        System.out.println("Vowels " + vowelCounter(phrase));
        System.out.println("Consonants: " + consonantCounter(phrase));
    }


}
