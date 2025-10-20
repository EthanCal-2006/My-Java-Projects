package lcs_algorithm;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first string: ");
        String str1 = scanner.nextLine();
        System.out.println("Enter second string: ");
        String str2 = scanner.nextLine();
        int lcsNumber = compareStrings(str1, str2);
        System.out.println("Length of LCS: " + lcsNumber);
    }
    public static int compareStrings(String str1, String str2) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for(int i = 1; i <= str1.length(); i++) {
            for(int j = 1; j <= str2.length(); j++) {
                if(Character.toUpperCase(str1.charAt(i - 1)) == Character.toUpperCase(str2.charAt(j - 1))) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }//innfor
        }//outfor
        printDPTable(dp, str1.length(), str2.length());
        return dp[str1.length()][str2.length()];
    }

    public static void printDPTable(int[][] dp, int m, int n) {
        System.out.println("Current DP Table:");
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
