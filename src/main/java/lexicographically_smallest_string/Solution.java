package lexicographically_smallest_string;

import java.util.Arrays;

public class Solution {
    public String findLexSmallestString(String s, int a, int b) {
        int length = s.length();
        int[] stringNumber = new int[s.length()];
        int[] stringNumberClone = new int[s.length()];

        int[] currLexSmallestStr = new int[s.length()];

        for(int i = 0; i < length; i++) {
            stringNumber[i] = s.charAt(i) - '0';
            currLexSmallestStr[i] = s.charAt(i) - '0';
        }//parse string into integer array

        //*rotate by index of b
        for(int j = 0; j < (length/b) * a; j++) {

            for(int i = 0; i < length; i++) {
                stringNumberClone[i] = stringNumber[(i - b % length + length) % length];
            }//*inner for 1: rotate array by b

            System.arraycopy(stringNumberClone, 0, stringNumber, 0, length);
            for(int i = 0; i < length; i++) {
                if (stringNumber[i] < currLexSmallestStr[i]) {
                    currLexSmallestStr = Arrays.copyOf(stringNumber, length);
                    break;
                } else if (stringNumber[i] > currLexSmallestStr[i]) {
                    break;
                }
            }//*inner for 2: checks each iteration of the array and compares with previous LSS

            for(int i = 1; i < length; i += 2) {
                stringNumber[i] = (stringNumber[i] + a) % 10;
            }//* a-step

            for(int i = 0; i < length; i++) {
                if (stringNumber[i] < currLexSmallestStr[i]) {
                    currLexSmallestStr = Arrays.copyOf(stringNumber, length);
                    break;
                }
                else if (stringNumber[i] > currLexSmallestStr[i]) {
                    break;
                }
            }//*inner for 2: checks each iteration of the array and compares with previous LSS
        }//*outer for: process
        StringBuilder sb = new StringBuilder();
        for (int num : currLexSmallestStr) {
            sb.append(num);
        }
        return sb.toString();
    }
}
