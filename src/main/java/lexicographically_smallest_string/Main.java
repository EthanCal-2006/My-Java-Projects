package lexicographically_smallest_string;

public class Main {
    static void main() {
        String number = "5525";
        int a = 9;
        int b = 2;

        Solution soln = new Solution();
        String solution = soln.findLexSmallestString(number, a, b);
        System.out.println(solution);
    }
}
