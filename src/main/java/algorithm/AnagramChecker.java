package algorithm;

import java.util.Arrays;

public class AnagramChecker {

	public static boolean isAnagram(String word1, String word2) {
                boolean result = false;

                if (!(word1.length() == word2.length())) {
                    return result;
                }
                /*
                for (int i = 0; i < word1.length(); i++) {
                    for (int j = 0; j < word2.length(); j++) {
                        if (!(word1.charAt(i) == word2.charAt(j))) {
                            continue;
                        }
                        if (j == word2.length())
                            return result;
                    }
                }
                result = true;
                return result; *///prototype

                char[] array1 = word1.toLowerCase().toCharArray();
                char[] array2 = word2.toLowerCase().toCharArray();

                Arrays.sort(array1);
                Arrays.sort(array2);

                return Arrays.equals(array1, array2);

	}
	
	
}
