package leetcode_bank;

class Solution {
    public int totalMoney(int n) {
        int weeks  = n / 7;
        int leftoverDays = n % 7;
        int totalMoney = 0;
        System.out.println("Weeks: " + weeks);
        System.out.println("leftover days: " + leftoverDays);

        for (int j = 0; j <= weeks; j++) {
            if(j == weeks) {
                for (int i = 1; i <= leftoverDays; i++) {
                    totalMoney += i + j;
                    System.out.print(totalMoney + " ");
                }
            }
            else {
                totalMoney = totalMoney + 28 + (7 * j);
                System.out.print(totalMoney + " ");
            }
        }//outer
        return totalMoney;
    }

    static void main() {
        int n = 10;
        Solution solution = new Solution();
        int x = solution.totalMoney(n);
    }
}