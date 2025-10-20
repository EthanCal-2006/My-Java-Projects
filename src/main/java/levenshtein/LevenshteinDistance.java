package levenshtein;

public class LevenshteinDistance {

    public static int calcLevenshteinDistance (String line1, String line2) {
        int strLength1 = line1.length();
        int strLength2 = line2.length();

        int[][] dp = new int[strLength1+1][strLength2+1];

        for(int i = 0; i <= strLength1; i++) {
            dp[i][0] = i; //cost of deleting all characters in string1
        }

        for(int j = 0; j <=strLength2; j++) {
            dp[0][j] = j;//cost of inserting all characters in string 2
        }
        //populate the dp array
        for(int i = 1; i <= strLength1; i++) {
            for(int j = 1; j <= strLength2; j++) {

                if(line1.charAt(i - 1) == line2.charAt((j - 1))) {
                    //*no operation needed
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(
                            dp[i - 1][j],
                            Math.min(
                                    dp[i][j - 1],
                                    dp[i - 1][j - 1]
                            )
                    ) + 1;//*END Math.min
                }

            }//inner for
        }//outer for
        return dp[strLength1][strLength2];
    }
}
