package levenshtein;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        System.out.println("Input 2 strings to compute Levenshtein distance between them");

        System.out.println("String 1: ");
        String line1 = scnr.nextLine();
        System.out.println("String 2: ");
        String line2 = scnr.nextLine();

        callCalcLevenshtein(line1, line2);
    }

    public static void callCalcLevenshtein(String string1, String string2) {
        int lD = LevenshteinDistance.calcLevenshteinDistance(string1, string2);

        System.out.println("LD between '" + string1 + "' and '" + string2 + "': " + lD);
    }
}
